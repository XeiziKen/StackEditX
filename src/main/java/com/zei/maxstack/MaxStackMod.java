package com.zei.maxstack;

import com.zei.maxstack.config.ModConfig;
import com.zei.maxstack.mixin.ItemAccessor;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.Registries;

public class MaxStackMod implements ModInitializer {

    @Override
    public void onInitialize() {
        applyStackSize();
    }

    public static void applyStackSize() {
        for (Item item : Registries.ITEM) {
            boolean isEquipment = item instanceof ArmorItem
                    || item instanceof SwordItem
                    || item instanceof MiningToolItem
                    || item instanceof RangedWeaponItem
                    || item instanceof TridentItem
                    || item.isDamageable();

            if (!isEquipment) {
                ((ItemAccessor) item).setMaxCount(ModConfig.maxStackSize);
            }
        }
    }

    public static int getItemMaxCount(ItemStack stack, int defaultMaxCount) {
        Item item = stack.getItem();
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            if (block instanceof ShulkerBoxBlock) {
                ContainerComponent container = stack.getOrDefault(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT);
                if (!container.copy().isEmpty()) {
                    return 1;
                }
            }
        }
        return defaultMaxCount;
    }
  }
