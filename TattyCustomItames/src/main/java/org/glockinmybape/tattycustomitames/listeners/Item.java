package org.glockinmybape.tattycustomitames.listeners;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.glockinmybape.tattycustomitames.manager.ItemManager;

public class Item extends org.glockinmybape.tattycustomitames.datas.Item {
    private String items;
    private String name;
    private List<String> effects;
    private List<String> sound;
    private boolean functionsDrop;
    private boolean functionsSave;
    private ItemStack icon;

    public Item(String items, String name, Material material, int data, int ammount, List<String> lore, List<String> enchants, List<String> effects, List<String> sound, boolean functionsBreak, boolean functionsDrop, boolean functionsSave) {
        super();
        this.items = items;
        this.name = name;
        this.effects = effects;
        this.sound = sound;
        this.functionsDrop = functionsDrop;
        this.functionsSave = functionsSave;
        this.icon = new ItemStack(material, ammount, (short)data);
        ItemMeta meta = this.icon.getItemMeta();
        meta.setDisplayName(name);
        List<String> newLore = new ArrayList();
        lore.forEach((line) -> {
            newLore.add(line.replaceAll("&", "§"));
        });
        meta.setLore(newLore);
        enchants.forEach((line) -> {
            String[] ench = line.split(":");
            Enchantment enchantment = Enchantment.getByName(ench[0].toUpperCase());
            int level = Integer.parseInt(ench[1]);
            meta.addEnchant(enchantment, level, true);
        });
        meta.spigot().setUnbreakable(!functionsBreak);
        this.icon.setItemMeta(meta);
        ItemManager.create(items, this);
    }

    public String getItems() {
        return this.items;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getEffects() {
        return this.effects;
    }

    public List<String> getSound() {
        return this.sound;
    }

    public boolean isFunctionsDrop() {
        return this.functionsDrop;
    }

    public boolean isFunctionsSave() {
        return this.functionsSave;
    }

    public ItemStack getIcon() {
        return this.icon;
    }
}
