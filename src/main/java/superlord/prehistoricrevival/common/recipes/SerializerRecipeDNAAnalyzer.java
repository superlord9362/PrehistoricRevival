package superlord.prehistoricrevival.common.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SerializerRecipeDNAAnalyzer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<RecipeDNAAnalyzer> {
	
	protected final IRecipeFactory<RecipeDNAAnalyzer> factory;
	
	public SerializerRecipeDNAAnalyzer(IRecipeFactory<RecipeDNAAnalyzer> factory) {
		this.factory = factory;
	}
	
	@Override
	public RecipeDNAAnalyzer read(ResourceLocation location, JsonObject json) {
		String group = JSONUtils.getString(json, "group", "");
		ItemStack input = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "input"));
		
		DNAAnalyzerResult.Builder builder = new DNAAnalyzerResult.Builder();
		JsonArray resultArray = JSONUtils.getJsonArray(json, "results");
		for (JsonElement resultElement : resultArray) {
			JsonObject resultEntry = resultElement.getAsJsonObject();
			builder.add(JSONUtils.hasField(resultEntry, "weight") ? JSONUtils.getFloat(resultEntry.get("weight"), "weight") : 1.0F, ShapedRecipe.deserializeItem(resultEntry));
		}
		return this.factory.create(location, group, input, builder.build());
	}
	
	@Override
	public RecipeDNAAnalyzer read(ResourceLocation location, PacketBuffer buffer) {
		String group = buffer.readString(47295);
		ItemStack input = buffer.readItemStack();
		DNAAnalyzerResult results = DNAAnalyzerResult.read(buffer);
		return this.factory.create(location, group, input, results);
	}
	
	@Override
	public void write(PacketBuffer buffer, RecipeDNAAnalyzer recipe) {
		buffer.writeString(recipe.group);
		buffer.writeItemStack(recipe.input);
		recipe.results.write(buffer);
	}
	
	public interface IRecipeFactory<T extends RecipeDNAAnalyzer> {
		T create(ResourceLocation location, String group, ItemStack input, DNAAnalyzerResult results);
	}
	
}
