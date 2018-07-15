package thunder.enhancer.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTItemProvider {
	
	public static int getCharge(ItemStack stack){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		return tag.getInteger("charge");
	}
	
	public static int getPower(ItemStack stack){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		return tag.getInteger("power");
	}
	
	public static int getMaxCharge(ItemStack stack){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		return tag.getInteger("max_charge");
	}
	
	public static int getMaxPower(ItemStack stack){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		return tag.getInteger("max_power");
	}
	
	public static void charge(ItemStack stack, int amount){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		int curr_charge = tag.getInteger("charge");
		int max_charge = tag.getInteger("max_charge");
		
		tag.setInteger("charge", curr_charge += amount);
		
		if(tag.getInteger("charge") > max_charge)
			tag.setInteger("charge", max_charge);

	}
	
	public static boolean discharge(ItemStack stack, int amount){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		int curr_charge = tag.getInteger("charge");
	
		int charge = (curr_charge -= (amount * NBTItemProvider.getPower(stack)));
		
		if(charge >= 0) {
			tag.setInteger("charge", charge);
			return true;
		}
		
		return false;
		
	}
	
	public static void addPower(ItemStack stack, int amount){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		int power = tag.getInteger("power");
		int max_power = tag.getInteger("max_power");
		
		tag.setInteger("power", power += amount);
		
		if(tag.getInteger("power") > max_power)
			tag.setInteger("power", max_power);
	}
	
	public static void setMaxCharge(ItemStack stack, int amount){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		if(amount < 0) return;
		tag.setInteger("max_charge", amount);	
	}
	
	public static void setCharge(ItemStack stack, int amount){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		if(amount < 0) return;
		tag.setInteger("charge", amount);	
	}
	
	public static void setMaxPower(ItemStack stack, int amount){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		if(amount < 1) return;
		tag.setInteger("max_power", amount);
	}
	
	public static void setPower(ItemStack stack, int amount){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		if(amount < 1) return;
		tag.setInteger("power", amount);
	}
	
	
	public static boolean canDischarge(ItemStack stack, int amount){
		
		NBTTagCompound tag = Utils.getNbt(stack);
		int curr_charge = tag.getInteger("charge");
	
		curr_charge -= (amount * NBTItemProvider.getPower(stack));
		
		return curr_charge > 0;
	}
	
}
