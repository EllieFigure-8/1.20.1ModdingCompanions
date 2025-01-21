package com.elliefigure8.companions.item.custom.accessories.wings;

import com.elliefigure8.companions.item.ModItems;
import com.elliefigure8.companions.registry.KeyBindRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class NetheriteWingsItem extends Item {

    public NetheriteWingsItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
            System.out.println("[DEBUG] Tiene WINGS puestas: " + player.getName().getString());
            handleFlight(player);
            player.fallDistance = 0;

        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }

    public static void handleFlight(Player player) {
        if (KeyBindRegistry.WingsAbilityKey.isDown()) {
            executeFlight(player);
        }
    }

    private static final double ACCELERATION = 0.02; // Tasa de aceleración
    private static final double DECELERATION = 0.03; // Tasa de desaceleración
    private static final double MAX_FORWARD_SPEED = 0.85;
    private static final double MAX_VERTICAL_SPEED = 0.35;

    private static final double MAX_FALL_SPEED = 0.1;
    private static final double MAX_FLIGHT_TIME = 1.5 * 60; // 1.5 segundos (60 ticks por segundo)
    private static double flightTimeRemaining = MAX_FLIGHT_TIME;
    private static final double horizontalSpeedPenalty = 0.95; // Penalización de velocidad horizontal

    private static double forwardSpeed = 0;
    private static double verticalSpeed = 0;
    private static double lateralSpeed = 0;

    private static void executeFlight(Player player) {
        Minecraft mc = Minecraft.getInstance();

        // Si el jugador está tocando el suelo, recarga la barra de vuelo
        if (player.onGround()) {
            flightTimeRemaining = MAX_FLIGHT_TIME;
        }

        // Movimiento hacia adelante y atrás
        if (mc.options.keyUp.isDown()) {
            if (forwardSpeed < MAX_FORWARD_SPEED) {
                forwardSpeed += ACCELERATION;  // Acelera al mover hacia adelante
            }
        } else if (mc.options.keyDown.isDown()) {
            if (forwardSpeed > -MAX_FORWARD_SPEED) {
                forwardSpeed -= ACCELERATION;  // Acelera al mover hacia atrás
            }
        } else {
            // Desaceleración gradual cuando no se está presionando ninguna tecla de movimiento
            if (forwardSpeed > 0) {
                forwardSpeed -= DECELERATION;
                if (forwardSpeed < 0) forwardSpeed = 0;
            } else if (forwardSpeed < 0) {
                forwardSpeed += DECELERATION;
                if (forwardSpeed > 0) forwardSpeed = 0;
            }
        }

        // Movimiento lateral (izquierda y derecha)
        if (mc.options.keyLeft.isDown()) {
            if (lateralSpeed > -MAX_FORWARD_SPEED) {
                lateralSpeed -= ACCELERATION;  // Acelera al mover a la izquierda
            }
        } else if (mc.options.keyRight.isDown()) {
            if (lateralSpeed < MAX_FORWARD_SPEED) {
                lateralSpeed += ACCELERATION;  // Acelera al mover a la derecha
            }
        } else {
            // Desaceleración lateral gradual cuando no se presionan las teclas de movimiento lateral
            if (lateralSpeed > 0) {
                lateralSpeed -= DECELERATION;
                if (lateralSpeed < 0) lateralSpeed = 0;
            } else if (lateralSpeed < 0) {
                lateralSpeed += DECELERATION;
                if (lateralSpeed > 0) lateralSpeed = 0;
            }
        }

        // Si hay tiempo de vuelo restante, permitir que el jugador suba
        if (flightTimeRemaining > 0) {
            // Movimiento hacia arriba (vuelo)
            if (mc.options.keyJump.isDown()) {
                if (verticalSpeed < MAX_VERTICAL_SPEED) {
                    verticalSpeed += ACCELERATION;  // Acelera al subir
                }
                flightTimeRemaining--;  // Reduce el tiempo de vuelo restante
            } else {
                if (verticalSpeed > 0) {
                    verticalSpeed -= DECELERATION;  // Desacelera al dejar de volar
                    if (verticalSpeed < 0) verticalSpeed = 0;
                }
            }
        } else {
            // Si no hay tiempo de vuelo, forzar al jugador a caer (desactivar levitación)
            if (verticalSpeed > 0) {
                verticalSpeed = 0;  // Deshabilitar cualquier velocidad ascendente o levitación
            }
            // Añadir gravedad para forzar al jugador a caer
            verticalSpeed -= DECELERATION;
            if (verticalSpeed < -MAX_FALL_SPEED) {
                verticalSpeed = -MAX_FALL_SPEED;  // Limitar la velocidad de caída
            }

            // Penalización de velocidad horizontal cuando se acaba el tiempo de vuelo
            forwardSpeed *= horizontalSpeedPenalty; // Reduce la velocidad hacia adelante
            lateralSpeed *= horizontalSpeedPenalty;  // Reduce la velocidad lateral
        }

        // Movimiento basado en la dirección de la cámara, pero proyectado en el plano horizontal
        Vec3 lookDirection = player.getLookAngle();
        // Crear un vector de movimiento que ignore la componente vertical (plano XZ)
        Vec3 forwardDirection = new Vec3(lookDirection.x, 0, lookDirection.z).normalize();

        // Dirección lateral (perpendicular a la dirección de avance)
        Vec3 lateralDirection = forwardDirection.cross(new Vec3(0, 1, 0)).normalize();

        // Crear el vector de movimiento total combinando las direcciones de avance, lateral y vertical
        Vec3 motion = forwardDirection.scale(forwardSpeed).add(lateralDirection.scale(lateralSpeed)).add(0, verticalSpeed, 0);

        // Aplicar el movimiento al jugador
        player.setDeltaMovement(motion);
        player.hurtMarked = true;  // Marca al jugador como "dañado" para forzar la actualización del movimiento
    }

    //public static void register(IEventBus eventBus) {
    //        eventBus.addListener(NetheriteWings::onTick);
    //    }
}
