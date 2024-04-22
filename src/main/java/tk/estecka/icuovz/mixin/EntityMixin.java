package tk.estecka.icuovz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;

// Unused mixin
@Mixin(Entity.class)
public class EntityMixin
{
	@Redirect( method="shouldRender(D)Z", at=@At(value="INVOKE", target="net/minecraft/util/math/Box.getAverageSideLength ()D") )
	private double ignoreSize(Box box){
		return 1;
	}
}
