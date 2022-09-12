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
import superlord.prehistoricrevival.common.entities.tile.CultivatorTileEntity;
import superlord.prehistoricrevival.common.init.PRRecipes;

public class RecipeCultivator implements IRecipe<IInventory> {
	
	public static final IRecipeType<IRecipe<IInventory>> RECIPE_TYPE_CULTIVATOR = IRecipeType.register(PrehistoricRevival.MOD_ID + ":recipe_cultivator");
	
	private final IRecipeSerializer<?> serializer;
	private final IRecipeType<?> type;
	protected final ResourceLocation location;
	protected final String group;
	protected final ItemStack input;
	protected final Ingredient eggInput;
	protected final CultivatorResult results;
	
	public RecipeCultivator(ResourceLocation location, String group, ItemStack input, Ingredient eggInput, CultivatorResult results) {
		this.serializer = PRRecipes.RECIPE_SERIALIZER_CULTIVATOR.get();
		this.type = RecipeCultivator.RECIPE_TYPE_CULTIVATOR;
		this.location = location;
		this.group = group;
		this.input = input;
		this.eggInput = eggInput;
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
		nonNullList.add(this.eggInput);
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
		return this.input.isItemEqual(inventory.getStackInSlot(CultivatorTileEntity.SLOT_DNA)) && this.eggInput.test(inventory.getStackInSlot(CultivatorTileEntity.SLOT_EGG));
	}
	
}
