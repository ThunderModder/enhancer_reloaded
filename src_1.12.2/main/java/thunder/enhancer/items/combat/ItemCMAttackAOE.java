package thunder.enhancer.items.combat;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;

public class ItemCMAttackAOE extends ItemModifier {

	public ItemCMAttackAOE(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);
	
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description1", new Object[]{}));
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description2", new Object[]{}) + (5 * NBTItemProvider.getPower(stack)));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.CM_ATTACKAOE_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onAttack(LivingAttackEvent event, EntityPlayer source, EntityLivingBase target, ItemStack stack) {
		
		if(NBTItemProvider.discharge(stack, Constants.CM_ATTACKAOE_BASE)){
			float damage = event.getAmount();
			int radius = 5 * NBTItemProvider.getPower(stack);
			AxisAlignedBB box = target.getEntityBoundingBox().expand(radius, radius, radius);
			List<Entity> entities = target.getEntityWorld().getEntitiesWithinAABBExcludingEntity(target, box);
			for(Entity e : entities){
				if(e instanceof EntityLivingBase && e != source)
					e.attackEntityFrom(DamageSource.GENERIC, damage * 0.2f);
			}
		}
	}

}
