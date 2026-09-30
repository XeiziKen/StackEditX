package com.zei.maxstack.mixin;

import com.zei.maxstack.MaxStackMod;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "getMaxCount", at = @At("RETURN"), cancellable = true)
    private void modifyMaxCount(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        int currentMax = cir.getReturnValue();
        int newMax = MaxStackMod.getItemMaxCount(stack, currentMax);
        if (newMax != currentMax) {
            cir.setReturnValue(newMax);
        }
    }
}
