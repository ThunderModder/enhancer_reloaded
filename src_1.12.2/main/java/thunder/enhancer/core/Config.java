package thunder.enhancer.core;

import net.minecraftforge.common.config.Configuration;

public class Config {

	//main config
    public static boolean canShowUpdates;
	
	public static void init(){
		
		Configuration config = Enhancer.getConfig();
		config.load();
			
		//main
        canShowUpdates = config.getBoolean("Show Updates", "Main configurations", true, "If true, Enhancer Reloaded will show available updates in chat.");
     
		
		if (config.hasChanged()) config.save();
	}
}
