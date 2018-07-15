package thunder.enhancer.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import thunder.enhancer.core.Enhancer;

public class ItemStandartEnhancer extends Item {

	public ItemStandartEnhancer(String unlocalizedName) {

		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setMaxStackSize(64);
		
		this.setCreativeTab(Enhancer.ENHANCER_TAB_BLOCKSNITEMS);
		ForgeRegistries.ITEMS.register(this);
	}
}