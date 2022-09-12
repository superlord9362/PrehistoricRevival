package superlord.prehistoricrevival.common.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import superlord.prehistoricrevival.PrehistoricRevival;
import superlord.prehistoricrevival.common.entities.tile.DNAAnalyzerTileEntity;
import superlord.prehistoricrevival.common.init.PRRecipes;

public class RecipeDNAAnalyzer implements IRecipe<IInventory> {
	
	public static final IRecipeType<IRecipe<IInventory>> RECIPE_TYPE_DNA_ANALYZER = IRecipeType.register(PrehistoricRevival.MOD_ID + ":recipe_dna_analyzer");
	
	private final IRecipeSerializer<?> serializer;
	private final IRecipeType<?> type;
	protected final ResourceLocation location;
	protected final String group;
	protected final ItemStack input;
	protected final DNAAnalyzerResult results;
	
	public RecipeDNAAnalyzer(ResourceLocation location, String group, ItemStack input, DNAAnalyzerResult results) {
		this.serializer = PRRecipes.RECIPE_SERIALIZER_DNA_ANALYZER.get();
		this.type = RecipeDNAAnalyzer.RECIPE_TYPE_DNA_ANALYZER;
		this.location = location;
		this.group = group;
		this.input = input;
		this.results = results;
	}
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return this.serializer;
	}
	
	@Override
	public IRecipeType<?> getType() {
		return this.type;
	}
	
	@Override
	public ResourceLocation getId() {
		return this.location;
	}
	
	@Override
	public String getGroup() {
		return this.group;
	}
	
	@Override
	public boolean canFit(int width, int height) {
		return true;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonNullList = NonNullList.create();
		nonNullList.add(Ingredient.fromStacks(this.input.copy()));
		return nonNullList;
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}
	
	@Override
	public ItemStack getCraftingResult(IInventory iInventory) {
		return this.results.next().copy();
	}
	
	@Override
	public boolean matches(IInventory inventory, World world) {
		return this.input.isItemEqual(inventory.getStackInSlot(DNAAnalyzerTileEntity.SLOT_FOSSIL));
	}
	
}
