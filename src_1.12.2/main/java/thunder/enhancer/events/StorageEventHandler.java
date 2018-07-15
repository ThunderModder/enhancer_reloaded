package thunder.enhancer.events;

import java.util.HashMap;

import com.google.common.collect.Maps;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import thunder.enhancer.api.IModifiable;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;
import thunder.enhancer.inventory.InventoryEnhanced;
import thunder.enhancer.items.defence.ItemDefSaveInventory;
import thunder.enhancer.network.HUDSyncMessage;
import thunder.enhancer.network.NetworkHandler;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;

public class StorageEventHandler {
	
	public static final StorageEventHandler storageEvent = new StorageEventHandler();
	
	public static void register(){

		MinecraftForge.EVENT_BUS.register(storageEvent);
	}
	

	public HashMap<String, IInventory []> storage = Maps.<String, IInventory []>newHashMap();

	
	@SubscribeEvent
    public void addToStorage(LivingDeathEvent event) {
		
		if(event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntity();
			IEnhancedInventory cap = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
			
			InventoryEnhanced inv = cap.getInventory();
			for(ItemStack stack : inv.getStacks()){
				if(!stack.isEmpty() && stack.getItem() instanceof IModifiable && stack.getItem() instanceof ItemDefSaveInventory){
					if(NBTItemProvider.discharge(stack, Constants.DEF_INVSAVE_BASE)){
						InventoryEnhanced invEnhanced = new InventoryEnhanced();
						invEnhanced.copy(inv);
						InventoryPlayer invPlayer = new InventoryPlayer(null);
						invPlayer.copyInventory(player.inventory);
						storage.put(player.getUniqueID().toString(), new IInventory []{invPlayer, invEnhanced});
						player.inventory.clear();
						cap.getInventory().clear();
						break;
					}
				}
			}
		}
	}
	
	@SubscribeEvent
    public void extractFromStorage(PlayerRespawnEvent event) {
		
		EntityPlayer player = (EntityPlayer)event.player;
		if(storage.containsKey(player.getUniqueID().toString()) && FMLCommonHandler.instance().getEffectiveSide().isServer()){
			player.inventory.copyInventory((InventoryPlayer) storage.get(player.getUniqueID().toString())[0]);
			IEnhancedInventory cap = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
			InventoryEnhanced inv = cap.getInventory();
			inv.copy((InventoryEnhanced) storage.get(player.getUniqueID().toString())[1]);
			NetworkHandler.network.sendTo(new HUDSyncMessage(cap), (EntityPlayerMP)player);		
			player.inventoryContainer.detectAndSendChanges();
			storage.remove(player.getUniqueID().toString());
								
		}
	}
	
	@SubscribeEvent
	public void onPlayerLoggedOut(PlayerLoggedOutEvent event) {	
		
		EntityPlayer player = (EntityPlayer)event.player;
		
		if(player != null && storage.containsKey(player.getUniqueID().toString())){
			storage.remove(player.getUniqueID().toString());
		}
	}

}
