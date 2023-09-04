package io.redspace.torchflowerrelit.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchflowerCropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Blocks.class)
public class BlockRegistryMixin {
    //https://fabricmc.net/wiki/tutorial:mixin_examples#injecting_with_a_slice
    @ModifyArg(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/FlowerBlock;<init>(Lnet/minecraft/world/effect/MobEffect;ILnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V"),
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=torchflower"),
                    to = @At(value = "CONSTANT", args = "stringValue=poppy")
            ))
    private static BlockBehaviour.Properties torchflowerBlockLightLevel(BlockBehaviour.Properties properties) {
        return properties.lightLevel(blockState -> 15);
    }

    //Only one torchflowercrop constructor, so no slice necessary
    @ModifyArg(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/TorchflowerCropBlock;<init>(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V")
    )
    private static BlockBehaviour.Properties torchflowerCropBlockLightLevel(BlockBehaviour.Properties properties) {
        return properties.lightLevel(blockState -> blockState.getValue(TorchflowerCropBlock.AGE) * 5);
    }

    @ModifyArg(method = "flowerPot(Lnet/minecraft/world/level/block/Block;[Lnet/minecraft/world/flag/FeatureFlag;)Lnet/minecraft/world/level/block/FlowerPotBlock;",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/FlowerPotBlock;<init>(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V")
    )
    private static BlockBehaviour.Properties torchflowerPotLightLevel(Block block, BlockBehaviour.Properties properties) {
        return block.equals(Blocks.TORCHFLOWER) ? properties.lightLevel(blockState -> 15) : properties;
    }
}
