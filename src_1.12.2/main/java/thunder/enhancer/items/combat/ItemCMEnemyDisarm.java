package thunder.enhancer.items.combat;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import thunder.enhancer.api.IModifiable;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.items.defence.ItemDefAntiDisarm;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;
import thunder.enhancer.utils.Utils;

public class ItemCMEnemyDisarm extends ItemModifier {

	public ItemCMEnemyDisarm(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);
	
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description1", new Object[]{}) + (5 * NBTItemProvider.getPower(stack)) + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description2", new Object[]{}));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.CM_ENEMYDISARM_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onAttack(LivingAttackEvent event, EntityPlayer source, EntityLivingBase target, ItemStack stack) {
		
		if(Utils.getRandom(5 * NBTItemProvider.getPower(stack)) && NBTItemProvider.canDischarge(stack, Constants.CM_ENEMYDISARM_BASE)){
			if(target instanceof EntityPlayer){	
				EntityPlayer player = (EntityPlayer)target;
				boolean flag = false;
				IEnhancedInventory inv = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
				if(inv != null){
					for(ItemStack st : inv.getInventory().getStacks()){
						if(!st.isEmpty() && stack.getItem() instanceof IModifiable && stack.getItem() instanceof ItemDefAntiDisarm){
							if(NBTItemProvider.discharge(st, Constants.DEF_ADISARM_BASE)){
								flag = true;
							}
						}
							
					}
				}	
				if(!flag){
					if(!player.getEntityWorld().isRemote)
						player.dropItem(true);
					NBTItemProvider.discharge(stack, Constants.CM_ENEMYDISARM_BASE);
				}
			}else if(target instanceof EntityMob){
				EntityMob mob = (EntityMob)target;
				if(!mob.getHeldItemMainhand().isEmpty()){
					if(!target.getEntityWorld().isRemote)
						mob.entityDropItem(mob.getHeldItemMainhand(), 0);
					mob.setHeldItem(EnumHand.MAIN_HAND, null);
					mob.setAttackTarget(source);
					NBTItemProvider.discharge(stack, Constants.CM_ENEMYDISARM_BASE);
				}
			}
		}
	}

}
