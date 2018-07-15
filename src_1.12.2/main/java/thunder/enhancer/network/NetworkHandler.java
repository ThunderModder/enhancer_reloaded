package thunder.enhancer.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import thunder.enhancer.core.Reference;

public class NetworkHandler {
	
	private static byte packetId = 0;

	public static SimpleNetworkWrapper network;

	public static void init() {
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		network.registerMessage(OpenInventoryMessage.Handler.class, OpenInventoryMessage.class, 0, Side.SERVER);
		network.registerMessage(HUDSyncRequestMessage.Handler.class, HUDSyncRequestMessage.class, 1, Side.SERVER);
		network.registerMessage(HUDSyncMessage.Handler.class, HUDSyncMessage.class, 2, Side.CLIENT);
		network.registerMessage(LightningBoltMessage.Handler.class, LightningBoltMessage.class, 3, Side.CLIENT);

	}
	
}
