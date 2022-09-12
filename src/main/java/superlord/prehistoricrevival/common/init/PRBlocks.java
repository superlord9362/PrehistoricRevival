package superlord.prehistoricrevival.common.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.prehistoricrevival.PrehistoricRevival;
import superlord.prehistoricrevival.common.blocks.CultivatorBlock;
import superlord.prehistoricrevival.common.blocks.DNAAnalyzerBlock;

public class PRBlocks {
	
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, PrehistoricRevival.MOD_ID);

	public static final RegistryObject<Block> DNA_ANALYZER = REGISTER.register("dna_extractor", () -> new DNAAnalyzerBlock(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> CULTIVATOR = REGISTER.register("cultivator", () -> new CultivatorBlock(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.METAL).setRequiresTool()));
	
}
