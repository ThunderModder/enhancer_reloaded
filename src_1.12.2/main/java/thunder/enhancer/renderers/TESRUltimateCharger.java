package thunder.enhancer.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import thunder.enhancer.tileentities.TileEntityUltimateCharger;

public class TESRUltimateCharger extends TileEntitySpecialRenderer<TileEntityUltimateCharger> {
	
	public TESRUltimateCharger() {}
	
	public void render(TileEntityUltimateCharger te,  double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        	
		String s = I18n.format("tooltip.charge", new Object[]{}) + (te.getCharge() > 0 ? TextFormatting.GREEN : TextFormatting.RED) + te.getCharge() + TextFormatting.WHITE + " LT";

        if (this.rendererDispatcher.cameraHitResult != null && te.getPos().equals(this.rendererDispatcher.cameraHitResult.getBlockPos())) {
            
        	this.setLightmapDisabled(true);
            this.drawNameplate(te, s, x, y, z, 12);
            this.setLightmapDisabled(false);
        }
    }

}
