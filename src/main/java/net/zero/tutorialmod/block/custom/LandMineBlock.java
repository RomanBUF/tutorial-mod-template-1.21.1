package net.zero.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.sound.Sound;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class LandMineBlock extends Block {

    private static final VoxelShape BOTTOM = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 1.0, 12.0);
    private static final VoxelShape TOP = Block.createCuboidShape(5.0, 1.0, 5.0, 11.0, 2.0, 11.0);

    private static final VoxelShape SHAPE = VoxelShapes.union(BOTTOM, TOP);

    public LandMineBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient) {


            world.createExplosion(
                    null,
                    pos.getX() + 0.5,
                    pos.getY() + 0.1,
                    pos.getZ() + 0.5,
                    0.5f,
                    World.ExplosionSourceType.NONE
            );

            world.removeBlock(pos, false);

            world.playSoundAtBlockCenter(
                    pos,
                    net.minecraft.sound.SoundEvents.ENTITY_GENERIC_EXPLODE.value(),
                    net.minecraft.sound.SoundCategory.BLOCKS,
                    1.0f,
                    1.0f,
                    true
            );
            entity.timeUntilRegen = 0;
            entity.damage(world.getDamageSources().explosion(null, null), 20.0f);
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}