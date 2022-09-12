package superlord.prehistoricrevival.common.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.prehistoricrevival.PrehistoricRevival;
import superlord.prehistoricrevival.common.entities.tile.CultivatorTileEntity;
import superlord.prehistoricrevival.common.entities.tile.DNAAnalyzerTileEntity;

public class PRTileEntities {
	
	public static final DeferredRegister<TileEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, PrehistoricRevival.MOD_ID);

	public static final RegistryObject<TileEntityType<DNAAnalyzerTileEntity>> DNA_ANALYZER = REGISTER.register("dna_analyzer_tile_entity", () -> TileEntityType.Builder.create(DNAAnalyzerTileEntity::new, PRBlocks.DNA_ANALYZER.get()).build(null));
	public static final RegistryObject<TileEntityType<CultivatorTileEntity>> CULTIVATOR = REGISTER.register("cultivator_tile_entity", () -> TileEntityType.Builder.create(CultivatorTileEntity::new, PRBlocks.CULTIVATOR.get()).build(null));

}
