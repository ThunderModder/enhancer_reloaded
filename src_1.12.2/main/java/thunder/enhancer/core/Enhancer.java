package thunder.enhancer.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thunder.enhancer.core.updates.VersionChecker;
import thunder.enhancer.network.NetworkHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Enhancer {
		
   @Mod.Instance(Reference.MOD_ID)
   public static Enhancer INSTANCE;
   
   @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
   public static ServerProxy proxy;
   
   public static VersionChecker checker;
   public static boolean wasWarnedNewVersion = false;
   
   public static final CreativeTabs ENHANCER_TAB_BLOCKSNITEMS = new EnhancerTabBlocksNItems();
   public static final CreativeTabs ENHANCER_TAB_MODIFIERS = new EnhancerTabModifiers();
    
   public static Configuration config;
   
   @EventHandler
   public void preInit(FMLPreInitializationEvent e) {
	   
	   config = null;
	   config = new Configuration(e.getSuggestedConfigurationFile(), Reference.VERSION, true);
	   Config.init();
	   
	   NetworkHandler.init();
	   	   
	   proxy.preInit();
	   
   }

   @EventHandler
   public void init(FMLInitializationEvent e) {

	   proxy.init();
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent e) {

	   proxy.postInit();
   }
   
   public static Configuration getConfig() {

	   return config;
   }
   
}