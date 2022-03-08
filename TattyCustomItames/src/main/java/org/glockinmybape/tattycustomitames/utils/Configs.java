package org.glockinmybape.tattycustomitames.utils;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.glockinmybape.tattycustomitames.datas.Item;

public class Configs {
    private FileConfiguration config;

    public Configs(FileConfiguration config) {
        this.config = config;
        this.init();
    }

    private void init() {
        this.config.getConfigurationSection("items").getKeys(false).forEach((item) -> {
            String name = this.config.getString("items." + item + ".name").replaceAll("&", "§");
            Material material = Material.valueOf(this.config.getString("items." + item + ".icon.material").toUpperCase());
            int data = this.config.getInt("items." + item + ".icon.data", 0);
            int ammount = this.config.getInt("items." + item + ".icon.ammount", 1);
            List<String> lore = this.config.getStringList("items." + item + ".lore");
            List<String> enchants = this.config.getStringList("items." + item + ".enchants");
            List<String> effects = this.config.getStringList("items." + item + ".effects");
            List<String> sound = this.config.getStringList("items." + item + ".sound");
            boolean functionsBreak = this.config.getBoolean("items." + item + ".functions.break", false);
            boolean functionsDrop = this.config.getBoolean("items." + item + ".functions.drop", false);
            boolean functionsSave = this.config.getBoolean("items." + item + ".functions.save", true);
            new Item(item, name, material, data, ammount, lore, enchants, effects, sound, functionsBreak, functionsDrop, functionsSave);
        });
    }
}
