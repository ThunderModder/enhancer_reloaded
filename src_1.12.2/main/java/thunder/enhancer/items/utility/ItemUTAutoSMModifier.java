package thunder.enhancer.items.utility;

import java.util.List;
import java.util.Random;

import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;
import thunder.enhancer.utils.Utils;

public class ItemUTAutoSMModifier extends ItemModifier {
	
	public ItemUTAutoSMModifier(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);	
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description", new Object[]{}));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.UT_AUTOSM_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onHarvestDrops(HarvestDropsEvent event, EntityPlayer player, ItemStack stack) {
		 
		if(NBTItemProvider.canDischarge(stack, Constants.UT_AUTOSM_BASE)){
			boolean flag = false;
			if (!event.isSilkTouching()) {			
				if (!player.getHeldItemMainhand().isEmpty()) {				
					ItemStack tool = player.getHeldItemMainhand();
					Random random = Utils.getRandom();				
					if (tool.getItem() instanceof ItemTool) {					
						int z = EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.getEnchantmentByID(35), player);
						ItemStack result = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock(), 1, event.getState().getBlock().damageDropped(event.getState())));					
						if (!result.isEmpty()) {				
							result.setCount(z > 0 ? random.nextInt(z) + 1 : 1);
							tool.damageItem(1, player);
							event.getDrops().clear();
							event.getDrops().add(result.copy());
							flag = true;
						}
					}
				}
			}
			if(flag)
				NBTItemProvider.discharge(stack, Constants.UT_AUTOSM_BASE);	
		}
	}
	
}