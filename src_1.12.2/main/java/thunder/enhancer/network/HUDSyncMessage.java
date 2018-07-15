package thunder.enhancer.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;

public class HUDSyncMessage implements IMessage {
	
	private static NBTTagCompound tag;
	
	public HUDSyncMessage() {}
    
	public HUDSyncMessage(IEnhancedInventory cap) {
		tag = new NBTTagCompound();
		cap.getInventory().writeToNBT(tag);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		tag = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, tag);
	}

    public static class Handler implements IMessageHandler<HUDSyncMessage, IMessage> {
        
        @Override
        public IMessage onMessage(HUDSyncMessage message, MessageContext ctx) {
        	EntityPlayerSP player = Minecraft.getMinecraft().player;
        	if(player!= null){
    			IEnhancedInventory cap = player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);
    			cap.getInventory().readFromNBT(tag);
    		}		
            return null; 
        }
    }
}
