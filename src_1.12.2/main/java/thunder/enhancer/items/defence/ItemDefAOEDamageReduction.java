package thunder.enhancer.items.defence;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;
import thunder.enhancer.utils.Utils;

public class ItemDefAOEDamageReduction extends ItemModifier {

	public ItemDefAOEDamageReduction(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);
		
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description1", new Object[]{}));
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description2", new Object[]{}) + (5 * NBTItemProvider.getPower(stack)));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.DEF_AOER_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onHurt(LivingHurtEvent event, EntityPlayer target, DamageSource source, ItemStack stack){
		
		if(NBTItemProvider.canDischarge(stack, Constants.DEF_AOER_BASE)){
			
			float damage = event.getAmount();
			if(damage >= 0){
				int radius = 5 * NBTItemProvider.getPower(stack);
				AxisAlignedBB box = target.getEntityBoundingBox().expand(radius, radius, radius);
				List<Entity> entities = target.getEntityWorld().getEntitiesWithinAABBExcludingEntity(target, box);
				int eCount = 0;
				for(Entity e : entities){
					if(e instanceof EntityLivingBase)
						eCount++;
				}
				if(eCount > 0){
					float toDamage = damage/eCount;
					for(Entity e : entities){
						if(e instanceof EntityLivingBase)
							e.attackEntityFrom(DamageSource.GENERIC, toDamage);
					}
					NBTItemProvider.discharge(stack, Constants.DEF_AOER_BASE);
					Utils.SendProgressToClient(target);
					event.setCanceled(true);
				}
			}
		}
	}

}
