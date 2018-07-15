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

public class ItemCMAttackHPReplacement extends ItemModifier {

	public ItemCMAttackHPReplacement(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);
	
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description", new Object[]{}));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.CM_ATTACKHPREPLACEMENT_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onAttack(LivingAttackEvent event, EntityPlayer source, EntityLivingBase target, ItemStack stack) {
		
		if(NBTItemProvider.discharge(stack, Constants.CM_ATTACKHPREPLACEMENT_BASE)){
			float source_hp = source.getHealth();
			float target_hp = target.getHealth();
			
			float source_maxhp = source.getMaxHealth();
			float target_maxhp = target.getMaxHealth();
			
			if(source_hp > 0 && target_hp > 0){
				if(target_hp > source_maxhp)
					source.setHealth(source_maxhp);
				else source.setHealth(target_hp);

				if(source_maxhp > target_hp)
					target.setHealth(target_maxhp);
				else target.setHealth(source_hp);
			}
			
		}
	}

}
