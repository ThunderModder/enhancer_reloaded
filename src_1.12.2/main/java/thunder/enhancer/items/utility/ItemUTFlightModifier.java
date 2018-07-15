package thunder.enhancer.items.utility;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;

public class ItemUTFlightModifier extends ItemModifier {
	
	public ItemUTFlightModifier(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);	
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description", new Object[]{}));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.UT_FLIGHT_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onInventoryTick(EntityPlayer player, ItemStack stack) {

		if(NBTItemProvider.canDischarge(stack, Constants.UT_FLIGHT_BASE)){
			if(!player.capabilities.allowFlying){
				player.capabilities.allowFlying = true;
			}
			if(player.capabilities.isFlying){
				NBTItemProvider.discharge(stack, Constants.UT_FLIGHT_BASE);
			}
		} else if(!player.capabilities.isCreativeMode && player.capabilities.allowFlying){
			player.capabilities.allowFlying = false;
			if(player.capabilities.isFlying)
				player.capabilities.isFlying = false;
		}
	}
	
}