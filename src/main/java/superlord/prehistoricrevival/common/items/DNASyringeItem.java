package superlord.prehistoricrevival.common.items;

import java.util.Map;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DNASyringeItem extends Item {

	private static final Map<Integer, DNASyringeItem> DNA = Maps.newIdentityHashMap();
	private final int primaryColor;
	private final int secondaryColor;

	public DNASyringeItem(int dna, int primaryColorIn, int secondaryColorIn, Properties properties) {
		super(properties);
		this.primaryColor = primaryColorIn;
		this.secondaryColor = secondaryColorIn;
		DNA.put(dna, this);
	}

	@OnlyIn(Dist.CLIENT)
	public int getColor(int tintIndex) {
		if (tintIndex == 0) {
			return this.primaryColor;
		} else if (tintIndex == 1) {
			return this.secondaryColor;
		} else {
			return 0x999999;
		}
	}

	public static Iterable<DNASyringeItem> getDNA() {
		return Iterables.unmodifiableIterable(DNA.values());
	}

}
