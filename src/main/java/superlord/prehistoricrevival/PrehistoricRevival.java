package superlord.prehistoricrevival;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.prehistoricrevival.common.init.PRBlocks;
import superlord.prehistoricrevival.common.init.PRContainers;
import superlord.prehistoricrevival.common.init.PRItems;
import superlord.prehistoricrevival.common.init.PRRecipes;
import superlord.prehistoricrevival.common.init.PRTileEntities;

@Mod(PrehistoricRevival.MOD_ID)
public class PrehistoricRevival {

	public static final String MOD_ID = "prehistoric_revival";

	public PrehistoricRevival() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		PRBlocks.REGISTER.register(modEventBus);
		PRItems.REGISTER.register(modEventBus);
		PRContainers.REGISTER.register(modEventBus);
		PRTileEntities.REGISTER.register(modEventBus);
		PRRecipes.REGISTER.register(modEventBus);
	}
	
	public static class PRDNA extends ItemGroup {
		public static final PRDNA instance = new PRDNA(ItemGroup.GROUPS.length, "prehistoric_dna_tab");

		private PRDNA(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(PRItems.TYRANNOSAURUS_DNA.get());
		}
	}

}
