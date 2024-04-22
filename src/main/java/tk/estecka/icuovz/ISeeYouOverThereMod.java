package tk.estecka.icuovz;

import net.fabricmc.api.ModInitializer;
import tk.estecka.icuovz.config.Config;
import tk.estecka.icuovz.config.ConfigIO;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ISeeYouOverThereMod
implements ModInitializer
{
	static public final Logger LOGGER = LoggerFactory.getLogger("ICUOVZ");
	static public final ConfigIO IO = new ConfigIO("ISeeYouOverThere.properties");
	static public final Config CONFIG = new Config();

	static public double fovTan = 1;

	@Override
	public void onInitialize() {
		try {
			IO.GetOrCreate(CONFIG);
		}
		catch (IOException e){
			LOGGER.error(e.toString());
		}
	}

}
