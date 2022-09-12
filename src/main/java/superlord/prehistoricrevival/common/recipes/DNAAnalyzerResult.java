package superlord.prehistoricrevival.common.recipes;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class DNAAnalyzerResult {
	
	private static final Random random = new Random();
	
	private final NavigableMap<Float, ItemStack> results;
	private final float total;
	
	private DNAAnalyzerResult(NavigableMap<Float, ItemStack> results, float total) {
		this.results = results;
		this.total = total;
	}
	
	public ItemStack next(Random rand) {
		return this.results.higherEntry(rand.nextFloat() * this.total).getValue();
	}
	
	public ItemStack next() {
		return this.next(DNAAnalyzerResult.random);
	}
	
	public static DNAAnalyzerResult read(PacketBuffer buffer) {
		DNAAnalyzerResult.Builder builder = new DNAAnalyzerResult.Builder();
		
		int resultListSize = buffer.readInt();
		for (int i = 0; i < resultListSize; ++i) {
			ItemStack result = buffer.readItemStack();
			float weight = buffer.readFloat();
			builder.add(weight, result);
		}
		return builder.build();
	}
	
	public void write(PacketBuffer buffer) {
		buffer.writeInt(this.results.size());
		for (Entry<Float, ItemStack> entry : this.results.entrySet()) {
			buffer.writeItemStack(entry.getValue());
			buffer.writeFloat(entry.getKey());
		}
	}
	
	public static class Builder {
		private NavigableMap<Float, ItemStack> results = new TreeMap<Float, ItemStack>();
		private float total = 0.0F;
		public Builder add(float weight, ItemStack result) {
			if (weight <= 0) {
				return this;
			}
			this.total += weight;
			this.results.put(this.total, result);
			return this;
		}
		
		public Builder add(ItemStack result) {
			return this.add(1.0F, result);
		}
		
		public DNAAnalyzerResult build() {
			return new DNAAnalyzerResult(this.results, this.total);
		}
	}

}
