package thunder.enhancer.items.defence;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;
import thunder.enhancer.utils.Utils;

public class ItemDefDamageBlock extends ItemModifier {

	public ItemDefDamageBlock(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);
		
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description1", new Object[]{}) + (10 * NBTItemProvider.getPower(stack)) + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description2", new Object[]{}));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.DEF_DAMAGEBLOCK_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onHurt(LivingHurtEvent event, EntityPlayer target, DamageSource source, ItemStack stack){
		
		if(Utils.getRandom(10 * NBTItemProvider.getPower(stack)) && NBTItemProvider.discharge(stack, Constants.DEF_DAMAGEBLOCK_BASE)){	
			Utils.SendProgressToClient(target);
			event.setCanceled(true);
		}
	}
}
