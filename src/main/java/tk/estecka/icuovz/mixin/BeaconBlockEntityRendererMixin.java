package tk.estecka.icuovz.mixin;

import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import tk.estecka.icuovz.ISeeYouOverThereMod;

@Mixin(BeaconRenderer.class)
public class BeaconBlockEntityRendererMixin 
{
	/**
	 * @author Estecka
	 * @reason Vanilla method is a dumb constant; nothing to salvage there.
	 */
	@Overwrite
	public int getViewDistance(){
		return ISeeYouOverThereMod.CONFIG.beaconBeam;
	}
}
