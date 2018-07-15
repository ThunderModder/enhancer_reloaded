package thunder.enhancer.core;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import thunder.enhancer.blocks.BlockRegistry;
import thunder.enhancer.core.updates.VersionChecker;
import thunder.enhancer.events.client.EnhancerClientEventHandler;
import thunder.enhancer.guis.GuiHUD;
import thunder.enhancer.items.ItemRegistry;
import thunder.enhancer.keyhandler.KeyHandler;
import thunder.enhancer.renderers.TESRUltimateCharger;
import thunder.enhancer.tileentities.TileEntityUltimateCharger;

public class ClientProxy extends ServerProxy {

	@Override
	public void preInit() {
		
	    super.preInit();
	}

	@Override
	public void init() {
		
		BlockRegistry.registerRenders();
		ItemRegistry.registerRenders();
		
		EnhancerClientEventHandler.register();
					
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUltimateCharger.class, new TESRUltimateCharger());
		
		KeyHandler.register();
		GuiHUD.register();
		
		super.init();
	}

	@Override
	public void postInit() {
		if(Config.canShowUpdates) {
            Enhancer.checker = new VersionChecker();
            Thread versionCheckThread = new Thread(Enhancer.checker, "Enhancer Version Check Thread");
            versionCheckThread.start();
        }
		super.postInit();
	}
}
