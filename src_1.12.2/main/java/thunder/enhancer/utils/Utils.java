package thunder.enhancer.utils;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;
import thunder.enhancer.network.HUDSyncMessage;
import thunder.enhancer.network.NetworkHandler;

public class Utils {
	
	public static Random rand = new Random();
	
	public static final int POTION_SPEED = 1;
	public static final int POTION_SLOWNESS = 2;
	public static final int POTION_HASTE = 3;
	public static final int POTION_MFATIGUE = 4;
	public static final int POTION_STRENGTH = 5;
	public static final int POTION_INSTANT_HEALTH = 6;
	public static final int POTION_INSTANT_DAMAGE = 7;
	public static final int POTION_JUMPB = 8;
	public static final int POTION_NAUSEA = 9;
	public static final int POTION_REGENERATION = 10;
	public static final int POTION_RESISTANCE = 11;
	public static final int POTION_FRESISTANCE = 12;
	public static final int POTION_WBREATHING = 13;
	public static final int POTION_INVISIBILITY = 14;
	public static final int POTION_BLINDNESS = 15;
	public static final int POTION_NIGHTV = 16;
	public static final int POTION_HUNGER = 17;
	public static final int POTION_WEAKNESS = 18;
	public static final int POTION_POISON = 19;
	public static final int POTION_WITHER = 20;
	public static final int POTION_HBOOST = 21;
	public static final int POTION_ABSRORPTION = 22;
	public static final int POTION_SATURATION = 23;
	public static final int POTION_GLOWING = 24;
	public static final int POTION_LEVITATION = 25;
	public static final int POTION_LUCK = 26;
	public static final int POTION_UNLUCK = 27;

	public static void addPotionEffect(EntityPlayer player, int effectId, int amplifier) {
		if(effectId == 16){
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(effectId), 340, amplifier - 1));
		}else
		if(!player.isPotionActive(Potion.getPotionById(effectId)) || player.getActivePotionEffect(Potion.getPotionById(effectId)).getDuration() <= 1){
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(effectId), 160, amplifier - 1));
		}
	}
	
	public static void addPotionEffect(EntityLivingBase entity, int effectId, int amplifier) {
		if(effectId == 16){
			entity.addPotionEffect(new PotionEffect(Potion.getPotionById(effectId), 340, amplifier - 1));
		}else
		if(!entity.isPotionActive(Potion.getPotionById(effectId)) || entity.getActivePotionEffect(Potion.getPotionById(effectId)).getDuration() <= 1){
			entity.addPotionEffect(new PotionEffect(Potion.getPotionById(effectId), 160, amplifier - 1));
		}
	}
	
	public static void addPotionEffect(EntityLivingBase entity, int effectId, int amplifier, int time) {
		if(effectId == 16){
			entity.addPotionEffect(new PotionEffect(Potion.getPotionById(effectId), 340, amplifier - 1));
		}else
		if(!entity.isPotionActive(Potion.getPotionById(effectId)) || entity.getActivePotionEffect(Potion.getPotionById(effectId)).getDuration() <= 1){
			entity.addPotionEffect(new PotionEffect(Potion.getPotionById(effectId), time, amplifier - 1));
		}
	}
	
	public static Random getRandom() {
		return rand;
	}
	
	public static boolean getRandom(int chance){
		if(chance > 100) return false;
		return rand.nextInt(100) < chance;
	}
	
	public static NBTTagCompound getNbt(ItemStack itemStack) {
	    NBTTagCompound nbt = itemStack.getTagCompound();
	    if (nbt == null) {
	    	
	    	nbt = new NBTTagCompound();
	      
	      itemStack.setTagCompound(nbt);
	    }
	    return nbt;
	}
	
	public static void SendProgressToClient(EntityPlayer player){
		
		IEnhancedInventory cap = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);		
		NetworkHandler.network.sendTo(new HUDSyncMessage(cap), (EntityPlayerMP)player);
	}
}
