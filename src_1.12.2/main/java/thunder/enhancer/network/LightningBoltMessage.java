package thunder.enhancer.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LightningBoltMessage implements IMessage {
	
	private static int xCoord;
	private static int yCoord;
	private static int zCoord;
	
	public LightningBoltMessage() {}
    
	public LightningBoltMessage(int x, int y, int z) {
		xCoord = x;
		yCoord = y;
		zCoord = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		xCoord = buf.readInt();
		yCoord = buf.readInt();
		zCoord = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(xCoord);
		buf.writeInt(yCoord);
		buf.writeInt(zCoord);
	}

    public static class Handler implements IMessageHandler<LightningBoltMessage, IMessage> {
        
        @Override
        public IMessage onMessage(LightningBoltMessage message, MessageContext ctx) {
        	EntityLightningBolt lightning = new EntityLightningBolt(Minecraft.getMinecraft().player.getEntityWorld(), xCoord, yCoord + 1, zCoord, true);
        	Minecraft.getMinecraft().player.getEntityWorld().spawnEntity(lightning);
        	
            return null; 
        }
    }
}
