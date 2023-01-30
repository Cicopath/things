package com.glisco.things.items.trinkets;

import com.glisco.things.Things;
import com.glisco.things.items.TrinketItemWithOptionalTooltip;
import dev.emi.trinkets.api.SlotReference;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class RabbitFootCharmItem extends TrinketItemWithOptionalTooltip {

    public RabbitFootCharmItem() {
        super(new OwoItemSettings().maxCount(1).group(Things.THINGS_GROUP));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (!(entity instanceof ServerPlayerEntity player)) return;

        player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 5, 1, true, false, true));
    }
}