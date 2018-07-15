package thunder.enhancer.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thunder.enhancer.core.Enhancer;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;

public class ItemWeatherWand extends Item {
	
	public ItemWeatherWand(String unlocalizedName){
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setMaxStackSize(1);
	
		this.setCreativeTab(Enhancer.ENHANCER_TAB_BLOCKSNITEMS);
		ForgeRegistries.ITEMS.register(this);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
	    	
		return EnumRarity.UNCOMMON;
	}	
	
	@Override
    public boolean showDurabilityBar(ItemStack stack) {
    	
        return true;
    }
    
	@Override
    public double getDurabilityForDisplay(ItemStack stack) {
    	
        return 1 - (double)NBTItemProvider.getCharge(stack)/(double)NBTItemProvider.getMaxCharge(stack);
    }
	
	public int getRGBDurabilityForDisplay(ItemStack stack) {
	    	
		return MathHelper.hsvToRGB((float) ((((double)NBTItemProvider.getCharge(stack)/(double)NBTItemProvider.getMaxCharge(stack))) / 6.0F), 1.0F, 1.0F);
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		
		ItemStack stack = player.getHeldItemMainhand();
    	
    	if(!world.isRemote && !world.getWorldInfo().isRaining() && NBTItemProvider.discharge(stack, Constants.WEATHER_WAND_BASE)){
    		world.getWorldInfo().setRaining(true);
    		world.getWorldInfo().setThunderTime(100);
    	}
    	
    	return new ActionResult(EnumActionResult.SUCCESS, stack);
    }
	

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
	    		 
		if(tab == Enhancer.ENHANCER_TAB_BLOCKSNITEMS){
			subItems.add(ItemWeatherWand.getNewWeatherWandCharged());
			subItems.add(ItemWeatherWand.getNewWeatherWandDischarged());
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)  {
		
		tooltip.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.charge", new Object[]{}) + (NBTItemProvider.getCharge(stack) > 0 ? TextFormatting.GREEN : TextFormatting.RED) + NBTItemProvider.getCharge(stack) + TextFormatting.GRAY +  "/" + NBTItemProvider.getMaxCharge(stack) + " LT");
	}
	
	public static ItemStack getNewWeatherWandCharged(){
		
		ItemStack wand = new ItemStack(ItemRegistry.ItemWeatherWand);
		NBTItemProvider.setMaxCharge(wand, 1000000);
		NBTItemProvider.setCharge(wand, 1000000);
		NBTItemProvider.setMaxPower(wand, 1);
		NBTItemProvider.setPower(wand, 1);
		return wand;
	}
	
	public static ItemStack getNewWeatherWandDischarged(){
		
		ItemStack wand = new ItemStack(ItemRegistry.ItemWeatherWand);
		NBTItemProvider.setMaxCharge(wand, 1000000);
		NBTItemProvider.setCharge(wand, 0);
		NBTItemProvider.setMaxPower(wand, 1);
		NBTItemProvider.setPower(wand, 1);
		return wand;
	}
}
