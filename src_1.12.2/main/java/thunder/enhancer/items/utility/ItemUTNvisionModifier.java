package thunder.enhancer.items.utility;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;
import thunder.enhancer.utils.Utils;

public class ItemUTNvisionModifier extends ItemModifier {
	
	public ItemUTNvisionModifier(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);	
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description", new Object[]{}));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.UT_NVISION_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onInventoryTick(EntityPlayer player, ItemStack stack) {

		if(!player.getEntityWorld().isRemote){
			if((!player.getEntityWorld().isDaytime() || player.getEntityWorld().getLightBrightness(new BlockPos(player)) < 0.5) && NBTItemProvider.discharge(stack, Constants.UT_NVISION_BASE)){
				Utils.addPotionEffect(player, Utils.POTION_NIGHTV, NBTItemProvider.getPower(stack));
			}else {
				player.removePotionEffect(Potion.getPotionById(Utils.POTION_NIGHTV));
			}
		}
	}
	
}