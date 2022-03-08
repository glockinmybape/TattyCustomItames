package org.glockinmybape.tattycustomitames.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.glockinmybape.tattycustomitames.manager.ItemManager;

public class AutoTabCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender sender, Command command, String commandName, String[] args) {
        List<String> completes = new ArrayList();
        List<String> subCommands = new ArrayList();
        if (args.length == 1) {
            ItemManager.getItems().forEach((items) -> {
                subCommands.add(items.getItems());
            });
            if (!args[0].equals("")) {
                subCommands.forEach((subCommand) -> {
                    String complete = subCommand.toLowerCase();
                    String startWith = args[0].toLowerCase();
                    if (complete.startsWith(startWith)) {
                        completes.add(complete);
                    }

                });
            } else {
                completes.addAll(subCommands);
            }

            return completes;
        } else if (args.length == 2) {
            Bukkit.getOnlinePlayers().forEach((player) -> {
                subCommands.add(player.getName());
            });
            if (!args[1].equals("")) {
                subCommands.forEach((subCommand) -> {
                    String complete = subCommand.toLowerCase();
                    String startWith = args[1].toLowerCase();
                    if (complete.startsWith(startWith)) {
                        completes.add(complete);
                    }

                });
            } else {
                completes.addAll(subCommands);
            }

            return completes;
        } else {
            return completes;
        }
    }
}
