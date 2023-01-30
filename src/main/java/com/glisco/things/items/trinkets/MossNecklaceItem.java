package com.glisco.things.items.trinkets;

import com.glisco.things.Things;
import com.glisco.things.client.SimplePlayerTrinketRenderer;
import com.glisco.things.items.TrinketItemWithOptionalTooltip;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;

public class MossNecklaceItem extends TrinketItemWithOptionalTooltip implements SimplePlayerTrinketRenderer {

    public MossNecklaceItem() {
        super(new OwoItemSettings().maxCount(1).group(Things.THINGS_GROUP));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (!(entity instanceof ServerPlayerEntity player)) return;

        int daytime = (int) player.world.getTimeOfDay() % 24000;
        if (player.world.getLightLevel(LightType.BLOCK, player.getBlockPos()) > 7 ||
                (player.world.getLightLevel(LightType.SKY, player.getBlockPos()) > 7 && (daytime > 23500 || daytime < 12500))) {

            if (player.getStatusEffect(StatusEffects.REGENERATION) != null
                    && player.getStatusEffect(StatusEffects.REGENERATION).getDuration() > 10) return;

            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 610,
                    Things.CONFIG.effectLevels.mossNecklaceRegen() - 1, true, false, true));
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (!(entity instanceof ServerPlayerEntity player)) return;

        if (player.hasStatusEffect(StatusEffects.REGENERATION))
            player.removeStatusEffect(StatusEffects.REGENERATION);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void align(AbstractClientPlayerEntity player, PlayerEntityModel<AbstractClientPlayerEntity> model, MatrixStack matrices, float headYaw, float headPitch) {
        TrinketRenderer.translateToChest(matrices, model, player);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrices.scale(.5f, .5f, .5f);
        matrices.translate(0, .45, -.05);
    }
}
