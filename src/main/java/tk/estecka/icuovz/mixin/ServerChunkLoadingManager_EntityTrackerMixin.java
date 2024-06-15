package tk.estecka.icuovz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import static tk.estecka.icuovz.ISeeYouOverThereMod.CONFIG;
import static tk.estecka.icuovz.ISeeYouOverThereMod.fovTan;

@Unique
@Mixin(targets={"net/minecraft/server/world/ServerChunkLoadingManager$EntityTracker"})
public class ServerChunkLoadingManager_EntityTrackerMixin
{
	@ModifyReturnValue(method="adjustTrackingDistance", at=@At(value="RETURN"))
	private int ClampMaxTrackingDistance(int original){
		if (original >= CONFIG.entityMax)
			return CONFIG.entityMax;

		double min = CONFIG.entityMin / fovTan;
		original /= fovTan;

		if (original <= min)
			return (int)min;

		return original;
	}
}
