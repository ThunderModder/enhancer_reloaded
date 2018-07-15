package thunder.enhancer.items.defence;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.TextFormatting;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;
import thunder.enhancer.utils.Utils;

public class ItemDefPoisoning extends ItemModifier {

	public ItemDefPoisoning(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);
		
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description", new Object[]{}));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.DEF_POISON_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onInventoryTick(EntityPlayer player, ItemStack stack) {

		if(player.isPotionActive(Potion.getPotionById(Utils.POTION_POISON)) && NBTItemProvider.discharge(stack, Constants.DEF_POISON_BASE)){
			player.removePotionEffect(Potion.getPotionById(Utils.POTION_POISON));
		}
	
	}


}
