package thunder.enhancer.recipes;

import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thunder.enhancer.blocks.BlockRegistry;
import thunder.enhancer.items.ItemDiamondGrinder;
import thunder.enhancer.items.ItemRegistry;
import thunder.enhancer.items.ItemWeatherWand;
import thunder.enhancer.maps.Modifiers;

public class RecipeRegistry {
		
	public static void init(){
		
		
		//crafting
		//red alloy
		FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(Items.REDSTONE, 2), new ItemStack(ItemRegistry.ItemRedstoneAlloy, 1), 0.2f);
		//flint plate
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:FlintPlate"), new ResourceLocation("enhancer:FlintPlate"), new ItemStack(ItemRegistry.ItemFlintPlate),  new Object []{"F  ", " G ", "   ", ('F'), Items.FLINT, ('G'), ItemRegistry.ItemDiamondGrinder});
	
		//circuits
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:RedstoneCircuit"), new ResourceLocation("enhancer:RedstoneCircuit"),new ItemStack(ItemRegistry.ItemRedstoneCircuit),  new Object []{"RAR", "APA", "RAR", ('P'), ItemRegistry.ItemFlintPlate, ('A'), ItemRegistry.ItemRedstoneAlloy, ('R'), Items.REDSTONE});	
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:LapisCircuit"), new ResourceLocation("enhancer:LapisCircuit"),new ItemStack(ItemRegistry.ItemLapisCircuit),  new Object []{"LAL", "APA", "LAL", ('P'), ItemRegistry.ItemFlintPlate, ('A'), ItemRegistry.ItemRedstoneAlloy, ('L'), new ItemStack(Items.DYE, 1, 4)});	
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:DiamondCircuit"), new ResourceLocation("enhancer:DiamondCircuit"),new ItemStack(ItemRegistry.ItemDiamondCircuit),  new Object []{"DAD", "APA", "DAD", ('P'), ItemRegistry.ItemFlintPlate, ('A'), ItemRegistry.ItemRedstoneAlloy, ('D'), Items.DIAMOND});	
		
		//grinder
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:DiamondGrinder"), new ResourceLocation("enhancer:DiamondGrinder"),ItemDiamondGrinder.getNewDiamondGrinder(),  new Object []{"CCC", " C ", " D ", ('C'), Blocks.COBBLESTONE, ('D'), Items.DIAMOND});

		//programmer
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:Programmer"), new ResourceLocation("enhancer:Programmer"),new ItemStack(ItemRegistry.ItemProgrammer),  new Object []{"IGI", "GLG", "IGI", ('I'), ItemRegistry.ItemRedstoneAlloy, ('G'), Items.GOLD_INGOT, ('L'), ItemRegistry.ItemLapisCircuit});

		//modifier core
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:ModifierCore"), new ResourceLocation("enhancer:ModifierCore"),new ItemStack(ItemRegistry.ItemModifierCore),  new Object []{" L ", "LCL", " P ", ('C'), ItemRegistry.ItemStandartCapacityModule, ('L'), ItemRegistry.ItemLapisCircuit, ('P'), ItemRegistry.ItemProgrammer});

		//power module
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:StandartPowerModule"), new ResourceLocation("enhancer:StandartPowerModule"),new ItemStack(ItemRegistry.ItemStandartPowerModule),  new Object []{"DRD", "RCR", "DRD", ('R'), Items.REDSTONE, ('C'), ItemRegistry.ItemRedstoneCircuit, ('D'), Items.DIAMOND});

		//capacity module
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:StandartCapacityModule"), new ResourceLocation("enhancer:StandartCapacityModule"),new ItemStack(ItemRegistry.ItemStandartCapacityModule),  new Object []{" G ", "GDG", " G ", ('G'), Items.GOLD_INGOT, ('D'), ItemRegistry.ItemDiamondCircuit});
	
		//power upgrade
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:PowerUpgrade"), new ResourceLocation("enhancer:PowerUpgrade"),new ItemStack(ItemRegistry.ItemPowerUpgrade),  new Object []{"RIR", "IMI", "RIR", ('I'), Items.IRON_INGOT, ('M'), ItemRegistry.ItemStandartPowerModule, ('R'), ItemRegistry.ItemRedstoneCircuit});
	
		//capacity upgrade
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:CapacityUpgrade"), new ResourceLocation("enhancer:CapacityUpgrade"),new ItemStack(ItemRegistry.ItemCapacityUpgrade),  new Object []{"RIR", "IMI", "RIR", ('I'), Items.IRON_INGOT, ('M'), ItemRegistry.ItemStandartCapacityModule, ('R'), ItemRegistry.ItemRedstoneCircuit});
	
		//item magnet
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:Magnet"), new ResourceLocation("enhancer:Magnet"),new ItemStack(ItemRegistry.ItemMagnet),  new Object []{" R ", "RIR", " R ", ('I'), Items.IRON_INGOT, ('R'), Items.REDSTONE});

		//ultimate charger
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:UltimateCharger"), new ResourceLocation("enhancer:UltimateCharger"),new ItemStack(BlockRegistry.BlockUltimateCharger),  new Object []{"DCD", "RFR", "DCD", ('D'), Items.DIAMOND, ('C'), ItemRegistry.ItemStandartCapacityModule, ('R'), ItemRegistry.ItemRedstoneCircuit, ('F'), Blocks.FURNACE});
		
		//weather wand
		GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:WeatherWand"), new ResourceLocation("enhancer:WeatherWand"),ItemWeatherWand.getNewWeatherWandDischarged(),  new Object []{" SD", " RS", "C  ", ('D'), Items.DIAMOND, ('S'), Items.REDSTONE, ('R'), Items.BLAZE_ROD, ('C'), ItemRegistry.ItemStandartCapacityModule});
	
		//modifiers
		for(Entry<ItemStack [], Integer> entry : Modifiers.getInstance().getMap().entrySet()){
			
			GameRegistry.addShapedRecipe(new ResourceLocation("enhancer:" + entry.getKey()[0].copy().getUnlocalizedName().substring(5)), new ResourceLocation("enhancer:" + entry.getKey()[0].copy().getUnlocalizedName().substring(5)), Modifiers.getInstance().getModifierWithType(entry.getKey()[0].copy()), new Object []{"LAL", "DMB", "LCL", ('A'), entry.getKey()[1].copy(), ('B'), entry.getKey()[2].copy(), ('C'), entry.getKey()[3].copy(), ('D'), entry.getKey()[4].copy(), ('M'), ItemRegistry.ItemModifierCore, ('L'), entry.getKey()[5].copy()});
		}		
	}

}
