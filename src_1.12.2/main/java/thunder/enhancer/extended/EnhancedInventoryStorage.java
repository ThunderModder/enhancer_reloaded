package thunder.enhancer.extended;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class EnhancedInventoryStorage implements IStorage<IEnhancedInventory> {

	@Override
	public NBTBase writeNBT(Capability<IEnhancedInventory> capability, IEnhancedInventory instance, EnumFacing side) {
		
		NBTTagCompound properties = new NBTTagCompound();
		instance.getInventory().writeToNBT(properties);
		return properties;
	}

	@Override
	public void readNBT(Capability<IEnhancedInventory> capability, IEnhancedInventory instance, EnumFacing side, NBTBase nbt) {
		
		NBTTagCompound properties = (NBTTagCompound)nbt;
		instance.getInventory().readFromNBT(properties);
	}

}
