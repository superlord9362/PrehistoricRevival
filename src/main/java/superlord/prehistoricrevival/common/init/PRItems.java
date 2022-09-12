package superlord.prehistoricrevival.common.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.prehistoricfauna.PrehistoricFauna.PFMisc;
import superlord.prehistoricrevival.PrehistoricRevival;
import superlord.prehistoricrevival.PrehistoricRevival.PRDNA;
import superlord.prehistoricrevival.common.items.DNASyringeItem;

public class PRItems {
	
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, PrehistoricRevival.MOD_ID);
	
	public static final RegistryObject<Item> DNA_ANALYZER = REGISTER.register("dna_extractor", () -> new BlockItem(PRBlocks.DNA_ANALYZER.get(), new Item.Properties().group(PFMisc.instance)));
	public static final RegistryObject<Item> CULTIVATOR = REGISTER.register("cultivator", () -> new BlockItem(PRBlocks.CULTIVATOR.get(), new Item.Properties().group(PFMisc.instance)));

	public static final RegistryObject<Item> ANKYLOSAURUS_DNA = REGISTER.register("ankylosaurus_dna", () -> new DNASyringeItem(1, 0x202C0C, 0x908730, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> BASILEMYS_DNA = REGISTER.register("basilemys_dna", () -> new DNASyringeItem(2, 0x6B3727, 0x373519, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> DAKOTARAPTOR_DNA = REGISTER.register("dakotaraptor_dna", () -> new DNASyringeItem(3, 0x453018, 0x986529,new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> DIDELPHODON_DNA = REGISTER.register("didelphodon_dna", () -> new DNASyringeItem(4, 0x3E2419, 0xAF9663, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> THESCELOSAURUS_DNA = REGISTER.register("thescelosaurus_dna", () -> new DNASyringeItem(5, 0x582C20, 0x496659, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> TRICERATOPS_DNA = REGISTER.register("triceratops_dna", () -> new DNASyringeItem(6, 0x494427, 0xABA37B, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> TYRANNOSAURUS_DNA = REGISTER.register("tyrannosaurus_dna", () -> new DNASyringeItem(7, 0x56483E, 0x614C38, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> ALLOSAURUS_DNA = REGISTER.register("allosaurus_dna", () -> new DNASyringeItem(8, 0x5E5D2D, 0x643F23, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> CAMARASAURUS_DNA = REGISTER.register("camarasaurus_dna", () -> new DNASyringeItem(9, 0x7E5E2D, 0x7E311C, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> CERATOSAURUS_DNA = REGISTER.register("ceratosaurus_dna", () -> new DNASyringeItem(10, 0x352217, 0x4056A0, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> DRYOSAURUS_DNA = REGISTER.register("dryosaurus_dna", () -> new DNASyringeItem(11, 0x8E6746, 0x629698, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> EILENODON_DNA = REGISTER.register("eilenodon_dna", () -> new DNASyringeItem(12, 0xA57B48, 0x84A36A, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> HESPERORNITHOIDES_DNA = REGISTER.register("hesperornithoides_dna", () -> new DNASyringeItem(13, 0x1F667D, 0x6F97A5, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> STEGOSAURUS_DNA = REGISTER.register("stegosaurus_dna", () -> new DNASyringeItem(14, 0xB0A047, 0x875D2A, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> CHROMOGISAURUS_DNA = REGISTER.register("chromogisaurus_dna", () -> new DNASyringeItem(15, 0x513935, 0x4E594B, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> EXAERETODON_DNA = REGISTER.register("exaeretodon_dna", () -> new DNASyringeItem(16, 0x473023, 0xDBC7BA, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> HERRERASAURUS_DNA = REGISTER.register("herrerasaurus_dna", () -> new DNASyringeItem(17, 0x372721, 0xE7E0C9, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> HYPERODAPEDON_DNA = REGISTER.register("hyperodapedon_dna", () -> new DNASyringeItem(18, 0x3A1F18, 0xAF9586, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> SAUROSUCHUS_DNA = REGISTER.register("saurosuchus_dna", () -> new DNASyringeItem(19, 0x4F2622, 0x8E4F34, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> ISCHIGUALASTIA_DNA = REGISTER.register("ischigualastia_dna", () -> new DNASyringeItem(20, 0x242820, 0x808776, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> SILLOSUCHUS_DNA = REGISTER.register("sillosuchus_dna", () -> new DNASyringeItem(21, 0x2F2E27, 0x58301B, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> MYLEDAPHUS_DNA = REGISTER.register("myledaphus_dna", () -> new DNASyringeItem(22, 0x896D48, 0xC4B087, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> GAR_DNA = REGISTER.register("gar_dna", () -> new DNASyringeItem(23, 0x442E1E, 0x947442, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> CYCLURUS_DNA = REGISTER.register("cyclurus_dna", () -> new DNASyringeItem(24, 0x5E6B3B, 0x968B53, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> CERATODUS_DNA = REGISTER.register("ceratodus_dna", () -> new DNASyringeItem(25, 0x3A3822, 0x969373, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> POTAMOCERATODUS_DNA = REGISTER.register("potamoceratodus_dna", () -> new DNASyringeItem(26, 0x302418, 0xAF621A, new Item.Properties().group(PRDNA.instance)));

	public static final RegistryObject<Item> ARAUCARIA_DNA = REGISTER.register("araucaria_dna", () -> new DNASyringeItem(27, 0x52672A, 0x1D330F, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> CLADOPHLEBIS_DNA = REGISTER.register("cladophlebis_dna", () -> new DNASyringeItem(28, 0x3C692C, 0x162610, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> CLUBMOSS_DNA = REGISTER.register("clubmoss_dna", () -> new DNASyringeItem(29, 0xA6CB33, 0x1C270A, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> CONIOPTERIS_DNA = REGISTER.register("coniopteris_dna", () -> new DNASyringeItem(30, 0x969F2E, 0x2B2E0B, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> DICROIDIUM_DNA = REGISTER.register("dicroidium_dna", () -> new DNASyringeItem(31, 0x375134, 0x261B11, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> HORSETAIL_DNA = REGISTER.register("horsetail_dna", () -> new DNASyringeItem(32, 0x648E25, 0x292D11, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> OSMUNDA_DNA = REGISTER.register("osmunda_dna", () -> new DNASyringeItem(33, 0xB6540B, 0x2B3E10, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> HEIDIPHYLLUM_DNA = REGISTER.register("heidiphyllum_dna", () -> new DNASyringeItem(34, 0x7F813C, 0x3F3231, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> JOHNSTONIA_DNA = REGISTER.register("johnstonia_dna", () -> new DNASyringeItem(35, 0x4A7030, 0x422915, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> LIRIODENDRITES_DNA = REGISTER.register("liriodendrites_dna", () -> new DNASyringeItem(36, 0x749B24, 0x5B5143, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> METASEQUOIA_DNA = REGISTER.register("metasequoia_dna", () -> new DNASyringeItem(37, 0x728223, 0x613513, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> MICHELILLOA_DNA = REGISTER.register("michelilloa_dna", () -> new DNASyringeItem(38, 0x8C885B, 0x192B0D, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> OSMUNDACAULIS_DNA = REGISTER.register("osmundacaulis_dna", () -> new DNASyringeItem(39, 0xC2A955, 0x342517, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> PROTOPICEOXYLON_DNA = REGISTER.register("protopiceoxylon_dna", () -> new DNASyringeItem(40, 0x98923B, 0x40241C, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> PTILOPHYLLUM_DNA = REGISTER.register("ptilophyllum_dna", () -> new DNASyringeItem(41, 0x999C37, 0x5E3A20, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> SCYTOPHYLLUM_DNA = REGISTER.register("scytophyllum_dna", () -> new DNASyringeItem(42, 0x8B2813, 0x24330F, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> ZAMITES_DNA = REGISTER.register("zamites_dna", () -> new DNASyringeItem(43, 0xB4B615, 0x5E4A30, new Item.Properties().group(PRDNA.instance)));
	public static final RegistryObject<Item> PROTOJUNIPEROXYLON_DNA = REGISTER.register("protojuniperoxylon_dna", () -> new DNASyringeItem(44, 0x435B4A, 0x272830, new Item.Properties().group(PRDNA.instance)));

	public static final RegistryObject<Item> DIDELPHODON_EMBRYO = REGISTER.register("didelphodon_embryo", () -> new Item(new Item.Properties().group(PRDNA.instance)));

}
