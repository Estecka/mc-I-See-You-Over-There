package tk.estecka.icuovz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import tk.estecka.icuovz.ISeeYouOverThereMod;

@Mixin(BeaconBlockEntityRenderer.class)
public class BeaconBlockEntityRendererMixin 
{
	@Overwrite
	public int getRenderDistance(){
		return ISeeYouOverThereMod.CONFIG.beaconBeam;
	}
}
