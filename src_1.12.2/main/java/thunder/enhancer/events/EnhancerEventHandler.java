package thunder.enhancer.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import akka.serialization.Serialization.Information;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import thunder.enhancer.api.IModifiable;
import thunder.enhancer.core.Reference;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;

public class EnhancerEventHandler {
	
	public static void register(){
		MinecraftForge.EVENT_BUS.register(new EnhancerEventHandler());
	}
	
	@SubscribeEvent
    public void onBookUnlock(PlayerEvent.PlayerLoggedInEvent event){
        EntityPlayer player = event.player;
        List<IRecipe> recipe = new ArrayList<>();
        if(!player.world.isRemote){
            for(Map.Entry<ResourceLocation, IRecipe> entry : ForgeRegistries.RECIPES.getEntries()){
                if(entry.getKey().toString().toLowerCase().startsWith(Reference.MOD_ID.toLowerCase())){
                    recipe.add(entry.getValue());
                }
            }
            player.unlockRecipes(recipe);
        }
    }
	
	@SubscribeEvent
	public void onInventoryTick(TickEvent.PlayerTickEvent event) {
		switch (event.phase) {
			case END:
				IEnhancedInventory inv = event.player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
				if(inv != null){
					for(ItemStack stack : inv.getInventory().getStacks()){
						if(!stack.isEmpty() && stack.getItem() instanceof IModifiable)
							((IModifiable)stack.getItem()).onInventoryTick(event.player, stack);
					}
				}
			default:
				break;
		}		
	}
	
	@SubscribeEvent
	public void onAttack(LivingAttackEvent event) {
		
		DamageSource source = event.getSource();
		Entity entity = event.getEntity();
		
		if(source.getTrueSource() instanceof EntityPlayer && entity instanceof EntityLivingBase){
			EntityPlayer psource = (EntityPlayer)source.getTrueSource();
			EntityLivingBase target = (EntityLivingBase)entity;
			IEnhancedInventory inv = psource.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
			if(inv != null){
				for(ItemStack stack : inv.getInventory().getStacks()){
					if(!stack.isEmpty() && stack.getItem() instanceof IModifiable)
						((IModifiable)stack.getItem()).onAttack(event, psource, target, stack);
				}
			}
		}
		
	}
	
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		
		DamageSource source = event.getSource();
		Entity entity = event.getEntity();
		
		if(entity instanceof EntityPlayer){
			EntityPlayer target = (EntityPlayer)entity;
			
			IEnhancedInventory inv = target.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
			if(inv != null){
				for(ItemStack stack : inv.getInventory().getStacks()){
					if(!stack.isEmpty() && stack.getItem() instanceof IModifiable)
						((IModifiable)stack.getItem()).onHurt(event, target, source, stack);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onBlockHarvestDrops(HarvestDropsEvent event) {
		
		if(event.getHarvester() != null){
			EntityPlayer player = (EntityPlayer)event.getHarvester();
			IEnhancedInventory inv = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
			if(inv != null){
				for(ItemStack stack : inv.getInventory().getStacks()){
					if(!stack.isEmpty() && stack.getItem() instanceof IModifiable)
						((IModifiable)stack.getItem()).onHarvestDrops(event, player, stack);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onItemUseEvent(LivingEntityUseItemEvent.Start event) {
		
		Entity entity = event.getEntity();
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)entity;
			IEnhancedInventory inv = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
			if(inv != null){
				for(ItemStack stack : inv.getInventory().getStacks()){
					if(!stack.isEmpty() && stack.getItem() instanceof IModifiable)
						((IModifiable)stack.getItem()).onItemUse(event, player, stack);
				}
			}
		}
	}
}
