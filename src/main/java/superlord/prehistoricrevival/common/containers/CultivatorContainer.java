package superlord.prehistoricrevival.common.containers;

import java.util.Objects;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.SlotItemHandler;
import superlord.prehistoricrevival.common.blocks.CultivatorBlock;
import superlord.prehistoricrevival.common.entities.tile.CultivatorTileEntity;
import superlord.prehistoricrevival.common.init.PRContainers;

public class CultivatorContainer extends Container {

	private final IWorldPosCallable canInteractWithCallable;
	private final CultivatorTileEntity tileEntity;

	public CultivatorContainer(final int windowID, final PlayerInventory playerInventory, final CultivatorTileEntity tileEntity) {
		super(PRContainers.CULTIVATOR.get(), windowID);
		this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
		this.tileEntity = tileEntity;
		int playerX = 8;
		int playerY = 84;
		for (int i = 0; i < 9; i++)
			this.addSlot(new Slot(playerInventory, i, playerX + i * 18, playerY + 58));
		for (int i = 0; i < 3; i++)
			for (int k = 0; k < 9; k++)
				this.addSlot(new Slot(playerInventory, k + i * 9 + 9, playerX + k * 18, playerY + i * 18));
		this.addSlot(new SlotItemHandler(this.tileEntity.getItemStackHandler(), CultivatorTileEntity.SLOT_DNA, 36, 50) {
			@Override
			public boolean isItemValid(@Nonnull ItemStack stack) {
				return super.isItemValid(stack) && tileEntity.isDNAStack(stack);
			}
		});
		this.addSlot(new SlotItemHandler(this.tileEntity.getItemStackHandler(), CultivatorTileEntity.SLOT_EGG, 36, 23) {
			@Override
			public boolean isItemValid(@Nonnull ItemStack stack) {
				return super.isItemValid(stack) && tileEntity.isEggStack(stack);
			}
		});
		int resultX = 92;
		int resultY = 41;
		this.addSlot(new CultivatorResultSlot(playerInventory.player, this.tileEntity.getInventory(), CultivatorTileEntity.SLOT_RESULTS[0] + 0 * 3 + 0, resultX + 0 * 18, resultY + 0 * 18));
		this.trackIntArray(this.tileEntity.getIntArray());
	}

	public CultivatorContainer(final int windowID, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowID, playerInventory, CultivatorContainer.getTileEntity(playerInventory, data));
	}

	private static CultivatorTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "Error: " + CultivatorContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + CultivatorContainer.class.getSimpleName() + " - Packet Buffer Data cannot be null!");

		final TileEntity tileEntityAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if (tileEntityAtPos instanceof CultivatorTileEntity) {
			return (CultivatorTileEntity) tileEntityAtPos;
		}
		throw new IllegalStateException("Error: " + CultivatorContainer.class.getSimpleName() + " - TileEntity is not correct! " + tileEntityAtPos);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.canInteractWithCallable.applyOrElse((world, blockPos) -> {
			return world.getBlockState(blockPos).getBlock() instanceof CultivatorBlock ? playerIn.getDistanceSq((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D : false;
		}, true);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		int playerInvSize = this.inventorySlots.size() - this.tileEntity.getInventorySize();
		Slot sourceSlot = (Slot) this.inventorySlots.get(index);
		if (sourceSlot == null || !sourceSlot.getHasStack()) {
			return ItemStack.EMPTY;
		}
		ItemStack sourceStack = sourceSlot.getStack();
		ItemStack copyOfSourceStack = sourceStack.copy();
		if (index >= playerInvSize) {
			if (!this.mergeItemStack(sourceStack, 0, playerInvSize, false)) {
				return ItemStack.EMPTY;
			}
		} else {
			if (this.tileEntity.isDNAStack(sourceStack)) {
				int slotIndex = CultivatorTileEntity.SLOT_DNA;
				if (!this.mergeItemStack(sourceStack, playerInvSize + slotIndex, playerInvSize + slotIndex + 1, false)) {
					return ItemStack.EMPTY;
				}
			} else if (this.tileEntity.isEggStack(sourceStack)) {
				int slotIndex = CultivatorTileEntity.SLOT_EGG;
				if (!this.mergeItemStack(sourceStack, playerInvSize + slotIndex, playerInvSize + slotIndex + 1, false)) {
					return ItemStack.EMPTY;
				}
			}  else {
				return ItemStack.EMPTY;
			}
		}
		if (sourceStack.isEmpty()) {
			sourceSlot.putStack(ItemStack.EMPTY);
		} else {
			sourceSlot.onSlotChanged();
		}
		if (sourceStack.getCount() == copyOfSourceStack.getCount()) {
			return ItemStack.EMPTY;
		}
		sourceSlot.onTake(player, sourceStack);
		return copyOfSourceStack;
	}

	@OnlyIn(Dist.CLIENT)
	public int getWorkProgressionScaled(int size) {
		return this.tileEntity.getWorkProgressionScaled(size);
	}

	public class CultivatorResultSlot extends Slot {
		private final PlayerEntity player;
		private int removeCount;

		public CultivatorResultSlot(PlayerEntity player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
			super(inventoryIn, slotIndex, xPosition, yPosition);
			this.player = player;
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return false;
		}

		@Override
		public ItemStack decrStackSize(int amount) {
			if (this.getHasStack()) {
				this.removeCount += Math.min(amount, this.getStack().getCount());
			}
			return super.decrStackSize(amount);
		}

		@Override
		public ItemStack onTake(PlayerEntity player, ItemStack stack) {
			this.onCrafting(stack);
			super.onTake(player, stack);
			return stack;
		}

		@Override
		protected void onCrafting(ItemStack stack) {
			stack.onCrafting(this.player.world, this.player, this.removeCount);
			if (!this.player.world.isRemote && this.inventory instanceof CultivatorTileEntity) {
				((CultivatorTileEntity) this.inventory).givePlayerXP(player, this.removeCount);
			}
			this.removeCount = 0;
		}
	}

}
