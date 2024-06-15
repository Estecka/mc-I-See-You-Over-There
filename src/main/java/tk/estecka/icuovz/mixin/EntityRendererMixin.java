package tk.estecka.icuovz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import static tk.estecka.icuovz.ISeeYouOverThereMod.CONFIG;
import static tk.estecka.icuovz.ISeeYouOverThereMod.fovTan;

/**
 * Considering moving this logic into the `EntityRendererDispatcher` instead. If
 * this ever  becomes a problem, this would let the logic  work around potential
 * overrides in `EntityRenderer` sub-classes.
 */
@Unique
@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity>
{
	@WrapOperation( method="shouldRender", at=@At(value="INVOKE", target="net/minecraft/entity/Entity.shouldRender (DDD)Z") )
	private boolean ClampRenderDistance(Entity entity, double camX, double camY, double camZ, Operation<Boolean> original, @Local(argsOnly=true) Frustum frustrum){
		double sqrDist = entity.squaredDistanceTo(camX, camY, camZ);
		return sqrDist<=CONFIG.entityMax*(double)CONFIG.entityMax
		    && ( sqrDist*fovTan*fovTan<=CONFIG.entityMin*(double)CONFIG.entityMin || ScaledShouldRender(entity,camX,camY,camZ,original) )
		    ;
	}

	// Scales render distance by virtually changing the distance from the entity to the camera
	static private boolean ScaledShouldRender(Entity entity, double camX, double camY, double camZ, Operation<Boolean> original){
		return original.call(
			entity,
			MathHelper.lerp(fovTan, entity.getX(), camX),
			MathHelper.lerp(fovTan, entity.getY(), camY),
			MathHelper.lerp(fovTan, entity.getZ(), camZ)
		);
	}

}
