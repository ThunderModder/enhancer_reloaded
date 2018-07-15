package thunder.enhancer.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thunder.enhancer.extended.EnhancedInventoryProvider;
import thunder.enhancer.extended.IEnhancedInventory;

public class HUDSyncRequestMessage implements IMessage {
    
    public HUDSyncRequestMessage() { }

    @Override
    public void fromBytes(ByteBuf buf) {
      
    }

    @Override
    public void toBytes(ByteBuf buf) {
       
    }

    public static class Handler implements IMessageHandler<HUDSyncRequestMessage, IMessage> {
        
        @Override
        public IMessage onMessage(HUDSyncRequestMessage message, MessageContext ctx) {
        	IEnhancedInventory cap = ctx.getServerHandler().player.getCapability(EnhancedInventoryProvider.INVENTORY_CAP, null);	
    		NetworkHandler.network.sendTo(new HUDSyncMessage(cap), (EntityPlayerMP)ctx.getServerHandler().player);			
            return null; 
        }
    }
}
