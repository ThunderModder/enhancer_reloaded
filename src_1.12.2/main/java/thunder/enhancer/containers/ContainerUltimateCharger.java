package thunder.enhancer.containers;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thunder.enhancer.api.IModifiable;
import thunder.enhancer.containers.slots.SlotStandartCharger;

public class ContainerUltimateCharger extends Container{
	
    private final IInventory tileUltimateCharger;
   


    public ContainerUltimateCharger(InventoryPlayer playerInventory, IInventory chargerInventory){
    	
        this.tileUltimateCharger = chargerInventory;

        this.addSlotToContainer(new SlotStandartCharger(playerInventory.player, chargerInventory, 0, 62, 8));//0
 
        this.addSlotToContainer(new SlotStandartCharger(playerInventory.player, chargerInventory, 1, 62, 26));//1

        this.addSlotToContainer(new SlotStandartCharger(playerInventory.player, chargerInventory, 2, 62, 44));//2

        this.addSlotToContainer(new SlotStandartCharger(playerInventory.player, chargerInventory, 3, 62, 62));//3
        
        this.addSlotToContainer(new SlotStandartCharger(playerInventory.player, chargerInventory, 4, 98, 62));//4
        
        this.addSlotToContainer(new SlotStandartCharger(playerInventory.player, chargerInventory, 5, 152, 62));//5
              

        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j){
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k){
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    public void addListener(IContainerListener listener){
    	
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileUltimateCharger);
    }

    public void detectAndSendChanges(){
    	
        super.detectAndSendChanges();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data){
     
    }

    public boolean canInteractWith(EntityPlayer playerIn){
    	
        return this.tileUltimateCharger.isUsableByPlayer(playerIn);
    } 

    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){
    	
    	ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
                        
            if (index < 6){
       
                if (!this.mergeItemStack(itemstack1, 6, 42, true)){
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 0 && index != 1 && index != 2 && index != 3 && index != 4 && index != 5){
            	
            	if (itemstack1.getItem() instanceof IModifiable){
                    if (!this.mergeItemStack(itemstack1, 0, 6, false)){
                        return ItemStack.EMPTY;
                    }
                }else if (index >= 6 && index < 33){
                    if (!this.mergeItemStack(itemstack1, 33, 42, false)){
                        return ItemStack.EMPTY;
                    }
                }else if (index >= 33 && index < 42 && !this.mergeItemStack(itemstack1, 6, 32, false)){
                    return ItemStack.EMPTY;
                }
            }else if (!this.mergeItemStack(itemstack1, 6, 42, false)){
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
}
