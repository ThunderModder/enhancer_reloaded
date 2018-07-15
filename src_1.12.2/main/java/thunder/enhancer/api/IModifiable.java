package thunder.enhancer.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public interface IModifiable {
	
	public void onInventoryTick(EntityPlayer player, ItemStack stack);
	
	public void onAttack(LivingAttackEvent event, EntityPlayer source, EntityLivingBase target, ItemStack stack);
	
	public void onHurt(LivingHurtEvent event, EntityPlayer target, DamageSource source, ItemStack stack);
	
	public void onDeath(LivingDeathEvent event, EntityPlayer player, DamageSource source, ItemStack stack);
		
	public void onBlockBreak(BreakEvent event, EntityPlayer player, ItemStack stack);
	
	public void onHarvestDrops(HarvestDropsEvent event, EntityPlayer player, ItemStack stack);
	
	public void onItemUse(LivingEntityUseItemEvent.Start event, EntityPlayer player, ItemStack stack);
	
}
