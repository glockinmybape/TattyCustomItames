package org.glockinmybape.tattycustomitames.runnables;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.glockinmybape.tattycustomitames.datas.IPlayer;
import org.glockinmybape.tattycustomitames.datas.Item;
import org.glockinmybape.tattycustomitames.manager.ItemManager;
import org.glockinmybape.tattycustomitames.manager.PlayerManager;

public class ItemsCheck extends BukkitRunnable {
    public void run() {
        Bukkit.getOnlinePlayers().forEach((player) -> {
            IPlayer iPlayer = PlayerManager.getPlayer(player);
            PlayerInventory playerInventory = player.getInventory();
            List<Item> skipItems = new ArrayList();
            ItemStack[] var4 = playerInventory.getArmorContents();
            int var5 = var4.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                ItemStack armor = var4[var6];
                if (armor != null) {
                    ItemMeta metax = armor.getItemMeta();
                    if (metax != null) {
                        String namex = metax.getDisplayName();
                        if (namex != null && !namex.isEmpty()) {
                            ItemManager.getItems().forEach((items) -> {
                                if (namex.equals(items.getName())) {
                                    if (!iPlayer.getItems().contains(items)) {
                                        iPlayer.addItem(items);
                                    }

                                    items.getEffects().forEach((effects) -> {
                                        String[] effect = effects.split(":");
                                        PotionEffectType effectType = PotionEffectType.getByName(effect[0].toUpperCase());
                                        int amplifier = Integer.parseInt(effect[1]);
                                        PotionEffect potionEffect = new PotionEffect(effectType, 9999999, amplifier - 1);
                                        player.addPotionEffect(potionEffect, false);
                                    });
                                    skipItems.add(items);
                                }
                            });
                        }
                    }
                }
            }

            ItemStack itemStack = playerInventory.getItemInMainHand();
            Material material;
            ItemMeta meta;
            String name;
            if (itemStack != null) {
                material = itemStack.getType();
                if (material != Material.AIR && !material.name().endsWith("_HELMET") && !material.name().endsWith("_CHESTPLATE") && !material.name().endsWith("_LEGGINGS") && !material.name().endsWith("_BOOTS")) {
                    meta = itemStack.getItemMeta();
                    if (meta != null) {
                        name = meta.getDisplayName();
                        if (name != null) {
                            String finalName = name;
                            ItemManager.getItems().forEach((items) -> {
                                if (finalName.equals(items.getName())) {
                                    if (!iPlayer.getItems().contains(items)) {
                                        iPlayer.addItem(items);
                                    }

                                    items.getEffects().forEach((effects) -> {
                                        String[] effect = effects.split(":");
                                        PotionEffectType effectType = PotionEffectType.getByName(effect[0].toUpperCase());
                                        int amplifier = Integer.parseInt(effect[1]);
                                        PotionEffect potionEffect = new PotionEffect(effectType, 9999999, amplifier - 1);
                                        player.addPotionEffect(potionEffect, false);
                                    });
                                    skipItems.add(items);
                                }
                            });
                        }
                    }
                }
            }

            itemStack = playerInventory.getItemInOffHand();
            if (itemStack != null) {
                material = itemStack.getType();
                if (material != Material.AIR && !material.name().endsWith("_HELMET") && !material.name().endsWith("_CHESTPLATE") && !material.name().endsWith("_LEGGINGS") && !material.name().endsWith("_BOOTS")) {
                    meta = itemStack.getItemMeta();
                    if (meta != null) {
                        name = meta.getDisplayName();
                        if (name != null) {
                            String finalName1 = name;
                            ItemManager.getItems().forEach((items) -> {
                                if (finalName1.equals(items.getName())) {
                                    if (!iPlayer.getItems().contains(items)) {
                                        iPlayer.addItem(items);
                                    }

                                    items.getEffects().forEach((effects) -> {
                                        String[] effect = effects.split(":");
                                        PotionEffectType effectType = PotionEffectType.getByName(effect[0].toUpperCase());
                                        int amplifier = Integer.parseInt(effect[1]);
                                        PotionEffect potionEffect = new PotionEffect(effectType, 9999999, amplifier - 1);
                                        player.addPotionEffect(potionEffect, false);
                                    });
                                    skipItems.add(items);
                                }
                            });
                        }
                    }
                }
            }

            List<Item> remover = new ArrayList();
            iPlayer.getItems().forEach((items) -> {
                if (!skipItems.contains(items)) {
                    items.getEffects().forEach((effects) -> {
                        String[] effect = effects.split(":");
                        String effectName = effect[0];
                        if (effectName != null) {
                            PotionEffectType effectType = PotionEffectType.getByName(effect[0].toUpperCase());
                            remover.add(items);
                            player.removePotionEffect(effectType);
                        }
                    });
                }
            });
            remover.forEach(iPlayer::removeItem);
        });
    }
}
