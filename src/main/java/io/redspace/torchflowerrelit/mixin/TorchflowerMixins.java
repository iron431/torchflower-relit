package io.redspace.torchflowerrelit.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TorchflowerCropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TorchflowerCropBlock.class)
public abstract class TorchflowerMixins implements IForgeBlock {
    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos)
    {
        return 15;
    }
}
