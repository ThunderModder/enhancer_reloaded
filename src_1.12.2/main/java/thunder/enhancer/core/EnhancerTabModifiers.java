package thunder.enhancer.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import thunder.enhancer.items.ItemRegistry;

public class EnhancerTabModifiers extends CreativeTabs {

	public EnhancerTabModifiers() {
		super("Enhancer_Modifiers");
	}

	@Override
	public ItemStack getTabIconItem() {

		return new ItemStack(ItemRegistry.ItemModifierCore);
	}
	
}