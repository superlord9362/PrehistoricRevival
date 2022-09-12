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
import superlord.prehistoricrevival.common.blocks.DNAAnalyzerBlock;
import superlord.prehistoricrevival.common.entities.tile.DNAAnalyzerTileEntity;
import superlord.prehistoricrevival.common.init.PRContainers;

public class DNAAnalyzerContainer extends Container {
	
	private final IWorldPosCallable canInteractWithCallable;
	private final DNAAnalyzerTileEntity tileEntity;
	
	public DNAAnalyzerContainer(final int windowID, final PlayerInventory playerInventory, final DNAAnalyzerTileEntity tileEntity) {
		super(PRContainers.DNA_ANALYZER.get(), windowID);
		this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
		this.tileEntity = tileEntity;
		int playerX = 8;
		int playerY = 84;
		for (int i = 0; i < 9; i++)
			this.addSlot(new Slot(playerInventory, i, playerX + i * 18, playerY + 58));
		for (int i = 0; i < 3; i++)
			for (int k = 0; k < 9; k++)
				this.addSlot(new Slot(playerInventory, k + i * 9 + 9, playerX + k * 18, playerY + i * 18));
		this.addSlot(new SlotItemHandler(this.tileEntity.getItemStackHandler(), DNAAnalyzerTileEntity.SLOT_FOSSIL, 36, 42) {
			@Override
			public boolean isItemValid(@Nonnull ItemStack stack) {
				return super.isItemValid(stack) && tileEntity.isFossilStack(stack);
			}
		});
		int resultX = 92;
		int resultY = 23;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.addSlot(new DNAAnalyzerResultSlot(playerInventory.player, this.tileEntity.getInventory(), DNAAnalyzerTileEntity.SLOT_RESULTS[0] + j * 3 + i, resultX + i * 18, resultY + j * 18));
			}
		}
		this.trackIntArray(this.tileEntity.getIntArray());
	}
	
	public DNAAnalyzerContainer(final int windowID, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowID, playerInventory, DNAAnalyzerContainer.getTileEntity(playerInventory, data));
	}
	
	private static DNAAnalyzerTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "Error: " + DNAAnalyzerContainer.class.getSimpleName() + " - Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Error: " + DNAAnalyzerContainer.class.getSimpleName() + " - Packet Buffer Data cannot be null!");
		
		final TileEntity tileEntityAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if (tileEntityAtPos instanceof DNAAnalyzerTileEntity) {
			return (DNAAnalyzerTileEntity) tileEntityAtPos;
		}
		throw new IllegalStateException("Error: " + DNAAnalyzerContainer.class.getSimpleName() + " - TileEntity is not correct! " + tileEntityAtPos);
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.canInteractWithCallable.applyOrElse((world, blockPos) -> {
			return world.getBlockState(blockPos).getBlock() instanceof DNAAnalyzerBlock ? playerIn.getDistanceSq((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D) <= 64.0D : false;
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
			if (this.tileEntity.isFossilStack(sourceStack)) {
				int slotIndex = DNAAnalyzerTileEntity.SLOT_FOSSIL;
				if (!this.mergeItemStack(sourceStack, playerInvSize + slotIndex, playerInvSize + slotIndex + 1, false)) {
					return ItemStack.EMPTY;
				}
			} else {
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
	
	public class DNAAnalyzerResultSlot extends Slot {
		private final PlayerEntity player;
		private int removeCount;
		
		public DNAAnalyzerResultSlot(PlayerEntity player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
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
			if (!this.player.world.isRemote && this.inventory instanceof DNAAnalyzerTileEntity) {
				((DNAAnalyzerTileEntity) this.inventory).givePlayerXP(player, this.removeCount);
			}
			this.removeCount = 0;
		}
	}

}
