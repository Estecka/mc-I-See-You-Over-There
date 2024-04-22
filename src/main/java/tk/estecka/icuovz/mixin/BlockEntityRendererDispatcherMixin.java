package tk.estecka.icuovz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.util.math.Vec3d;
import static tk.estecka.icuovz.ISeeYouOverThereMod.CONFIG;
import static tk.estecka.icuovz.ISeeYouOverThereMod.fovTan;

@Unique
@Mixin(BlockEntityRenderDispatcher.class)
public class BlockEntityRendererDispatcherMixin 
{
	@WrapOperation( method="render", at=@At(value="INVOKE", target="net/minecraft/client/render/block/entity/BlockEntityRenderer.isInRenderDistance (Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/util/math/Vec3d;)Z") )
	private boolean AdjustRenderDistance(BlockEntityRenderer<?> renderer, BlockEntity block, Vec3d camera, Operation<Boolean> original){
		return block.getPos().getSquaredDistance(camera)*fovTan*fovTan <= CONFIG.blockMin*(double)CONFIG.blockMin
		    || original.call(renderer, block,  block.getPos().toCenterPos().lerp(camera, fovTan))
		    ;
	}
}
