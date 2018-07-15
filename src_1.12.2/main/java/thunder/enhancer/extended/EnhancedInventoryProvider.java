package thunder.enhancer.extended;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class EnhancedInventoryProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(IEnhancedInventory.class)
	public static final Capability<IEnhancedInventory> INVENTORY_CAP = null;
	
	private IEnhancedInventory instance = INVENTORY_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {	
		
		 return capability == INVENTORY_CAP;		 
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		
		 return capability == INVENTORY_CAP ? INVENTORY_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		
		 return INVENTORY_CAP.getStorage().writeNBT(INVENTORY_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
	
		INVENTORY_CAP.getStorage().readNBT(INVENTORY_CAP, this.instance, null, nbt);
	}

}
