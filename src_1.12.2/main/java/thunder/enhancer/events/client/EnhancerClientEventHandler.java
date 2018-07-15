package thunder.enhancer.events.client;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import thunder.enhancer.core.Config;
import thunder.enhancer.core.Enhancer;

public class EnhancerClientEventHandler {
	
	public static void register(){
		MinecraftForge.EVENT_BUS.register(new EnhancerClientEventHandler());
	}
	
	@SubscribeEvent
	public void onUpdateEvent(TickEvent.PlayerTickEvent event) {
		if (Config.canShowUpdates && !Enhancer.wasWarnedNewVersion && event.player.world.isRemote && !Enhancer.checker.isLatestVersion()) {
			if(Enhancer.checker.getLatestVersion().equals("") || Enhancer.checker.getNewVersionURL().equals("") || Enhancer.checker.getChanges().equals("")) {
				event.player.sendMessage(new TextComponentString(I18n.format("checker.message.cant")));
				Enhancer.wasWarnedNewVersion = true;
	        }
	        else {
	            event.player.sendMessage(ForgeHooks.newChatWithLinks(TextFormatting.YELLOW + I18n.format("checker.message.version") + " " + TextFormatting.AQUA + Enhancer.checker.getLatestVersion() + " " + TextFormatting.YELLOW + I18n.format("checker.message.av") + TextFormatting.BLUE + " " +  Enhancer.checker.getNewVersionURL()));
	            event.player.sendMessage(new TextComponentString(""));
	            event.player.sendMessage(new TextComponentString(TextFormatting.YELLOW + I18n.format("checker.message.changes")));
	            List<String> info = Enhancer.checker.getChanges();
	            for (String s : info) {
	            	event.player.sendMessage(new TextComponentString(TextFormatting.GRAY + s));
	            }
	            Enhancer.wasWarnedNewVersion = true;
	        }
	    }

	}

}
