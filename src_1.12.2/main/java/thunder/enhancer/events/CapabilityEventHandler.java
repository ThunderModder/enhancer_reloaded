package thunder.enhancer.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import thunder.enhancer.api.IModifiable;
import thunder.enhancer.core.Reference;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;
import thunder.enhancer.inventory.InventoryEnhanced;
import thunder.enhancer.items.ItemRegistry;
import thunder.enhancer.network.HUDSyncMessage;
import thunder.enhancer.network.HUDSyncRequestMessage;
import thunder.enhancer.network.NetworkHandler;
import thunder.enhancer.utils.Utils;

public class CapabilityEventHandler {
	
	public static void register(){
		MinecraftForge.EVENT_BUS.register(new CapabilityEventHandler());
	}
	
	public static final ResourceLocation INVENTORY_CAP = new ResourceLocation(Reference.MOD_ID, "inventory");
	public static final ResourceLocation ITEM_CAP = new ResourceLocation(Reference.MOD_ID, "item_modifiable");
	
	@SubscribeEvent
	public void syncInvHUDLog(EntityJoinWorldEvent event) {	
		
		if(event.getEntity() instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer)event.getEntity();
			if(player != null && player.getEntityWorld().isRemote){
				NetworkHandler.network.sendToServer(new HUDSyncRequestMessage());
			}
		}
	}
	
	@SubscribeEvent
	public void syncInvHUD(PlayerChangedDimensionEvent event) {		
		EntityPlayer player = (EntityPlayer)event.player;
		if(!player.world.isRemote){
			IEnhancedInventory cap = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);		
			NetworkHandler.network.sendTo(new HUDSyncMessage(cap), (EntityPlayerMP)player);		
		}	
	}
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent event) {
		
		if (event.getObject() instanceof EntityPlayer){
			event.addCapability(INVENTORY_CAP, new EnhancedInventoryProvider());
		}
		
	}
	
	@SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
		
        EntityPlayer player = event.getEntityPlayer();
        IEnhancedInventory newCap = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
        IEnhancedInventory oldCap = event.getOriginal().getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);

        newCap.copyInventory(oldCap);

    }
	
	@SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
		
		if(event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntity();
			IEnhancedInventory cap = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);

			InventoryEnhanced inv = cap.getInventory();
			for(ItemStack s : inv.getStacks()){
				if(!s.isEmpty() && s.getItem() instanceof IModifiable)
					((IModifiable)s.getItem()).onDeath(event, player, event.getSource(), s);
				if(!s.isEmpty() && s.getItem().equals(ItemRegistry.ItemUTFlightModifier)){
					 if(!player.capabilities.isCreativeMode && player.capabilities.allowFlying){
				        	player.capabilities.allowFlying = false;
				        	if(player.capabilities.isFlying)
				        		player.capabilities.isFlying = false;
				        }
				}
				if(!s.isEmpty() && s.getItem().equals(ItemRegistry.ItemDefAddHealth) && player.isPotionActive(Potion.getPotionById(Utils.POTION_HBOOST))){
					player.removePotionEffect(Potion.getPotionById(Utils.POTION_HBOOST));
				}
			}	
			
			dropAllItems(player, inv);
			inv.clear();
		}	 
    }
	
	private static void dropAllItems(EntityPlayer player, InventoryEnhanced inventory){
		
		NonNullList<ItemStack> aitemstack = inventory.getStacks();
		for (int i = 0; i < aitemstack.size(); ++i) {
			if (!aitemstack.get(i).isEmpty()) {  	
				player.dropItem(aitemstack.get(i), true, false);
            }
        } 
	}
}
