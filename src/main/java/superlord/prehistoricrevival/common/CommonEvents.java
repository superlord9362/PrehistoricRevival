package superlord.prehistoricrevival.common;

import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.DonkeyEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.passive.horse.MuleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import superlord.prehistoricfauna.common.entities.DidelphodonEntity;
import superlord.prehistoricfauna.init.PFEntities;
import superlord.prehistoricrevival.PrehistoricRevival;
import superlord.prehistoricrevival.common.init.PRItems;

@Mod.EventBusSubscriber(modid = PrehistoricRevival.MOD_ID)
public class CommonEvents {
	
	@SubscribeEvent
	public static void impregnateMammalEvent(PlayerInteractEvent.EntityInteract event) {
		PlayerEntity player = event.getPlayer();
		ItemStack itemstack = player.getHeldItemMainhand();
		Item item = itemstack.getItem();
		World world = event.getWorld();
		if (item == PRItems.DIDELPHODON_EMBRYO.get()) {
			if (event.getTarget() instanceof CowEntity || event.getTarget() instanceof FoxEntity || event.getTarget() instanceof HorseEntity || event.getTarget() instanceof LlamaEntity || event.getTarget() instanceof DonkeyEntity || event.getTarget() instanceof CatEntity || event.getTarget() instanceof BatEntity || event.getTarget() instanceof MooshroomEntity || event.getTarget() instanceof DolphinEntity || event.getTarget() instanceof WolfEntity || event.getTarget() instanceof PolarBearEntity || event.getTarget() instanceof SheepEntity || event.getTarget() instanceof PigEntity || event.getTarget() instanceof OcelotEntity || event.getTarget() instanceof MuleEntity || event.getTarget() instanceof RabbitEntity || event.getTarget() instanceof PandaEntity) {
				AnimalEntity entity = (AnimalEntity) event.getTarget();
				if (entity.canFallInLove()) {	
					DidelphodonEntity didelphodon = new DidelphodonEntity(PFEntities.DIDELPHODON_ENTITY, world);
					didelphodon.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
					didelphodon.setGrowingAge(-24000);
					world.addEntity(didelphodon);
					entity.setInLove(600);
					if (!player.isCreative()) {
						itemstack.shrink(1);
					}	
				}
			}
		}
	}

}
