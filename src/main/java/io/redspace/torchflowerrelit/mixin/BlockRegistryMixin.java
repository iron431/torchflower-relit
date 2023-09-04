package io.redspace.torchflowerrelit.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchflowerCropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Blocks.class)
public class BlockRegistryMixin {
    //    @Redirect(method="<init>",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;of()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;"))
//    BlockBehaviour.Properties redirect(){
//        return null;
//    }
//    @ModifyConstant(method="<clinit>", constant = @Constant())
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

}
