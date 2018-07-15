package thunder.enhancer.items.defence;

import java.util.Collection;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;

public class ItemDefEffectsRemove extends ItemModifier {

	public ItemDefEffectsRemove(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);
		
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description1", new Object[]{}) + ((1200/NBTItemProvider.getPower(stack)) / 20) + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description2", new Object[]{}));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.DEF_EFFREMOVE_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onInventoryTick(EntityPlayer player, ItemStack stack) {

		if(player.ticksExisted % (1200/NBTItemProvider.getPower(stack)) == 0 && !player.getActivePotionEffects().isEmpty() && NBTItemProvider.canDischarge(stack, Constants.DEF_EFFREMOVE_BASE)){
			
			Collection<PotionEffect> effects = player.getActivePotionEffects();
			if(!effects.isEmpty()){			
				for(PotionEffect eff : effects){				
					if(eff.getPotion().isBadEffect()){					
						effects.remove(eff);
						NBTItemProvider.discharge(stack, Constants.DEF_EFFREMOVE_BASE);
						break;
					}
				}
			}
		}
	}

}
