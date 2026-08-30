package tk.estecka.icuovz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.GameRenderer;
import tk.estecka.icuovz.ISeeYouOverThereMod;
import static tk.estecka.icuovz.ISeeYouOverThereMod.CONFIG;
import static tk.estecka.icuovz.ISeeYouOverThereMod.fovTan;

@Unique
@Mixin(GameRenderer.class)
public abstract class GameRendererMixin
{
	@ModifyExpressionValue(
		method="renderWorld",
		at=@At(
			value = "INVOKE",
			ordinal = 0,
			target = "net/minecraft/client/render/GameRenderer.getFov (Lnet/minecraft/client/render/Camera;FZ)F"
		)
	)
	private float ComputeTan(float fov){
		if (CONFIG.fovScaling){
			ISeeYouOverThereMod.fovTan = Math.tan(Math.toRadians(fov/2));
			ISeeYouOverThereMod.fovTanInverse = 1 / fovTan;
		}
		else {
			ISeeYouOverThereMod.fovTan = 1;
			ISeeYouOverThereMod.fovTanInverse = 1;
		}
		return fov;
	}
}
