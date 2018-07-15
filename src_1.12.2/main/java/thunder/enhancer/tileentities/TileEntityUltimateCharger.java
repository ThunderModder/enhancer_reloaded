package thunder.enhancer.tileentities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import thunder.enhancer.api.IModifiable;
import thunder.enhancer.containers.ContainerUltimateCharger;
import thunder.enhancer.items.ItemCapacityUpgrade;
import thunder.enhancer.items.ItemPowerUpgrade;
import thunder.enhancer.items.ItemRegistry;
import thunder.enhancer.network.LightningBoltMessage;
import thunder.enhancer.network.NetworkHandler;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;
import thunder.enhancer.utils.Utils;

public class TileEntityUltimateCharger extends TileEntityLockable implements ITickable, ISidedInventory {
	
	private static final int[] SLOTS_TOP = new int[] {0, 1, 2, 3, 4};
    private static final int[] SLOTS_BOTTOM = new int[] {0, 1, 2, 3, 4};
    private static final int[] SLOTS_SIDES = new int[] {0, 1, 2, 3, 4};
    
	private NonNullList<ItemStack> inventoryStacks = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
    
    private int max_charge = Constants.MAX_CHARGE_ULTIMATE_CHARGER;
    private int curr_charge = 0;
    
    private int ticker = 0;
       
    private String chargerCustomName;

	@Override
	public void update() {
							
		if(!this.world.isRemote){
			
			boolean flag = false;

			this.incrementTicker();
					
			if(this.ticker % 600 == 0 && this.world.canBlockSeeSky(this.pos.up()) && this.world.isRaining() && Utils.getRandom(80)){
				NetworkHandler.network.sendToAllAround(new LightningBoltMessage(this.pos.getX(), this.pos.getY(), this.pos.getZ()), new TargetPoint(this.world.provider.getDimension(), (double)this.pos.getX(), (double)this.pos.getY(), (double)this.pos.getZ(), 100));
				EntityLightningBolt lightning = new EntityLightningBolt(this.world, this.pos.getX(), this.pos.getY(), this.pos.getZ(), true);
				this.world.spawnEntity(lightning);
				this.charge(500000 + 100000 * Utils.getRandom().nextInt(5));
				updateCharger();
				flag = true;
	
			}
							
			if(flag){
				if(Utils.getRandom(35)){
					this.world.spawnEntity(new EntityItem(this.world, this.pos.getX(), this.pos.getY() + 1, this.pos.getZ(), new ItemStack(ItemRegistry.ItemLightningShard)));
				}
			}

			for(int i = 0; i < 4; i++){
				if(!this.inventoryStacks.get(i).isEmpty()){
					this.chargeItem(this.inventoryStacks.get(i), 1000);
					updateCharger();	
				}
			}
			
			if(!this.inventoryStacks.get(4).isEmpty() && !this.inventoryStacks.get(5).isEmpty()){
				ItemStack upgrade = this.inventoryStacks.get(4);
				ItemStack modifier = this.inventoryStacks.get(5);
				if(modifier.getItem() instanceof IModifiable){
					if(upgrade.getItem() instanceof ItemPowerUpgrade && NBTItemProvider.getPower(modifier) < NBTItemProvider.getMaxPower(modifier) && this.curr_charge >= 500000){
						upgrade.shrink(1);
						this.discharge(500000);
						updateCharger();
						if(upgrade.getCount() <= 0) this.setInventorySlotContents(4, ItemStack.EMPTY);
						NBTItemProvider.addPower(modifier, 1);
					}else
						if(upgrade.getItem() instanceof ItemCapacityUpgrade && NBTItemProvider.getMaxCharge(modifier) < 100000000 && this.curr_charge >= 1000000){
							upgrade.shrink(1);
							this.discharge(1000000);
							updateCharger();
							if(upgrade.getCount() <= 0) this.setInventorySlotContents(4, ItemStack.EMPTY);
							NBTItemProvider.setMaxCharge(modifier, NBTItemProvider.getMaxCharge(modifier) + 1000000);
						}
				}
				
			}	
		}
		
		
	}
	
	private void updateCharger(){
		
		this.markDirty();	
		IBlockState iblockstate = this.world.getBlockState(pos);
        world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
    	
		NBTTagCompound tag = getUpdateTag();
		return new SPacketUpdateTileEntity(this.pos, this.getBlockMetadata(), tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	@Override
	public NBTTagCompound getUpdateTag(){
		
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return tag;
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		 
	    this.readFromNBT(tag);
	}
		
	private void incrementTicker(){
		
		this.ticker++;
		if(this.ticker >= 240000) ticker = 0;
	}
	
	private void charge(int amount){
		
		this.curr_charge += amount;
		if(this.curr_charge > this.max_charge)
			this.curr_charge = this.max_charge;
	}
	
	private void discharge(int amount){
		
		this.curr_charge -= amount;
		if(this.curr_charge < 0)
			this.curr_charge = 0;

	}
	
	public int getCharge(){
		
		return this.curr_charge;
	}
	
	private void chargeItem(ItemStack stack, int transfer){
				
		int charge = NBTItemProvider.getMaxCharge(stack) - NBTItemProvider.getCharge(stack);
		if(charge == 0) return;
		
		int amplifier = (NBTItemProvider.getMaxCharge(stack) / 1000000) * 2;
		
		transfer *= amplifier;
		
		if(transfer > charge){
			if(charge <= this.curr_charge){
				NBTItemProvider.charge(stack, charge);
				this.discharge(charge);
			}
		}else if(transfer <= this.curr_charge){
			NBTItemProvider.charge(stack, transfer);
			this.discharge(transfer);
		}
	}
	
	@Override
    public void readFromNBT(NBTTagCompound compound){
		
		super.readFromNBT(compound);
    	
        this.inventoryStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventoryStacks);
       
        this.curr_charge = compound.getInteger("Current_Charge");
        this.max_charge = compound.getInteger("Max_Charge");
        this.ticker = compound.getInteger("Ticker");
        
        if (compound.hasKey("CustomName", 8)){
            this.chargerCustomName = compound.getString("CustomName");
        }
        
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
    	
    	super.writeToNBT(compound);
        
        compound.setInteger("Current_Charge", this.curr_charge);
        compound.setInteger("Max_Charge", this.max_charge);
        compound.setInteger("Ticker", this.ticker);

        ItemStackHelper.saveAllItems(compound, this.inventoryStacks);
       
        if (this.hasCustomName()){
            compound.setString("CustomName", this.chargerCustomName);
        }
              
        return compound;
    }
    
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
	
		 return side == EnumFacing.DOWN ? SLOTS_BOTTOM : (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES);
	}

	@Override
	public int getField(int id) {
		
		return 0; 
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		
		return 1;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		
		  return new ContainerUltimateCharger(playerInventory, this);
	}
	
	@Override
	public int getSizeInventory() {
		
		 return this.inventoryStacks.size();
	}
	
	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.inventoryStacks) {
			if (!itemstack.isEmpty()) {
	                return false;
	        }
	    }
	    return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		
		  return this.inventoryStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
	
		return ItemStackHelper.getAndSplit(this.inventoryStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		
		return ItemStackHelper.getAndRemove(this.inventoryStacks, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		
        ItemStack itemstack = (ItemStack)this.inventoryStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.inventoryStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()){
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !flag) {
            this.markDirty();
        }
	}

	@Override
	public int getInventoryStackLimit() {
		
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {

		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		
		if(index == 4 && (stack.getItem() instanceof ItemCapacityUpgrade || stack.getItem() instanceof ItemPowerUpgrade)) return true;
    	if(!(stack.getItem() instanceof IModifiable)) return false;
    	return true;
	}

	@Override
	public void clear() {
		
		this.inventoryStacks.clear();
	}

	@Override
	public String getName() {
		
		return "tileentity.ultimatecharger.name";
	}

	@Override
    public boolean hasCustomName(){
    	
        return this.chargerCustomName != null && !this.chargerCustomName.isEmpty();
    }

	@Override
	public String getGuiID() {
		
		return "";
	}


	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		
        return true;
	}

	net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing){
    	
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

}
