package superlord.prehistoricrevival.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import superlord.prehistoricrevival.common.entities.tile.DNAAnalyzerTileEntity;
import superlord.prehistoricrevival.common.init.PRTileEntities;

public class DNAAnalyzerBlock extends Block {
	
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	
	public DNAAnalyzerBlock(Properties builder) {
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(ACTIVE, false));
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return PRTileEntities.DNA_ANALYZER.get().create();
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, ACTIVE);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		if (stack.hasDisplayName()) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof DNAAnalyzerTileEntity) {
				((DNAAnalyzerTileEntity)tile).setCustomName(stack.getDisplayName());
			}
		}
	}
	
	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
		return Container.calcRedstone(world.getTileEntity(pos));
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world != null && !world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof DNAAnalyzerTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider) tile, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
	}
	
	

}
