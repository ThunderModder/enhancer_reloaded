package thunder.enhancer.items.utility;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;

public class ItemUTMagnetModifier extends ItemModifier {
	
	public ItemUTMagnetModifier(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);	
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description1", new Object[]{}));
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description2", new Object[]{}) + (NBTItemProvider.getPower(stack) * 5));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.UT_MAGNET_BASE * NBTItemProvider.getPower(stack) + " LT");
    }

	@Override
	public void onInventoryTick(EntityPlayer player, ItemStack stack) {

		if(NBTItemProvider.discharge(stack, Constants.UT_MAGNET_BASE)){
			int radius = NBTItemProvider.getPower(stack) * 5;
			AxisAlignedBB box = player.getEntityBoundingBox().expand(radius, radius, radius);
			List<Entity> entities = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, box);
			if(!entities.isEmpty()){
				for(Entity e : entities){
					if(e instanceof EntityItem){
						EntityItem item = (EntityItem)e;
						double d1 = (player.posX - item.posX) / radius;
				        double d2 = (player.posY + (double)player.getEyeHeight() / 2.0D - item.posY) / radius;
				        double d3 = (player.posZ - item.posZ) / radius;
				        double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
				        double d5 = 1.0D - d4;

				        if (d5 > 0.0D) {
				        	
				            item.motionX += d1 / d4 * d5 * 0.1D;
				            item.motionY += d2 / d4 * d5 * 0.1D;
				            item.motionZ += d3 / d4 * d5 * 0.1D;
				        }
				         
				        item.move(MoverType.SELF, item.motionX, item.motionY, item.motionZ);
				        float f = 0.98F;

				        if (item.onGround) {
				        	 
				            f = item.world.getBlockState(new BlockPos(MathHelper.floor(item.posX), MathHelper.floor(item.getEntityBoundingBox().minY) - 1, MathHelper.floor(item.posZ))).getBlock().slipperiness * 0.98F;
				        }

				        item.motionX *= (double)f;
				        item.motionY *= 0.9800000190734863D;
				        item.motionZ *= (double)f;

				        if (item.onGround) {
				        	 
				        	item.motionY *= -0.8999999761581421D;
				        }
					}
				}
			}
		}
	
	}
	
}
