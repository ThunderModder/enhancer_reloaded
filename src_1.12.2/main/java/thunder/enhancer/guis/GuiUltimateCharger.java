package thunder.enhancer.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thunder.enhancer.containers.ContainerUltimateCharger;
import thunder.enhancer.core.Reference;
import thunder.enhancer.tileentities.TileEntityUltimateCharger;

@SideOnly(Side.CLIENT)
public class GuiUltimateCharger extends GuiContainer {
	
    private static final ResourceLocation ULTIMATE_CHARGER_GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_ultimate_charger.png");
    private final InventoryPlayer playerInventory;
    private final TileEntityUltimateCharger tileUltimateCharger;
    private final EntityPlayer player;
    
	private float oldMouseX;
	private float oldMouseY;

    public GuiUltimateCharger(InventoryPlayer playerInv, TileEntityUltimateCharger tileInv){
    	
        super(new ContainerUltimateCharger(playerInv, tileInv));
        this.playerInventory = playerInv;
        this.tileUltimateCharger = tileInv;
        this.player = playerInv.player;
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	
    	this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
    }


    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
    	
        String s = I18n.format(this.tileUltimateCharger.getDisplayName().getUnformattedText());
        this.fontRenderer.drawString(s, 38 + this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.tileUltimateCharger.getCharge() + "/1B", 96, this.ySize - 118, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
    	
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(ULTIMATE_CHARGER_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		   		
        int k = (int)(66.0F * this.getChargeScaled());
        this.drawTexturedModalRect(i + 100, j + 27, 176, 0, k, 14);
        
        drawEntityOnScreen(i + 32, j + 75, 30, (float)(i + 32) - this.oldMouseX, (float)(j + 75 - 30) - this.oldMouseY, this.mc.player);
        
    }

    private float getChargeScaled(){
    	
        return (float)this.tileUltimateCharger.getField(0) / (float)this.tileUltimateCharger.getField(1);
    }
    
    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent) {
		 
	    GlStateManager.enableColorMaterial();
	    GlStateManager.pushMatrix();
	    GlStateManager.translate((float)posX, (float)posY, 50.0F);
	    GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
	    GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
	    float f = ent.renderYawOffset;
	    float f1 = ent.rotationYaw;
	    float f2 = ent.rotationPitch;
	    float f3 = ent.prevRotationYawHead;
	    float f4 = ent.rotationYawHead;
	    GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
	    RenderHelper.enableStandardItemLighting();
	    GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
	    GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
	    ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
	    ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
	    ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
	    ent.rotationYawHead = ent.rotationYaw;
	    ent.prevRotationYawHead = ent.rotationYaw;
	    GlStateManager.translate(0.0F, 0.0F, 0.0F);
	    RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
	    rendermanager.setPlayerViewY(180.0F);
	    rendermanager.setRenderShadow(false);
	    rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
	    rendermanager.setRenderShadow(true);
	    ent.renderYawOffset = f;
	    ent.rotationYaw = f1;
	    ent.rotationPitch = f2;
	    ent.prevRotationYawHead = f3;
	    ent.rotationYawHead = f4;
	    GlStateManager.popMatrix();
	    RenderHelper.disableStandardItemLighting();
	    GlStateManager.disableRescaleNormal();
	    GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
	    GlStateManager.disableTexture2D();
	    GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
}