package thunder.enhancer.keyhandler;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import thunder.enhancer.network.NetworkHandler;
import thunder.enhancer.network.OpenInventoryMessage;

public class KeyHandler {

	public static KeyBinding openKey = new KeyBinding("key.enhanced", Keyboard.KEY_H, "key.categories.enhanced");

	public static void register() {		
		MinecraftForge.EVENT_BUS.register(new KeyHandler());
		ClientRegistry.registerKeyBinding(openKey);	
	}	

	 @SubscribeEvent
	 public void onKey(KeyInputEvent event) {
	     if (openKey.isPressed()) {
	         NetworkHandler.network.sendToServer(new OpenInventoryMessage());
	     }
	 }
	 
}

