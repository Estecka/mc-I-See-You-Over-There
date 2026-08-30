package tk.estecka.icuovz.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import tk.estecka.icuovz.ISeeYouOverThereMod;
import static tk.estecka.icuovz.ISeeYouOverThereMod.CONFIG;
import static tk.estecka.icuovz.ISeeYouOverThereMod.fovTan;

@Unique
@Mixin(GameRenderer.class)
public abstract class GameRendererMixin
{
	private @Shadow @Final Camera mainCamera;

	@Inject( method="extractCamera", at=@At("HEAD"))
	private void ComputeTan(CallbackInfo ci){
		if (CONFIG.fovScaling){
			float fov = this.mainCamera.getFov();
			ISeeYouOverThereMod.fovTan = Math.tan(Math.toRadians(fov/2));
			ISeeYouOverThereMod.fovTanInverse = 1 / fovTan;
		}
		else {
			ISeeYouOverThereMod.fovTan = 1;
			ISeeYouOverThereMod.fovTanInverse = 1;
		}
	}
}
