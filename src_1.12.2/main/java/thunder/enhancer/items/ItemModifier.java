package thunder.enhancer.items;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thunder.enhancer.api.IModifiable;
import thunder.enhancer.core.Enhancer;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;
import thunder.enhancer.inventory.InventoryEnhanced;
import thunder.enhancer.maps.Modifiers;
import thunder.enhancer.network.HUDSyncMessage;
import thunder.enhancer.network.NetworkHandler;
import thunder.enhancer.utils.NBTItemProvider;

public abstract class ItemModifier extends Item implements IModifiable {
	
	private EnumModifierType type;
					
	public ItemModifier(String unlocalizedName, EnumModifierType type) {
		
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setMaxStackSize(1);
	
		this.type = type;
		
		this.setCreativeTab(Enhancer.ENHANCER_TAB_MODIFIERS);
		ForgeRegistries.ITEMS.register(this);
		
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
    	     
       return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
    	
        return EnumRarity.RARE;
    }
        
    public void getDescription(ItemStack stack, List<String> tips){
    	

    }
    
    public boolean showDurabilityBar(ItemStack stack) {
    	
        return true;
    }
    
    public double getDurabilityForDisplay(ItemStack stack) {
    	
        return 1 - ((double)NBTItemProvider.getCharge(stack)/(double)NBTItemProvider.getMaxCharge(stack));
    }
    
    public int getRGBDurabilityForDisplay(ItemStack stack) {
    	
        return MathHelper.hsvToRGB((float) ((((double)NBTItemProvider.getCharge(stack)/(double)NBTItemProvider.getMaxCharge(stack))) / 6.0F), 1.0F, 1.0F);
    }
    
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
    	
    	IEnhancedInventory inv = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
    	ItemStack stack = ItemStack.EMPTY;
    	ItemStack itemStack = player.getHeldItemMainhand();
    	InventoryEnhanced inventory = inv.getInventory();
    	
    	for(int i = 0; i < inventory.getSizeInventory(); i++){
    		if((stack = inventory.getStackInSlot(i)).isEmpty()){
    			for(ItemStack s : inventory.getStacks()){
    				if(!s.isEmpty() && s.getItem().equals(itemStack.getItem()))  
    					return new ActionResult(EnumActionResult.SUCCESS, itemStack);
    			}
    			
    			inventory.setInventorySlotContents(i, itemStack.copy());
    			if(!player.getEntityWorld().isRemote)
    				NetworkHandler.network.sendTo(new HUDSyncMessage(inv), (EntityPlayerMP)player);
    			player.inventory.removeStackFromSlot(player.inventory.currentItem);
    			player.inventoryContainer.detectAndSendChanges();
    			return new ActionResult(EnumActionResult.SUCCESS, itemStack);
    		}
    	}
    	
        return new ActionResult(EnumActionResult.SUCCESS, itemStack);
    }
	
	@Override
	public void onInventoryTick(EntityPlayer player, ItemStack stack) {
		
		
	}

	@Override
	public void onAttack(LivingAttackEvent event, EntityPlayer source, EntityLivingBase target, ItemStack stack) {
		
		
	}

	@Override
	public void onHurt(LivingHurtEvent event, EntityPlayer target, DamageSource source, ItemStack stack) {
		
		
	}
	
	@Override
	public void onDeath(LivingDeathEvent event, EntityPlayer player, DamageSource source, ItemStack stack) {
		
		
	}
	
	@Override
	public void onBlockBreak(BreakEvent event, EntityPlayer player, ItemStack stack){
		
		
	}
	
	@Override
	public void onHarvestDrops(HarvestDropsEvent event, EntityPlayer player, ItemStack stack){
		
		
	}
	
	@Override
	public void onItemUse(LivingEntityUseItemEvent.Start event, EntityPlayer player, ItemStack stack){
		
		
	}
		
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
    
   
    }
    
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
    	
    	if(tab == Enhancer.ENHANCER_TAB_MODIFIERS && subItems.isEmpty())
    		Modifiers.getInstance().matches(subItems);
    }
			
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
			
		TextFormatting txt = null;
		
		if(this.type.toString().equals("COMBAT")) txt = TextFormatting.RED;
		else
			if(this.type.toString().equals("UTILITY")) txt = TextFormatting.GREEN;
			else
				if(this.type.toString().equals("DEFENCE")) txt = TextFormatting.BLUE;
		
		tooltip.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.type", new Object[]{}) + txt + I18n.format("tooltip.type." + this.type.toString().toLowerCase(), new Object[]{}));
		tooltip.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.charge", new Object[]{}) + (NBTItemProvider.getCharge(stack) > 0 ? TextFormatting.GREEN : TextFormatting.RED) + NBTItemProvider.getCharge(stack) + TextFormatting.GRAY +  "/" + NBTItemProvider.getMaxCharge(stack) + " LT");
		tooltip.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.power", new Object[]{}) + TextFormatting.GOLD + NBTItemProvider.getPower(stack) + "/" + NBTItemProvider.getMaxPower(stack));	
		tooltip.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.description", new Object[]{}));
		List<String> tips = new ArrayList<String>();
		this.getDescription(stack, tips);
		for(String s : tips)
			tooltip.add(TextFormatting.AQUA + s);
	
	}

}
