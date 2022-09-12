package superlord.prehistoricrevival.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import superlord.prehistoricrevival.PrehistoricRevival;
import superlord.prehistoricrevival.client.render.gui.CultivatorScreen;
import superlord.prehistoricrevival.client.render.gui.DNAAnalyzerScreen;
import superlord.prehistoricrevival.common.init.PRContainers;
import superlord.prehistoricrevival.common.items.DNASyringeItem;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = PrehistoricRevival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ItemColors itemColors = Minecraft.getInstance().getItemColors();
		for(DNASyringeItem spawneggitem : DNASyringeItem.getDNA()) {
			itemColors.register((stack, color) -> {
	            return spawneggitem.getColor(color);
	         }, spawneggitem);
	      }
		ScreenManager.registerFactory(PRContainers.DNA_ANALYZER.get(), DNAAnalyzerScreen::new);
		ScreenManager.registerFactory(PRContainers.CULTIVATOR.get(), CultivatorScreen::new);
	}

}
