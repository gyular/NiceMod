package ru.miuno.nicemod.blocks.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class WallLantern extends HorizontalFacingBlock {
	public WallLantern(Settings settings) {
		super(Settings.copy(Blocks.LANTERN).nonOpaque());
		setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(Properties.HORIZONTAL_FACING);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
		Direction dir = state.get(FACING);
		switch(dir) {
			case NORTH:
				return VoxelShapes.union(VoxelShapes.cuboid(0.3, 0, 0, 0.7, 1, 1));
			case SOUTH:
				return VoxelShapes.union(VoxelShapes.cuboid(0.3, 0, 0, 0.7, 1, 1));
			case EAST:
				return VoxelShapes.union(VoxelShapes.cuboid(0, 0, 0.3, 1, 1, 0.7));
			case WEST:
				return VoxelShapes.union(VoxelShapes.cuboid(0, 0, 0.3, 1, 1, 0.7));
			default:
				return VoxelShapes.fullCube();
		}
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
	}
	@Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
    }
}
