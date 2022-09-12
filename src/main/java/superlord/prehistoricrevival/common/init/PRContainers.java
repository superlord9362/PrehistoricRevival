package superlord.prehistoricrevival.common.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.prehistoricrevival.PrehistoricRevival;
import superlord.prehistoricrevival.common.containers.CultivatorContainer;
import superlord.prehistoricrevival.common.containers.DNAAnalyzerContainer;

public class PRContainers {
	
	public static final DeferredRegister<ContainerType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, PrehistoricRevival.MOD_ID);

	public static final RegistryObject<ContainerType<DNAAnalyzerContainer>> DNA_ANALYZER = REGISTER.register("dna_analyzer_container", () -> IForgeContainerType.create(DNAAnalyzerContainer::new));
	public static final RegistryObject<ContainerType<CultivatorContainer>> CULTIVATOR = REGISTER.register("cultivator_container", () -> IForgeContainerType.create(CultivatorContainer::new));

}
