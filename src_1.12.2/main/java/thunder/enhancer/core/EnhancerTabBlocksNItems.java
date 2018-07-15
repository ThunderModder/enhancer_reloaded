package thunder.enhancer.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import thunder.enhancer.blocks.BlockRegistry;

public class EnhancerTabBlocksNItems extends CreativeTabs {

	public EnhancerTabBlocksNItems() {
		super("Enhancer_BlocksNItems");
	}

	@Override
	public ItemStack getTabIconItem() {

		return new ItemStack(BlockRegistry.BlockUltimateCharger);
	}
	
}