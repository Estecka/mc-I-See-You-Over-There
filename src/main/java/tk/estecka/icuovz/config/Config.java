package tk.estecka.icuovz.config;

import java.util.HashMap;
import java.util.Map;
import tk.estecka.icuovz.config.ConfigIO.Property;

public class Config
extends ConfigIO.AFixedCoded
{
	public int entityMin = 128;
	public int entityMax = 512;
	public boolean fovScaling = true;

	public int blockMin = 256;
	public int beaconBeam = 512;

	public Map<String, Property<?>> GetProperties(){
		return new HashMap<>(){{
			put("entity.min", Property.Integer(()->entityMin, i->entityMin=i));
			put("entity.max", Property.Integer(()->entityMax, i->entityMax=i));
			put("fovScaling", Property.Boolean( ()->fovScaling, b->fovScaling=b));

			put("block.min", Property.Integer(()->blockMin, i->blockMin=i));
			put("block.beacon", Property.Integer(()->blockMin, i->blockMin=i));
		}};
	}
}
