package org.glockinmybape.tattycustomitames;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.glockinmybape.tattycustomitames.commands.AutoTabCompleter;
import org.glockinmybape.tattycustomitames.commands.GiveCommand;
import org.glockinmybape.tattycustomitames.datas.IPlayer;
import org.glockinmybape.tattycustomitames.listeners.PlayerListener;
import org.glockinmybape.tattycustomitames.runnables.ItemsCheck;
import org.glockinmybape.tattycustomitames.utils.Configs;

import java.util.logging.Logger;

public class Main extends JavaPlugin {
    private static Main main;

    public void onEnable() {
        main = this;
        this.saveDefaultConfig();
        new Configs(this.getConfig());
        (new ItemsCheck()).runTaskTimer(this, 20L, 20L);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        this.getCommand("tgive").setExecutor(new GiveCommand());
        this.getCommand("tgive").setTabCompleter(new AutoTabCompleter());
        Bukkit.getOnlinePlayers().forEach(IPlayer::new);

        Logger log = Bukkit.getLogger();
        log.info("§b");
        log.info("§b .----------------------------------------------------------. ");
        log.info("§b| .-------------------------------------------------------. |");
        log.info("§b| |             \t\t\t\t\t\t");
        log.info("§b| |            §7Плагин: §bTattyCustomItames§8| §7Версия: §b1.0.1                ");
        log.info("§b| |        §7Создан для §bTattyWorld §8- §7Разработал: §bglockinmybape\t");
        log.info("§b| |                    §bvk.com/TattyWorld");
        log.info("§b| |             \t\t\t\t\t\t");
        log.info("§b| '-------------------------------------------------------'§b|");
        log.info("§b'-----------------------------------------------------------'");
        log.info("§b");
    }

    public void onDisable() {
        HandlerList.unregisterAll(this);
        Bukkit.getScheduler().cancelTasks(this);
    }

    public static Main get() {
        return main;
    }
}
