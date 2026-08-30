package tk.estecka.icuovz.config;

import java.io.IOException;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import tk.estecka.icuovz.ISeeYouOverThereMod;
import static tk.estecka.icuovz.ISeeYouOverThereMod.CONFIG;

public class ModMenu
implements ModMenuApi
{
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory(){
		Config defaultConfig = new Config();

		return parent -> {
			final var builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Component.literal("I See You Over There!"));
			final var entries = builder.entryBuilder();
			final var category = builder.getOrCreateCategory(Component.literal("Render Distance"));

			var vanillaScaling = Minecraft.getInstance().options.entityDistanceScaling();

			var eMul = entries.startIntSlider(
					Component.literal("(Vanilla) ").append(Component.translatable("options.entityDistanceScaling")),
					(int)(vanillaScaling.get()*100/25),
					50/25, 500/25
				)
				.setDefaultValue(100/25)
				.setSaveConsumer(i -> vanillaScaling.set(i*25/100d))
				.setTextGetter(i -> Component.literal(String.format("%d%%", i*25)))
				// .setTooltip(Text.translatable("icuovz.config.entity.multiplier.tooltip"))
				.build()
				;

			var eFov = entries.startBooleanToggle(Component.translatable("icuovz.config.fovScaling"), CONFIG.fovScaling)
				.setSaveConsumer(v -> CONFIG.fovScaling = v)
				.setDefaultValue(defaultConfig.fovScaling)
				.setTooltip(Component.translatable("icuovz.config.fovScaling.tooltip"))
				.build()
				;
			var eMin = entries.startIntField(Component.translatable("icuovz.config.entity.min"), CONFIG.entityMin)
				.setMin(0)
				.setSaveConsumer(v -> CONFIG.entityMin = v)
				.setDefaultValue(defaultConfig.entityMin)
				.setTooltip(Component.translatable("icuovz.config.entity.min.tooltip"))
				.build()
				;
			var eMax = entries.startIntField(Component.translatable("icuovz.config.entity.max"), CONFIG.entityMax)
				.setMin(0)
				.setSaveConsumer(v -> CONFIG.entityMax = v)
				.setDefaultValue(defaultConfig.entityMax)
				.setTooltip(Component.translatable("icuovz.config.entity.max.tooltip"))
				.build()
				;

			var bMin = entries.startIntField(Component.translatable("icuovz.config.block.min"), CONFIG.blockMin)
				.setMin(0)
				.setSaveConsumer(v -> CONFIG.blockMin = v)
				.setDefaultValue(defaultConfig.blockMin)
				.setTooltip(Component.translatable("icuovz.config.block.min.tooltip"))
				.build()
				;
			var beam = entries.startIntField(Component.translatable("icuovz.config.block.beacon"), CONFIG.beaconBeam)
				.setMin(0)
				.setSaveConsumer(v -> CONFIG.beaconBeam = v)
				.setDefaultValue(defaultConfig.beaconBeam)
				.setTooltip(Component.translatable("icuovz.config.block.beacon.tooltip"))
				.build()
				;

			category.addEntry(eMul);
			category.addEntry(eFov);
			category.addEntry(eMax);
			category.addEntry(eMin);
			category.addEntry(bMin);
			category.addEntry(beam);

			builder.setSavingRunnable(()->{
				try {
					ISeeYouOverThereMod.IO.Write(CONFIG);
				}
				catch (IOException e) {
					ISeeYouOverThereMod.LOGGER.error("Unable to save config: {}", e);
				}
			});

			return builder.build();
		};
	}
}
