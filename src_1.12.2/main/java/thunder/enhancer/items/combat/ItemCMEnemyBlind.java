package thunder.enhancer.items.combat;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;
import thunder.enhancer.utils.Utils;

public class ItemCMEnemyBlind extends ItemModifier {

	public ItemCMEnemyBlind(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);
	
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description", new Object[]{}));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.CM_ENEMYBLIND_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onAttack(LivingAttackEvent event, EntityPlayer source, EntityLivingBase target, ItemStack stack) {
		
		if(NBTItemProvider.discharge(stack, Constants.CM_ENEMYBLIND_BASE)){
			Utils.addPotionEffect(target, Utils.POTION_BLINDNESS, NBTItemProvider.getPower(stack), 60);
		}
	}

}
