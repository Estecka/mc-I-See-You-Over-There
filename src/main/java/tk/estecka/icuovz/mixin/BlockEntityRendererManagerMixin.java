package tk.estecka.icuovz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import static tk.estecka.icuovz.ISeeYouOverThereMod.CONFIG;
import static tk.estecka.icuovz.ISeeYouOverThereMod.fovTan;

/**
 * @deprecated Feature may be dropped in a future release. Is redundant with the
 * mod "Enhanced Block Entities", which does the same thing but better.
 */
@Unique
@Mixin(BlockEntityRenderDispatcher.class)
public class BlockEntityRendererManagerMixin
{
	@WrapOperation(
		method = "tryExtractRenderState",
		at = @At(
			value = "INVOKE",
			target = "net/minecraft/client/renderer/blockentity/BlockEntityRenderer.shouldRender (Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/phys/Vec3;)Z"
		)
	)
	private boolean AdjustRenderDistance(BlockEntityRenderer<?, ?> renderer, BlockEntity block, Vec3 camera, Operation<Boolean> original){
		return block.getBlockPos().distToCenterSqr(camera)*fovTan*fovTan <= CONFIG.blockMin*(double)CONFIG.blockMin
		    || original.call(renderer, block, Vec3.atCenterOf(block.getBlockPos()).lerp(camera, fovTan))
		    ;
	}
}
