package thunder.enhancer.guis;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import thunder.enhancer.containers.ContainerEnhancedInventory;
import thunder.enhancer.containers.ContainerUltimateCharger;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;
import thunder.enhancer.tileentities.TileEntityUltimateCharger;

public class GuiHandler implements IGuiHandler {
	
	public static final int ENHANCER_GUI_ID = 0;
	public static final int ULTIMATE_CHARGER_GUI_ID = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		IEnhancedInventory inv = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
			
		if(ID == ENHANCER_GUI_ID) {
			return new ContainerEnhancedInventory(player.inventory, inv.getInventory(), player);
		}else
		if(ID == ULTIMATE_CHARGER_GUI_ID) {
			return new ContainerUltimateCharger(player.inventory, (TileEntityUltimateCharger)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		IEnhancedInventory inv = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
		
		if(ID == ENHANCER_GUI_ID) {
			return new GuiEnhancedInventory(player, player.inventory, inv.getInventory());
		}else
		if(ID == ULTIMATE_CHARGER_GUI_ID) {
			return new GuiUltimateCharger(player.inventory, (TileEntityUltimateCharger)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

}
