package thunder.enhancer.maps;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import thunder.enhancer.items.ItemRegistry;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;

public class Modifiers {
	
	public static final Modifiers MODIFIERS = new Modifiers();
	
	public static Modifiers getInstance(){
		
		return MODIFIERS;
	}
	
	private final Map<ItemStack [], Integer> modifiers = Maps.<ItemStack [], Integer>newHashMap();
	
	private Modifiers(){
		
		//Utility
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTSpeedModifier, 1), new ItemStack(Items.SUGAR, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.SWIFTNESS), new ItemStack(Items.SUGAR, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.SWIFTNESS), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 11);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTJumpModifier, 1), new ItemStack(Items.RABBIT_FOOT, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LEAPING), new ItemStack(Items.RABBIT_FOOT, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LEAPING), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 8);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTLevitationModifier, 1), new ItemStack(Items.GHAST_TEAR), new ItemStack(Items.FEATHER, 1), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTInvModifier, 1), new ItemStack(Items.GOLDEN_CARROT, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.INVISIBILITY), new ItemStack(Items.GOLDEN_CARROT, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.INVISIBILITY), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 2);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTWwModifier, 1), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(Items.SNOWBALL, 1), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(Items.SNOWBALL, 1), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTLwModifier, 1), new ItemStack(Items.LAVA_BUCKET, 1), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(Items.LAVA_BUCKET, 1), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTFlightModifier, 1), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTSaturModifier, 1), new ItemStack(Items.WHEAT, 1), new ItemStack(Items.EGG, 1), new ItemStack(Items.WHEAT, 1), new ItemStack(Items.EGG, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTRegenModifier, 1), new ItemStack(Items.MELON, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.REGENERATION), new ItemStack(Items.MELON, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.REGENERATION), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTNvisionModifier, 1), new ItemStack(Items.ENDER_EYE, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.NIGHT_VISION), new ItemStack(Items.ENDER_EYE, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.NIGHT_VISION), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 2);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTWbModifier, 1), new ItemStack(Items.FISH, 1, 3), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER_BREATHING), new ItemStack(Items.FISH, 1, 3), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER_BREATHING), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTAutoSMModifier, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTWSModifier, 1), new ItemStack(Items.FISH, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_SWIFTNESS), new ItemStack(Items.FISH, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_SWIFTNESS), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemUTMagnetModifier, 1), new ItemStack(ItemRegistry.ItemMagnet, 1), new ItemStack(ItemRegistry.ItemMagnet, 1), new ItemStack(ItemRegistry.ItemMagnet, 1), new ItemStack(ItemRegistry.ItemMagnet, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 4);//+
		
		//Combat
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyBurn, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.BLAZE_POWDER, 1), new ItemStack(Items.FLINT_AND_STEEL, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 5);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyPoison, 1), new ItemStack(Items.SPIDER_EYE, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.POISON), new ItemStack(Items.SPIDER_EYE, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.POISON), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 3);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyWither, 1), new ItemStack(Items.SKULL, 1, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.NETHER_WART, 1), new ItemStack(Items.NETHER_WART, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 2);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyBlind, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemySlow, 1), new ItemStack(Items.SLIME_BALL, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.SLOWNESS), new ItemStack(Items.SLIME_BALL, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.SLOWNESS), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 8);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAttackStrength, 1), new ItemStack(Items.IRON_SWORD, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRENGTH), new ItemStack(Items.BLAZE_ROD, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRENGTH), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 5);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAttackJump, 1), new ItemStack(Items.IRON_SWORD, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LEAPING), new ItemStack(Items.RABBIT_FOOT, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LEAPING), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 8);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAttackSpeed, 1), new ItemStack(Items.IRON_SWORD, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.SWIFTNESS), new ItemStack(Items.SUGAR, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.SWIFTNESS), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 11);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyDisarm, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.BONE, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.BONE, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 3);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyExplosion, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.GUNPOWDER, 1), new ItemStack(Items.GUNPOWDER, 1), new ItemStack(Items.GUNPOWDER, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyUnluck, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.RABBIT_HIDE, 1), new ItemStack(Items.RABBIT_HIDE, 1), new ItemStack(Items.RABBIT_HIDE, 1), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyHunger, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.ROTTEN_FLESH, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1), new ItemStack(Items.ROTTEN_FLESH, 1), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 5);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMKillOrDie, 1), new ItemStack(Items.BONE, 1), new ItemStack(Items.RABBIT_FOOT, 1), new ItemStack(Items.BONE, 1), new ItemStack(Items.RABBIT_FOOT, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAttackHeal, 1), new ItemStack(Items.IRON_SWORD, 1),  PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), new ItemStack(Items.SPECKLED_MELON, 1),  PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAttackVampirism, 1), new ItemStack(Items.IRON_SWORD, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HARMING), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HARMING), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAttackMagicDamage, 1), new ItemStack(Items.IRON_SWORD, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HARMING),new ItemStack(Items.BONE, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HARMING), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAttackAbsorption, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 2);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAttackAOE, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.ENDER_EYE, 1), new ItemStack(Items.ARROW, 1), new ItemStack(Items.ENDER_EYE, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAntiArmor, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.BONE, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_STRENGTH), new ItemStack(Items.BONE, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemySlowdown, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.CLAY_BALL, 1), new ItemStack(Items.SLIME_BALL, 1), new ItemStack(Items.CLAY_BALL, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyNausea, 1), new ItemStack(Items.IRON_SWORD, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.POISON), new ItemStack(Items.SPIDER_EYE, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.POISON), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAttackHPReplacement, 1), new ItemStack(Items.IRON_SWORD, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.REGENERATION), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.REGENERATION), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMAOELightningStrike, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(ItemRegistry.ItemLightningShard, 1), new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(ItemRegistry.ItemLightningShard, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyRandomTeleport, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.ENDER_EYE, 1), new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(Items.ENDER_EYE, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemCMEnemyLevitation, 1), new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(Items.GHAST_TEAR, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 5);//+

		//Defence
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefFalling, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(Items.FEATHER, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefPoisoning, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.SPIDER_EYE, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.POISON), new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefMagic, 1), new ItemStack(Items.SHIELD, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_HEALING), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HARMING), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefCactusEffect, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.BONE, 1), new ItemStack(Items.GOLDEN_SWORD, 1), new ItemStack(Items.BONE, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefInvisible, 1), new ItemStack(Items.REDSTONE, 1), new ItemStack(Items.GLOWSTONE_DUST, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_INVISIBILITY), new ItemStack(Items.GLOWSTONE_DUST, 1), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefFireResistance, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.MAGMA_CREAM, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_FIRE_RESISTANCE), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefAntiDisarm, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.SLIME_BALL, 1), new ItemStack(Items.BONE, 1), new ItemStack(Items.SLIME_BALL, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefArrow, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.ARROW, 1), new ItemStack(Items.ARROW, 1), new ItemStack(Items.ARROW, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefExtinguish, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_FIRE_RESISTANCE), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(Items.WATER_BUCKET, 1), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefHPHeal, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_HEALING), new ItemStack(Items.GOLDEN_APPLE, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_HEALING), new ItemStack(Items.GOLDEN_APPLE, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefExplosion, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.GUNPOWDER, 1), new ItemStack(Items.GUNPOWDER, 1), new ItemStack(Items.GUNPOWDER, 1), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 1);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefLuck, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.THICK), new ItemStack(Items.RABBIT_FOOT, 1), new ItemStack(Items.RABBIT_FOOT, 1), new ItemStack(Items.RABBIT_FOOT, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefAOEDamageReduction, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.ENDER_EYE, 1), new ItemStack(Items.ARROW, 1), new ItemStack(Items.ENDER_EYE, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefProtection, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(Items.IRON_CHESTPLATE, 1), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 5);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefDamageBlock, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.RABBIT_FOOT, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.THICK), new ItemStack(Items.RABBIT_FOOT, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefAttackInvisible, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.INVISIBILITY), new ItemStack(Items.RABBIT_FOOT, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.THICK), new ItemStack(Items.RABBIT_FOOT, 1), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefAbsoption, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.REGENERATION), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(Items.SHIELD, 1), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(ItemRegistry.ItemLapisCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefAddHealth, 1), new ItemStack(Items.GOLDEN_APPLE, 1, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.HEALING), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 10);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefEffectsRemove, 1), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(Items.SPIDER_EYE, 1), new ItemStack(Items.SUGAR, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1), new ItemStack(ItemRegistry.ItemRedstoneCircuit, 1)}, 4);//+
		this.modifiers.put(new ItemStack [] {new ItemStack(ItemRegistry.ItemDefSaveInventory, 1), new ItemStack(Items.NETHER_STAR, 1), new ItemStack(Items.BOOK, 1), new ItemStack(Items.BOOK, 1), new ItemStack(Items.BOOK, 1), new ItemStack(ItemRegistry.ItemDiamondCircuit, 1)}, 1);//+

	
	
	}
	
	public void addModifier(ItemStack modifier, ItemStack circuit, int power, ItemStack ... recipe){
		
		this.modifiers.put(new ItemStack [] {modifier, recipe[0], recipe[1], recipe[2], recipe[3], circuit}, power);
	}
	
	public void  matches(NonNullList<ItemStack> subItems){
		
		for(Entry<ItemStack [], Integer> entry : this.modifiers.entrySet()){

			ItemStack [] modifiers = new ItemStack [] {entry.getKey()[0].copy(), entry.getKey()[0].copy()};	
						
			NBTItemProvider.setMaxPower(modifiers[0], entry.getValue());
			NBTItemProvider.setMaxCharge(modifiers[0], Constants.STANDART_MAX_CHARGE);
			NBTItemProvider.setCharge(modifiers[0], 0);
			NBTItemProvider.setPower(modifiers[0], 1);
			subItems.add(modifiers[0]);
			NBTItemProvider.setMaxPower(modifiers[1], entry.getValue());
			NBTItemProvider.setMaxCharge(modifiers[1], Constants.STANDART_MAX_CHARGE);
			NBTItemProvider.setCharge(modifiers[1], Constants.STANDART_MAX_CHARGE);
			NBTItemProvider.setPower(modifiers[1], 1);
			subItems.add(modifiers[1]);			
		}		
	}
	
	public ItemStack getModifierWithType(ItemStack stack){
		
		for(Entry<ItemStack [], Integer> entry : this.modifiers.entrySet()){
			if(entry.getKey()[0].getItem().equals(stack.getItem())){
			
				NBTItemProvider.setMaxPower(stack, entry.getValue());
				NBTItemProvider.setMaxCharge(stack, Constants.STANDART_MAX_CHARGE);
				NBTItemProvider.setCharge(stack, 0);
				NBTItemProvider.setPower(stack, 1);
			}
		}
		return stack;
	}
	
	public Map<ItemStack [], Integer> getMap(){
		
		return this.modifiers;
	}

}
