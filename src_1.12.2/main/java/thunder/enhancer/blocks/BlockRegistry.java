package thunder.enhancer.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class BlockRegistry {
	
	public static Block BlockUltimateCharger;
	
	public static void init(){
		
		BlockUltimateCharger = new BlockUltimateCharger("block_ultimate_charger");
	}
	
	public static void registerRenders(){
		
		registerRenders(BlockUltimateCharger);
	}
	
	public static void registerRenders(Block block){
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

}
