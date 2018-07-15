package thunder.enhancer.items.defence;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;
import thunder.enhancer.utils.Utils;

public class ItemDefInvisible extends ItemModifier {

	public ItemDefInvisible(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);
	
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description1", new Object[]{}));
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description2", new Object[]{}) + (5 * NBTItemProvider.getPower(stack)));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.DEF_INVISIBLE_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onInventoryTick(EntityPlayer player, ItemStack stack) {

		if(player.ticksExisted % 35 == 0 && NBTItemProvider.discharge(stack, Constants.DEF_INVISIBLE_BASE)){
			int radius = 5 * NBTItemProvider.getPower(stack);
			AxisAlignedBB box = player.getEntityBoundingBox().expand(radius, radius, radius);
			List<Entity> entities = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, box);
			for(Entity e : entities){
				if(e instanceof EntityLivingBase && e.isInvisible())	{	
					Utils.addPotionEffect((EntityLivingBase)e, Utils.POTION_GLOWING, 0, 20);
				}
			}
		}
	
	}

}
