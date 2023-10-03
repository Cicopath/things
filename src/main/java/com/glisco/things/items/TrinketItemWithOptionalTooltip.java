package com.glisco.things.items;

import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class TrinketItemWithOptionalTooltip extends TrinketItem implements ExtendableTooltipProvider {

    public TrinketItemWithOptionalTooltip(Settings settings) {
        super(settings);
    }

    @Override
    public String tooltipTranslationKey() {
        return this.getTranslationKey() + ".tooltip";
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tryAppend(tooltip);
    }
}
