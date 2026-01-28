package net.zero.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.zero.tutorialmod.item.ModItems;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.Direction;
import net.minecraft.block.HorizontalFacingBlock;
import net.zero.tutorialmod.sound.ModSounds;

import java.util.Set;

import static net.minecraft.state.property.Properties.POWERED;

public class KeyCardReaderBlock extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private final Set<Item> validKeyCards;

    public KeyCardReaderBlock(Settings settings, Set<Item> validKeyCards) {
        super(settings);
        this.validKeyCards = validKeyCards;

        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(POWERED, false)
                .with(FACING, Direction.NORTH));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos,
                                 PlayerEntity player, BlockHitResult hit) {


        if (state.get(Properties.POWERED)) {
            player.sendMessage(
                    Text.literal("Терминал уже активирован")
                            .formatted(Formatting.RED),
                    true
            );
            return ActionResult.SUCCESS;
        }

        if (!validKeyCards.contains(player.getMainHandStack().getItem())) {
            player.sendMessage(
                    Text.literal("Нужна подходящая карта доступа")
                            .formatted(Formatting.RED),
                    true
            );
            return ActionResult.FAIL;
        }



        if (!world.isClient) {
            world.setBlockState(pos, state.with(POWERED, true), Block.NOTIFY_ALL);
            world.updateNeighbors(pos, this);

            world.playSound(
                    null,
                    pos,
                    ModSounds.KEYCARD_READER_USE,
                    net.minecraft.sound.SoundCategory.BLOCKS,
                    1.0f,
                    1.0f
            );


            if (!player.isCreative()) {
                player.getMainHandStack().decrement(1);
            }


            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hit);
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }


    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, net.minecraft.world.BlockView world, BlockPos pos, net.minecraft.util.math.Direction direction) {
        return state.get(POWERED) ? 15 : 0;
    }

    @Override
    protected void appendProperties(net.minecraft.state.StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, FACING);
    }



}