package thunder.enhancer.guis;

import java.text.DecimalFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;
import thunder.enhancer.utils.NBTItemProvider;

@SideOnly(Side.CLIENT)
public class GuiHUD extends Gui{
	
	private Minecraft mc;
	
	private DecimalFormat format = new DecimalFormat("#");
	
	public static void register(){	
		MinecraftForge.EVENT_BUS.register(new GuiHUD(Minecraft.getMinecraft()));
	}

	public GuiHUD(Minecraft mc)
	{
		super();
		
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderHUDInfo(RenderGameOverlayEvent event)
	{
		
		if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE)
		{
			return;
		}
				
		IEnhancedInventory inv = this.mc.player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);		
		ScaledResolution res = event.getResolution();
		
		int y = res.getScaledHeight() - 100;
		int x =  res.getScaledWidth();
								
		NonNullList<ItemStack> stacks = inv.getInventory().getStacks();
		
		int k = 10;
		int j = 10;
		int count = 0;
		for(int i = 0; i < stacks.size(); i++){
			ItemStack stack = stacks.get(i);
			if(!stack.isEmpty()){
				count++;
				if(count < 6){		
					this.mc.fontRenderer.drawString("" + (NBTItemProvider.getCharge(stack) > 0 ? TextFormatting.GREEN : TextFormatting.RED) + format.format((100 * NBTItemProvider.getCharge(stack))/(NBTItemProvider.getMaxCharge(stack) + 0.00001)), x - 20, y + k + 5, 0xffffff);
					this.mc.getRenderItem().renderItemIntoGUI(stack, x - 35, y + k);
					k += 15;
				}
				else{						
					this.mc.fontRenderer.drawString("" + (NBTItemProvider.getCharge(stack) > 0 ? TextFormatting.GREEN : TextFormatting.RED) + format.format((100 * NBTItemProvider.getCharge(stack))/(NBTItemProvider.getMaxCharge(stack) + 0.00001)), x - 65, y + j + 5, 0xffffff);
					this.mc.getRenderItem().renderItemIntoGUI(stack, x - 48, y + j);
					j += 15;
				}
			}
		}	

	}

}
