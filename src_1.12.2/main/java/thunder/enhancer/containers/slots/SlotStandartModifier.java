package thunder.enhancer.containers.slots;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import thunder.enhancer.api.IModifiable;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;
import thunder.enhancer.items.ItemRegistry;
import thunder.enhancer.utils.Utils;

public class SlotStandartModifier extends Slot {
	
    private final EntityPlayer thePlayer;
    private int removeCount;

    public SlotStandartModifier(EntityPlayer player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition){
    	
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.thePlayer = player;
    }

    public boolean isItemValid(@Nullable ItemStack stack){
    	
    	if(!(stack.getItem() instanceof IModifiable)) return false;
    	
    	IEnhancedInventory inv = thePlayer.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
    	for(ItemStack istack : inv.getInventory().getStacks()){
  			if(!istack.isEmpty() && istack.getItem().equals(stack.getItem())) return false;
    	}
    	
        return true;
    }

    public ItemStack decrStackSize(int amount){
    	
        if (this.getHasStack()){
            this.removeCount += Math.min(amount, this.getStack().getCount());
        }

        return super.decrStackSize(amount);
    }

    public ItemStack onTake(EntityPlayer player, ItemStack stack){
    	
        this.onCrafting(stack);
        if(!player.capabilities.isCreativeMode && player.capabilities.allowFlying && stack.getItem().equals(ItemRegistry.ItemUTFlightModifier)){
        	player.capabilities.allowFlying = false;
        	if(player.capabilities.isFlying)
        		player.capabilities.isFlying = false;
        }
        if(stack.getItem().equals(ItemRegistry.ItemDefAddHealth) && player.isPotionActive(Potion.getPotionById(Utils.POTION_HBOOST))){
			player.removePotionEffect(Potion.getPotionById(Utils.POTION_HBOOST));
		}
        super.onTake(player, stack);
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
