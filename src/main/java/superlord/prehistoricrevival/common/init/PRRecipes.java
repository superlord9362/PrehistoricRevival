package superlord.prehistoricrevival.common.init;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.prehistoricrevival.PrehistoricRevival;
import superlord.prehistoricrevival.common.recipes.RecipeCultivator;
import superlord.prehistoricrevival.common.recipes.RecipeDNAAnalyzer;
import superlord.prehistoricrevival.common.recipes.SerializerRecipeCultivator;
import superlord.prehistoricrevival.common.recipes.SerializerRecipeDNAAnalyzer;

public class PRRecipes {
	
	public static final DeferredRegister<IRecipeSerializer<?>> REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PrehistoricRevival.MOD_ID);
	
	public static final RegistryObject<SerializerRecipeDNAAnalyzer> RECIPE_SERIALIZER_DNA_ANALYZER = REGISTER.register("recipe_dna_analyzer", () -> new SerializerRecipeDNAAnalyzer(RecipeDNAAnalyzer::new));
	public static final RegistryObject<SerializerRecipeCultivator> RECIPE_SERIALIZER_CULTIVATOR = REGISTER.register("recipe_cultivator", () -> new SerializerRecipeCultivator(RecipeCultivator::new));

}
