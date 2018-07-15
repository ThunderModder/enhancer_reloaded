package thunder.enhancer.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import thunder.enhancer.items.combat.ItemCMAOELightningStrike;
import thunder.enhancer.items.combat.ItemCMAntiArmor;
import thunder.enhancer.items.combat.ItemCMAttackAOE;
import thunder.enhancer.items.combat.ItemCMAttackAbsorption;
import thunder.enhancer.items.combat.ItemCMAttackHPReplacement;
import thunder.enhancer.items.combat.ItemCMAttackHeal;
import thunder.enhancer.items.combat.ItemCMAttackJump;
import thunder.enhancer.items.combat.ItemCMAttackMagicDamage;
import thunder.enhancer.items.combat.ItemCMAttackSpeed;
import thunder.enhancer.items.combat.ItemCMAttackStrength;
import thunder.enhancer.items.combat.ItemCMAttackVampirism;
import thunder.enhancer.items.combat.ItemCMEnemyBlind;
import thunder.enhancer.items.combat.ItemCMEnemyBurn;
import thunder.enhancer.items.combat.ItemCMEnemyDisarm;
import thunder.enhancer.items.combat.ItemCMEnemyExplosion;
import thunder.enhancer.items.combat.ItemCMEnemyHunger;
import thunder.enhancer.items.combat.ItemCMEnemyLevitation;
import thunder.enhancer.items.combat.ItemCMEnemyNausea;
import thunder.enhancer.items.combat.ItemCMEnemyPoison;
import thunder.enhancer.items.combat.ItemCMEnemyRandomTeleport;
import thunder.enhancer.items.combat.ItemCMEnemySlow;
import thunder.enhancer.items.combat.ItemCMEnemySlowdown;
import thunder.enhancer.items.combat.ItemCMEnemyUnluck;
import thunder.enhancer.items.combat.ItemCMEnemyWither;
import thunder.enhancer.items.combat.ItemCMKillOrDie;
import thunder.enhancer.items.defence.ItemDefAOEDamageReduction;
import thunder.enhancer.items.defence.ItemDefAbsoption;
import thunder.enhancer.items.defence.ItemDefAddHealth;
import thunder.enhancer.items.defence.ItemDefAntiDisarm;
import thunder.enhancer.items.defence.ItemDefArrow;
import thunder.enhancer.items.defence.ItemDefAttackInvisible;
import thunder.enhancer.items.defence.ItemDefCactusEffect;
import thunder.enhancer.items.defence.ItemDefDamageBlock;
import thunder.enhancer.items.defence.ItemDefEffectsRemove;
import thunder.enhancer.items.defence.ItemDefExplosion;
import thunder.enhancer.items.defence.ItemDefExtinguish;
import thunder.enhancer.items.defence.ItemDefFalling;
import thunder.enhancer.items.defence.ItemDefFireResistance;
import thunder.enhancer.items.defence.ItemDefHPHeal;
import thunder.enhancer.items.defence.ItemDefInvisible;
import thunder.enhancer.items.defence.ItemDefLuck;
import thunder.enhancer.items.defence.ItemDefMagic;
import thunder.enhancer.items.defence.ItemDefPoisoning;
import thunder.enhancer.items.defence.ItemDefProtection;
import thunder.enhancer.items.defence.ItemDefSaveInventory;
import thunder.enhancer.items.utility.ItemUTAutoSMModifier;
import thunder.enhancer.items.utility.ItemUTFlightModifier;
import thunder.enhancer.items.utility.ItemUTInvModifier;
import thunder.enhancer.items.utility.ItemUTJumpModifier;
import thunder.enhancer.items.utility.ItemUTLevitationModifier;
import thunder.enhancer.items.utility.ItemUTLwModifier;
import thunder.enhancer.items.utility.ItemUTMagnetModifier;
import thunder.enhancer.items.utility.ItemUTNvisionModifier;
import thunder.enhancer.items.utility.ItemUTRegenModifier;
import thunder.enhancer.items.utility.ItemUTSaturModifier;
import thunder.enhancer.items.utility.ItemUTSpeedModifier;
import thunder.enhancer.items.utility.ItemUTWSModifier;
import thunder.enhancer.items.utility.ItemUTWbModifier;
import thunder.enhancer.items.utility.ItemUTWwModifier;

public class ItemRegistry {
	
	//Modifiers
	//Utilities
	public static ItemModifier ItemUTSpeedModifier;
	public static ItemModifier ItemUTJumpModifier;
	public static ItemModifier ItemUTLevitationModifier;
	public static ItemModifier ItemUTInvModifier;
	public static ItemModifier ItemUTRegenModifier;
	public static ItemModifier ItemUTMagnetModifier;
	public static ItemModifier ItemUTWbModifier;
	public static ItemModifier ItemUTNvisionModifier;
	public static ItemModifier ItemUTFlightModifier;
	public static ItemModifier ItemUTWSModifier;
	public static ItemModifier ItemUTSaturModifier;
	public static ItemModifier ItemUTWwModifier;
	public static ItemModifier ItemUTLwModifier;
	public static ItemModifier ItemUTAutoSMModifier;
	//Combat
	public static ItemModifier ItemCMEnemyBurn;
	public static ItemModifier ItemCMEnemyPoison;
	public static ItemModifier ItemCMEnemyWither;
	public static ItemModifier ItemCMEnemyBlind;
	public static ItemModifier ItemCMEnemySlow;
	public static ItemModifier ItemCMAttackStrength;
	public static ItemModifier ItemCMAttackJump;
	public static ItemModifier ItemCMAttackSpeed;
	public static ItemModifier ItemCMEnemyDisarm;
	public static ItemModifier ItemCMEnemyExplosion;
	public static ItemModifier ItemCMEnemyUnluck;
	public static ItemModifier ItemCMEnemyHunger;
	public static ItemModifier ItemCMKillOrDie;
	public static ItemModifier ItemCMAttackHeal;
	public static ItemModifier ItemCMAttackVampirism;
	public static ItemModifier ItemCMAttackMagicDamage;
	public static ItemModifier ItemCMAttackAbsorption;
	public static ItemModifier ItemCMAttackAOE;
	public static ItemModifier ItemCMAntiArmor;
	public static ItemModifier ItemCMEnemySlowdown;
	public static ItemModifier ItemCMEnemyNausea;
	public static ItemModifier ItemCMAttackHPReplacement;
	public static ItemModifier ItemCMAOELightningStrike;
	public static ItemModifier ItemCMEnemyRandomTeleport;
	public static ItemModifier ItemCMEnemyLevitation;
	//defence
	public static ItemModifier ItemDefFalling;
	public static ItemModifier ItemDefPoisoning;
	public static ItemModifier ItemDefMagic;
	public static ItemModifier ItemDefCactusEffect;
	public static ItemModifier ItemDefInvisible;
	public static ItemModifier ItemDefFireResistance;
	public static ItemModifier ItemDefAntiDisarm;
	public static ItemModifier ItemDefArrow;
	public static ItemModifier ItemDefExtinguish;
	public static ItemModifier ItemDefHPHeal;
	public static ItemModifier ItemDefExplosion;
	public static ItemModifier ItemDefLuck;
	public static ItemModifier ItemDefAOEDamageReduction;
	public static ItemModifier ItemDefProtection;
	public static ItemModifier ItemDefDamageBlock;
	public static ItemModifier ItemDefAttackInvisible;
	public static ItemModifier ItemDefAbsoption;
	public static ItemModifier ItemDefAddHealth;
	public static ItemModifier ItemDefEffectsRemove;
	public static ItemModifier ItemDefSaveInventory;

	
	//components
	public static ItemStandartEnhancer ItemPowerUpgrade;
	public static ItemStandartEnhancer ItemCapacityUpgrade;
	
	public static ItemStandartEnhancer ItemFlintPlate;
	
	public static ItemStandartEnhancer ItemDiamondCircuit;
	public static ItemStandartEnhancer ItemRedstoneCircuit;
	public static ItemStandartEnhancer ItemLapisCircuit;
	
	public static ItemStandartEnhancer ItemModifierCore;
	public static ItemStandartEnhancer ItemStandartPowerModule;
	public static ItemStandartEnhancer ItemStandartCapacityModule;
	
	public static ItemStandartEnhancer ItemProgrammer;
	
	public static Item ItemDiamondGrinder;
	public static Item ItemWeatherWand;
	
	public static ItemStandartEnhancer ItemRedstoneAlloy;
	public static ItemStandartEnhancer ItemMagnet;
		
	//decorative
	public static ItemStandartEnhancer ItemLightningShard;
	
	public static void init() {
		
		
		ItemUTSpeedModifier = new ItemUTSpeedModifier("item_ut_speedmodifier", EnumModifierType.UTILITY);//+//
		ItemUTJumpModifier = new ItemUTJumpModifier("item_ut_jumpmodifier", EnumModifierType.UTILITY);//+//
		ItemUTLevitationModifier = new ItemUTLevitationModifier("item_ut_levitationmodifier", EnumModifierType.UTILITY);//+//
		ItemUTInvModifier = new ItemUTInvModifier("item_ut_invmodifier", EnumModifierType.UTILITY);//+//
		ItemUTRegenModifier = new ItemUTRegenModifier("item_ut_regenmodifier", EnumModifierType.UTILITY);//+//
		ItemUTMagnetModifier = new ItemUTMagnetModifier("item_ut_magnetmodifier", EnumModifierType.UTILITY);//+//
		ItemUTWbModifier = new ItemUTWbModifier("item_ut_wbmodifier", EnumModifierType.UTILITY);//+//
		ItemUTNvisionModifier = new ItemUTNvisionModifier("item_ut_nvisionmodifier", EnumModifierType.UTILITY);//+//
		ItemUTFlightModifier = new ItemUTFlightModifier("item_ut_flightmodifier", EnumModifierType.UTILITY);//+//
		ItemUTWSModifier = new ItemUTWSModifier("item_ut_wsmodifier", EnumModifierType.UTILITY);//+//
		ItemUTSaturModifier = new ItemUTSaturModifier("item_ut_saturmodifier", EnumModifierType.UTILITY);//+//
		ItemUTWwModifier = new ItemUTWwModifier("item_ut_wwmodifier", EnumModifierType.UTILITY);//+//
		ItemUTLwModifier = new ItemUTLwModifier("item_ut_lwmodifier", EnumModifierType.UTILITY);//+//
		ItemUTAutoSMModifier = new ItemUTAutoSMModifier("item_ut_autosmmodifier", EnumModifierType.UTILITY);//+//
		
		ItemCMEnemyBurn = new ItemCMEnemyBurn("item_cm_eburnmodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemyPoison = new ItemCMEnemyPoison("item_cm_epoisonmodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemyWither = new ItemCMEnemyWither("item_cm_ewithermodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemyBlind = new ItemCMEnemyBlind("item_cm_eblindmodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemySlow = new ItemCMEnemySlow("item_cm_eslowmodifier", EnumModifierType.COMBAT);//+//
		ItemCMAttackStrength = new ItemCMAttackStrength("item_cm_astrengthmodifier", EnumModifierType.COMBAT);//+//
		ItemCMAttackJump = new ItemCMAttackJump("item_cm_ajumpmodifier", EnumModifierType.COMBAT);//+//
		ItemCMAttackSpeed = new ItemCMAttackSpeed("item_cm_aspeedmodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemyDisarm = new ItemCMEnemyDisarm("item_cm_edisarmmodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemyExplosion = new ItemCMEnemyExplosion("item_cm_eexplosionmodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemyUnluck = new ItemCMEnemyUnluck("item_cm_eunluckmodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemyHunger = new ItemCMEnemyHunger("item_cm_ehungermodifier", EnumModifierType.COMBAT);//+//
		ItemCMKillOrDie = new ItemCMKillOrDie("item_cm_killordiemodifier", EnumModifierType.COMBAT);//+//
		ItemCMAttackHeal = new ItemCMAttackHeal("item_cm_ahealmodifier", EnumModifierType.COMBAT);//+//
		ItemCMAttackVampirism = new ItemCMAttackVampirism("item_cm_avampirismmodifier", EnumModifierType.COMBAT);//+//
		ItemCMAttackMagicDamage = new ItemCMAttackMagicDamage("item_cm_amagicdamagemodifier", EnumModifierType.COMBAT);//+//
		ItemCMAttackAbsorption = new ItemCMAttackAbsorption("item_cm_aabsorptionmodifier", EnumModifierType.COMBAT);//+//
		ItemCMAttackAOE = new ItemCMAttackAOE("item_cm_aaoemodifier", EnumModifierType.COMBAT);//+//
		ItemCMAntiArmor = new ItemCMAntiArmor("item_cm_aarmormodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemySlowdown = new ItemCMEnemySlowdown("item_cm_eslowdownmodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemyNausea = new ItemCMEnemyNausea("item_cm_enauseamodifier", EnumModifierType.COMBAT);//+//
		ItemCMAttackHPReplacement = new ItemCMAttackHPReplacement("item_cm_ahpreplacementmodifier", EnumModifierType.COMBAT);//+//
		ItemCMAOELightningStrike = new ItemCMAOELightningStrike("item_cm_aoelightningmodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemyRandomTeleport = new ItemCMEnemyRandomTeleport("item_cm_erandomtpmodifier", EnumModifierType.COMBAT);//+//
		ItemCMEnemyLevitation = new ItemCMEnemyLevitation("item_cm_elevitationmodifier", EnumModifierType.COMBAT);//+//
		
		ItemDefFalling = new ItemDefFalling("item_def_fallingmodifier", EnumModifierType.DEFENCE);//+
		ItemDefPoisoning = new ItemDefPoisoning("item_def_poisonmodifier", EnumModifierType.DEFENCE);//+
		ItemDefMagic = new ItemDefMagic("item_def_magicmodifier", EnumModifierType.DEFENCE);//+
		ItemDefCactusEffect = new ItemDefCactusEffect("item_def_cactusmodifier", EnumModifierType.DEFENCE);//+
		ItemDefInvisible = new ItemDefInvisible("item_def_invisiblemodifier", EnumModifierType.DEFENCE);//+
		ItemDefFireResistance = new ItemDefFireResistance("item_def_fireresmodifier", EnumModifierType.DEFENCE);//+
		ItemDefAntiDisarm = new ItemDefAntiDisarm("item_def_adisarmmodifier", EnumModifierType.DEFENCE);//+
		ItemDefArrow = new ItemDefArrow("item_def_arrowmodifier", EnumModifierType.DEFENCE);//+
		ItemDefExtinguish = new ItemDefExtinguish("item_def_extmodifier", EnumModifierType.DEFENCE);//+
		ItemDefHPHeal = new ItemDefHPHeal("item_def_hphealmodifier", EnumModifierType.DEFENCE);//+
		ItemDefExplosion = new ItemDefExplosion("item_def_explosionmodifier", EnumModifierType.DEFENCE);//+
		ItemDefLuck = new ItemDefLuck("item_def_luckmodifier", EnumModifierType.DEFENCE);//+
		ItemDefAOEDamageReduction = new ItemDefAOEDamageReduction("item_def_aoermodifier", EnumModifierType.DEFENCE);//+
		ItemDefProtection = new ItemDefProtection("item_def_protectionmodifier", EnumModifierType.DEFENCE);//+
		ItemDefDamageBlock = new ItemDefDamageBlock("item_def_dmageblockmodifier", EnumModifierType.DEFENCE);//+
		ItemDefAttackInvisible = new ItemDefAttackInvisible("item_def_ainvisiblemodifier", EnumModifierType.DEFENCE);//+
		ItemDefAbsoption = new ItemDefAbsoption("item_def_absorbtionmodifier", EnumModifierType.DEFENCE);//+
		ItemDefAddHealth = new ItemDefAddHealth("item_def_addhealthmodifier", EnumModifierType.DEFENCE);//+
		ItemDefEffectsRemove = new ItemDefEffectsRemove("item_def_effectsremovemodifier", EnumModifierType.DEFENCE);//+
		ItemDefSaveInventory = new ItemDefSaveInventory("item_def_invsavemodifier", EnumModifierType.DEFENCE);//+
		
		ItemPowerUpgrade = new ItemPowerUpgrade("item_powerupgrade");
		ItemCapacityUpgrade = new ItemCapacityUpgrade("item_capacityupgrade"); 
		 
		ItemFlintPlate = new ItemStandartEnhancer("item_flintplate");
		
		ItemDiamondCircuit = new ItemStandartEnhancer("item_dcircuit");
		ItemRedstoneCircuit = new ItemStandartEnhancer("item_rcircuit");
		ItemLapisCircuit = new ItemStandartEnhancer("item_lcircuit");
		
		ItemModifierCore = new ItemStandartEnhancer("item_modcore");
		ItemStandartPowerModule = new ItemStandartEnhancer("item_stpowermodule");
		ItemStandartCapacityModule = new ItemStandartEnhancer("item_stcapacitymodule");
		
		ItemProgrammer = new ItemStandartEnhancer("item_programmer");
		ItemRedstoneAlloy = new ItemStandartEnhancer("item_redstone_alloy");
		ItemDiamondGrinder = new ItemDiamondGrinder("item_diamond_grinder");
		
		ItemWeatherWand = new ItemWeatherWand("item_weather_wand");
		
		ItemMagnet = new ItemStandartEnhancer("item_magnet");
		
		ItemLightningShard = new ItemStandartEnhancer("item_lightning_shard");
		
	}
		
	public static void registerRenders() {
		
		registerRenders(ItemUTSpeedModifier);
		registerRenders(ItemUTJumpModifier);
		registerRenders(ItemUTLevitationModifier);
		registerRenders(ItemUTInvModifier);
		registerRenders(ItemUTRegenModifier);
		registerRenders(ItemUTMagnetModifier);
		registerRenders(ItemUTWbModifier);
		registerRenders(ItemUTNvisionModifier);
		registerRenders(ItemUTFlightModifier);
		registerRenders(ItemUTWSModifier);
		registerRenders(ItemUTSaturModifier);
		registerRenders(ItemUTWwModifier);
		registerRenders(ItemUTLwModifier);
		registerRenders(ItemUTAutoSMModifier);
		
		registerRenders(ItemCMEnemyBurn);
		registerRenders(ItemCMEnemyPoison);
		registerRenders(ItemCMEnemyWither);
		registerRenders(ItemCMEnemyBlind);
		registerRenders(ItemCMEnemySlow);
		registerRenders(ItemCMAttackStrength);
		registerRenders(ItemCMAttackJump);
		registerRenders(ItemCMAttackSpeed);
		registerRenders(ItemCMEnemyDisarm);
		registerRenders(ItemCMEnemyExplosion);
		registerRenders(ItemCMEnemyUnluck);
		registerRenders(ItemCMEnemyHunger);
		registerRenders(ItemCMKillOrDie);
		registerRenders(ItemCMAttackHeal);
		registerRenders(ItemCMAttackVampirism);
		registerRenders(ItemCMAttackMagicDamage);
		registerRenders(ItemCMAttackAbsorption);
		registerRenders(ItemCMAttackAOE);
		registerRenders(ItemCMAntiArmor);
		registerRenders(ItemCMEnemySlowdown);
		registerRenders(ItemCMEnemyNausea);
		registerRenders(ItemCMAttackHPReplacement);
		registerRenders(ItemCMAOELightningStrike);
		registerRenders(ItemCMEnemyRandomTeleport);
		registerRenders(ItemCMEnemyLevitation);
		
		registerRenders(ItemDefFalling);
		registerRenders(ItemDefPoisoning);
		registerRenders(ItemDefMagic);
		registerRenders(ItemDefCactusEffect);
		registerRenders(ItemDefInvisible);
		registerRenders(ItemDefFireResistance);
		registerRenders(ItemDefAntiDisarm);
		registerRenders(ItemDefArrow);
		registerRenders(ItemDefExtinguish);
		registerRenders(ItemDefHPHeal);
		registerRenders(ItemDefExplosion);
		registerRenders(ItemDefLuck);
		registerRenders(ItemDefAOEDamageReduction);
		registerRenders(ItemDefProtection);
		registerRenders(ItemDefDamageBlock);
		registerRenders(ItemDefAttackInvisible);
		registerRenders(ItemDefAbsoption);
		registerRenders(ItemDefAddHealth);
		registerRenders(ItemDefEffectsRemove);
		registerRenders(ItemDefSaveInventory);
		
		registerRenders(ItemPowerUpgrade);
		registerRenders(ItemCapacityUpgrade);
		registerRenders(ItemFlintPlate);
		registerRenders(ItemDiamondCircuit);
		registerRenders(ItemRedstoneCircuit);
		registerRenders(ItemLapisCircuit);
		registerRenders(ItemModifierCore);
		registerRenders(ItemStandartPowerModule);
		registerRenders(ItemStandartCapacityModule);
		registerRenders(ItemProgrammer);
		
		registerRenders(ItemRedstoneAlloy);
		registerRenders(ItemDiamondGrinder);
		registerRenders(ItemWeatherWand);
		registerRenders(ItemMagnet);
		
		registerRenders(ItemLightningShard);
	}
		
	public static void registerRenders(Item item){
		
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}
