package thunder.enhancer.containers.slots;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thunder.enhancer.api.IModifiable;
import thunder.enhancer.items.ItemCapacityUpgrade;
import thunder.enhancer.items.ItemPowerUpgrade;
import thunder.enhancer.items.ItemWeatherWand;

public class SlotStandartCharger extends Slot {
	
    private final EntityPlayer thePlayer;
    private int removeCount;

    public SlotStandartCharger(EntityPlayer player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition){
    	
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.thePlayer = player;
    }

    public boolean isItemValid(@Nullable ItemStack stack){
    	
    	if(this.getSlotIndex() == 4 && (stack.getItem() instanceof ItemCapacityUpgrade || stack.getItem() instanceof ItemPowerUpgrade)) return true;
    	if(stack.getItem() instanceof ItemWeatherWand) return true;
    	if(!(stack.getItem() instanceof IModifiable)) return false;
    	return true;
    }

    public ItemStack decrStackSize(int amount){
    	
        if (this.getHasStack()){
            this.removeCount += Math.min(amount, this.getStack().getCount());
        }

        return super.decrStackSize(amount);
    }
   
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
    {
        this.onCrafting(stack);
        super.onTake(thePlayer, stack);
        return stack;
    }

    protected void onCrafting(ItemStack stack, int amount){
    	
        this.removeCount += amount;
        this.onCrafting(stack);
    }

    protected void onCrafting(ItemStack stack){
    	
        stack.onCrafting(this.thePlayer.world, this.thePlayer, this.removeCount);
        this.removeCount = 0;    
    }
}
