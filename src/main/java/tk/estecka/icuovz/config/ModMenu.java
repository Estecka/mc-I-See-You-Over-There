package tk.estecka.icuovz.config;

import java.io.IOException;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import tk.estecka.icuovz.ISeeYouOverThereMod;
import static tk.estecka.icuovz.ISeeYouOverThereMod.CONFIG;

public class ModMenu
implements ModMenuApi
{
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory(){
		Config defaultConfig = new Config();

		return parent -> {
			final var builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Text.literal("I See You Over There!"));
			final var entries = builder.entryBuilder();
			final var category = builder.getOrCreateCategory(Text.literal("Render Distance"));

			var vanillaScaling = MinecraftClient.getInstance().options.getEntityDistanceScaling();

			var eMul = entries.startIntSlider(
					Text.literal("(Vanilla) ").append(Text.translatable("options.entityDistanceScaling")),
					(int)(vanillaScaling.getValue()*100/25),
					50/25, 500/25
				)
				.setDefaultValue(100/25)
				.setSaveConsumer(i -> vanillaScaling.setValue(i*25/100d))
				.setTextGetter(i -> Text.literal(String.format("%d%%", i*25)))
				// .setTooltip(Text.translatable("icuovz.config.entity.multiplier.tooltip"))
				.build()
				;

			var eFov = entries.startBooleanToggle(Text.translatable("icuovz.config.fovScaling"), CONFIG.fovScaling)
				.setSaveConsumer(v -> CONFIG.fovScaling = v)
				.setDefaultValue(defaultConfig.fovScaling)
				.setTooltip(Text.translatable("icuovz.config.fovScaling.tooltip"))
				.build()
				;
			var eMin = entries.startIntField(Text.translatable("icuovz.config.entity.min"), CONFIG.entityMin)
				.setMin(0)
				.setSaveConsumer(v -> CONFIG.entityMin = v)
				.setDefaultValue(defaultConfig.entityMin)
				.setTooltip(Text.translatable("icuovz.config.entity.min.tooltip"))
				.build()
				;
			var eMax = entries.startIntField(Text.translatable("icuovz.config.entity.max"), CONFIG.entityMax)
				.setMin(0)
				.setSaveConsumer(v -> CONFIG.entityMax = v)
				.setDefaultValue(defaultConfig.entityMax)
				.setTooltip(Text.translatable("icuovz.config.entity.max.tooltip"))
				.build()
				;

			var bMin = entries.startIntField(Text.translatable("icuovz.config.block.min"), CONFIG.blockMin)
				.setMin(0)
				.setSaveConsumer(v -> CONFIG.blockMin = v)
				.setDefaultValue(defaultConfig.blockMin)
				.setTooltip(Text.translatable("icuovz.config.block.min.tooltip"))
				.build()
				;
			var beam = entries.startIntField(Text.translatable("icuovz.config.block.beacon"), CONFIG.beaconBeam)
				.setMin(0)
				.setSaveConsumer(v -> CONFIG.beaconBeam = v)
				.setDefaultValue(defaultConfig.beaconBeam)
				.setTooltip(Text.translatable("icuovz.config.block.beacon.tooltip"))
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
