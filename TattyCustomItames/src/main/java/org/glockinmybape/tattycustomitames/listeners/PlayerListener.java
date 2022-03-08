package org.glockinmybape.tattycustomitames.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.glockinmybape.tattycustomitames.datas.IPlayer;
import org.glockinmybape.tattycustomitames.manager.ItemManager;

public class PlayerListener implements Listener {
    @EventHandler(
            priority = EventPriority.MONITOR
    )
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new IPlayer(player);
    }

    @EventHandler(
            priority = EventPriority.MONITOR
    )
    public void onPlayerDeath(PlayerDeathEvent event) {
    }

    @EventHandler(
            priority = EventPriority.MONITOR
    )
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damagerEntity = event.getDamager();
        Player player;
        PlayerInventory playerInventory;
        ItemStack armor;
        if (damagerEntity instanceof Player) {
            player = (Player)damagerEntity;
            playerInventory = player.getInventory();
            armor = playerInventory.getItemInMainHand();
            if (armor == null) {
                return;
            }

            Material material = armor.getType();
            if (material == Material.BOW && material.name().contains("_HELMET") && material.name().contains("_CHESTPLATE") && material.name().contains("_LEGGINGS") && material.name().contains("_BOOTS")) {
                return;
            }

            ItemMeta meta = armor.getItemMeta();
            if (meta == null) {
                return;
            }

            String name = meta.getDisplayName();
            if (name == null || name.isEmpty()) {
                return;
            }

            Player finalPlayer = player;
            ItemManager.getItems().forEach((items) -> {
                if (name.equals(items.getName())) {
                    items.getSound().forEach((sounds) -> {
                        String[] sound = sounds.split(":");
                        Sound soundType = Sound.valueOf(sound[0].toUpperCase());
                        float volume = Float.parseFloat(sound[1]);
                        float pitch = Float.parseFloat(sound[2]);
                        Location location = finalPlayer.getLocation();
                        finalPlayer.playSound(location, soundType, volume, pitch);
                    });
                }
            });
        }

        if (entity instanceof Player) {
            player = (Player)entity;
            playerInventory = player.getInventory();
            ItemStack[] var14;
            int var13 = (var14 = playerInventory.getArmorContents()).length;

            for(int var12 = 0; var12 < var13; ++var12) {
                armor = var14[var12];
                if (armor != null) {
                    ItemMeta meta = armor.getItemMeta();
                    if (meta == null) {
                        return;
                    }

                    String name = meta.getDisplayName();
                    if (name != null && !name.isEmpty()) {
                        Player finalPlayer1 = player;
                        ItemManager.getItems().forEach((items) -> {
                            if (name.equals(items.getName())) {
                                items.getSound().forEach((sounds) -> {
                                    String[] sound = sounds.split(":");
                                    Sound soundType = Sound.valueOf(sound[0].toUpperCase());
                                    float volume = Float.parseFloat(sound[1]);
                                    float pitch = Float.parseFloat(sound[2]);
                                    Location location = finalPlayer1.getLocation();
                                    finalPlayer1.playSound(location, soundType, volume, pitch);
                                });
                            }
                        });
                    }
                }
            }

        }
    }

    @EventHandler(
            priority = EventPriority.MONITOR
    )
    public void onEntityShootBow(EntityShootBowEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player)entity;
            ItemStack bow = event.getBow();
            if (bow != null) {
                ItemMeta meta = bow.getItemMeta();
                if (meta != null) {
                    String name = meta.getDisplayName();
                    if (name != null && !name.isEmpty()) {
                        ItemManager.getItems().forEach((items) -> {
                            if (name.equals(items.getName())) {
                                items.getSound().forEach((sounds) -> {
                                    String[] sound = sounds.split(":");
                                    Sound soundType = Sound.valueOf(sound[0].toUpperCase());
                                    float volume = Float.parseFloat(sound[1]);
                                    float pitch = Float.parseFloat(sound[2]);
                                    Location location = player.getLocation();
                                    player.playSound(location, soundType, volume, pitch);
                                });
                            }
                        });
                    }
                }
            }
        }
    }
}
