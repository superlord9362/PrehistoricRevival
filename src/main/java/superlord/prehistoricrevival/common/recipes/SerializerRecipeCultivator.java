package superlord.prehistoricrevival.common.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SerializerRecipeCultivator extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<RecipeCultivator> {
	
	protected final IRecipeFactory<RecipeCultivator> factory;
	
	public SerializerRecipeCultivator(IRecipeFactory<RecipeCultivator> factory) {
		this.factory = factory;
	}
	
	@Override
	public RecipeCultivator read(ResourceLocation location, JsonObject json) {
		String group = JSONUtils.getString(json, "group", "");
		ItemStack input = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "input"));
		Ingredient eggInput = Ingredient.deserialize(JSONUtils.getJsonObject(json, "eggInput"));
		
		CultivatorResult.Builder builder = new CultivatorResult.Builder();
		JsonArray resultArray = JSONUtils.getJsonArray(json, "results");
		for (JsonElement resultElement : resultArray) {
			JsonObject resultEntry = resultElement.getAsJsonObject();
			builder.add(JSONUtils.hasField(resultEntry, "weight") ? JSONUtils.getFloat(resultEntry.get("weight"), "weight") : 1.0F, ShapedRecipe.deserializeItem(resultEntry));
		}
		return this.factory.create(location, group, input, eggInput, builder.build());
	}
	
	@Override
	public RecipeCultivator read(ResourceLocation location, PacketBuffer buffer) {
		String group = buffer.readString(47295);
		ItemStack input = buffer.readItemStack();
		Ingredient eggInput = Ingredient.read(buffer);
		CultivatorResult results = CultivatorResult.read(buffer);
		return this.factory.create(location, group, input, eggInput, results);
	}
	
	@Override
	public void write(PacketBuffer buffer, RecipeCultivator recipe) {
		buffer.writeString(recipe.group);
		buffer.writeItemStack(recipe.input);
		recipe.eggInput.write(buffer);
		recipe.results.write(buffer);
	}
	
	public interface IRecipeFactory<T extends RecipeCultivator> {
		T create(ResourceLocation location, String group, ItemStack input, Ingredient eggInput, CultivatorResult results);
	}
	
}
