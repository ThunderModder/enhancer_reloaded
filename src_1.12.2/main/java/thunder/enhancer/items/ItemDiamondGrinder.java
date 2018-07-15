package thunder.enhancer.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thunder.enhancer.core.Enhancer;
import thunder.enhancer.utils.Utils;

public class ItemDiamondGrinder extends Item {
	
	public ItemDiamondGrinder(String unlocalizedName) {

		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setMaxStackSize(1);
		
		this.setCreativeTab(Enhancer.ENHANCER_TAB_BLOCKSNITEMS);
		ForgeRegistries.ITEMS.register(this);
	}
	
	@Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		
		if(!stack.hasTagCompound()){
			NBTTagCompound tag = Utils.getNbt(stack);
			tag.setInteger("uses", 64);
		}   	
    }
	
	@Override
    public ItemStack getContainerItem(ItemStack stack) {
		
		NBTTagCompound tag = Utils.getNbt(stack);
		tag.setInteger("uses", tag.getInteger("uses") - 1);
		if(tag.getInteger("uses") <= 0) return ItemStack.EMPTY;
		else return stack.copy();	
    }
	
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		
        return true;
    }
     	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
		if(stack.hasTagCompound()){
			NBTTagCompound tag = Utils.getNbt(stack);
			tooltip.add(I18n.format("tooltip.grinder.uses", new Object[]{}) + tag.getInteger("uses"));
		}
	}
	
	public static ItemStack getNewDiamondGrinder(){
		
		ItemStack grinder = new ItemStack(ItemRegistry.ItemDiamondGrinder);
		NBTTagCompound tag = Utils.getNbt(grinder);
		tag.setInteger("uses", 64);
		return grinder;
	}
	

}
