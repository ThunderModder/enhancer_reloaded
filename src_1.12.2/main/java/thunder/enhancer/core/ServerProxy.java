package thunder.enhancer.core;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thunder.enhancer.blocks.BlockRegistry;
import thunder.enhancer.events.CapabilityEventHandler;
import thunder.enhancer.events.EnhancerEventHandler;
import thunder.enhancer.events.StorageEventHandler;
import thunder.enhancer.extended.EnhancedInventory;
import thunder.enhancer.extended.EnhancedInventoryStorage;
import thunder.enhancer.extended.IEnhancedInventory;
import thunder.enhancer.guis.GuiHandler;
import thunder.enhancer.items.ItemRegistry;
import thunder.enhancer.recipes.RecipeRegistry;
import thunder.enhancer.tileentities.TileEntityUltimateCharger;

public class ServerProxy {

	public void preInit() {
		
		BlockRegistry.init();
		ItemRegistry.init();
			
		GameRegistry.registerTileEntity(TileEntityUltimateCharger.class, Reference.MOD_ID + ":block_ultimate_charger");
		
	}
	
	public void init() {
		
		CapabilityManager.INSTANCE.register(IEnhancedInventory.class, new EnhancedInventoryStorage(), EnhancedInventory.class);
		NetworkRegistry.INSTANCE.registerGuiHandler(Enhancer.INSTANCE, new GuiHandler());
		
		RecipeRegistry.init();
		
		StorageEventHandler.register();
		CapabilityEventHandler.register();
		EnhancerEventHandler.register();
	}
	
	public void postInit() {
		
	}
	
}
