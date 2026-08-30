package tk.estecka.icuovz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import static tk.estecka.icuovz.ISeeYouOverThereMod.CONFIG;
import static tk.estecka.icuovz.ISeeYouOverThereMod.fovTanInverse;

@Unique
@Mixin(targets={"net/minecraft/server/level/ChunkMap$TrackedEntity"})
public class ServerChunkLoadingManager_EntityTrackerMixin
{
	// Mojmap scaledRange == Yarn adjustTrackingDistance
	@ModifyReturnValue(method="scaledRange", at=@At(value="RETURN"))
	private int ClampMaxTrackingDistance(int original){
		if (original >= CONFIG.entityMax)
			return CONFIG.entityMax;
		if (original <= CONFIG.entityMin)
			return (int)(CONFIG.entityMin * fovTanInverse);
		else
			return (int)(original * fovTanInverse);
	}
}
