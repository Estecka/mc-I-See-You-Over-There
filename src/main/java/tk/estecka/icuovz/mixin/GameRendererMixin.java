package tk.estecka.icuovz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.GameRenderer;
import tk.estecka.icuovz.ISeeYouOverThereMod;
import static tk.estecka.icuovz.ISeeYouOverThereMod.CONFIG;

@Unique
@Mixin(GameRenderer.class)
public abstract class GameRendererMixin
{
	@ModifyExpressionValue( method="renderWorld", at=@At(value="INVOKE", target="net/minecraft/client/render/GameRenderer.getFov (Lnet/minecraft/client/render/Camera;FZ)D") )
	private double ComputeTan(double fov){
		ISeeYouOverThereMod.fovTan = CONFIG.fovScaling ? Math.tan(Math.toRadians(fov/2)) : 1;
		return fov;
	}
}
