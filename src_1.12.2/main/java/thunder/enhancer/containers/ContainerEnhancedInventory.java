package thunder.enhancer.containers;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thunder.enhancer.api.IModifiable;
import thunder.enhancer.containers.slots.SlotStandartModifier;
import thunder.enhancer.inventory.InventoryEnhanced;

public class ContainerEnhancedInventory  extends Container {

    private final EntityPlayer thePlayer;
    
    public ContainerEnhancedInventory(InventoryPlayer playerInventory, InventoryEnhanced eInventory, EntityPlayer player) {
        
        this.thePlayer = player;
        
        this.addSlotToContainer(new SlotStandartModifier(player, eInventory, 0, 41, 8));
        this.addSlotToContainer(new SlotStandartModifier(player, eInventory, 1, 41, 35));
        this.addSlotToContainer(new SlotStandartModifier(player, eInventory, 2, 41, 62));
        this.addSlotToContainer(new SlotStandartModifier(player, eInventory, 3, 118, 8));
        this.addSlotToContainer(new SlotStandartModifier(player, eInventory, 4, 118, 35));
        this.addSlotToContainer(new SlotStandartModifier(player, eInventory, 5, 118, 62));
        this.addSlotToContainer(new SlotStandartModifier(player, eInventory, 6, 18, 8));
        this.addSlotToContainer(new SlotStandartModifier(player, eInventory, 7, 18, 35));
        this.addSlotToContainer(new SlotStandartModifier(player, eInventory, 8, 141, 8));
        this.addSlotToContainer(new SlotStandartModifier(player, eInventory, 9, 141, 35));
        
        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlotToContainer(new Slot(playerInventory, j1 + (l + 1) * 9, 8 + j1 * 18, 84 + l * 18));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18, 142));
        }

    }
	
    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
    	
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(itemstack);
           
            if (index < 10)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, true)) {
                	
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 0 && index != 1 && index != 2 && index != 3 && index != 4 && index != 5 && index != 6 && index != 7 && index != 8 && index != 9){
            	
            	if(itemstack1.getItem() instanceof IModifiable){
            		if (!this.mergeItemStack(itemstack1, 0, 10, false)){
                        return ItemStack.EMPTY;
                    }
            	}else if (index >= 10 && index < 37){
                    if (!this.mergeItemStack(itemstack1, 37, 46, false)){
                        return ItemStack.EMPTY;
                    }
                }else if (index >= 37 && index < 46 && !this.mergeItemStack(itemstack1, 10, 37, false)){
                    return ItemStack.EMPTY;
                }
            }else if (!this.mergeItemStack(itemstack1, 10, 46, false)){
                return ItemStack.EMPTY;
            }                  

            if (itemstack1.getCount() == 0){
                slot.putStack(ItemStack.EMPTY);
            }
            else{
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()){
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
	
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		
		return true;
	}

}
