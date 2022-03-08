package org.glockinmybape.tattycustomitames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.glockinmybape.tattycustomitames.datas.Item;
import org.glockinmybape.tattycustomitames.manager.ItemManager;

public class GiveCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String commandName, String[] args) {
        if (sender.isOp() && sender.hasPermission("*")) {
            if (args.length == 2) {
                String items = args[0];
                String nick = args[1];
                Player player = Bukkit.getPlayerExact(nick);
                if (player == null) {
                    sender.sendMessage("§cИгрок '" + nick + "' не онлайн");
                    return true;
                } else {
                    Item item = ItemManager.getItems(items);
                    if (item == null) {
                        sender.sendMessage("§cПредмета '" + items + "' не существует");
                        return true;
                    } else {
                        PlayerInventory inventory = player.getInventory();
                        inventory.addItem(new ItemStack[]{item.getIcon()});
                        return true;
                    }
                }
            } else {
                sender.sendMessage("§bИспользуй §c/" + commandName + " [Название предмета] [Ник игрока]");
                return true;
            }
        } else {
            return false;
        }
    }
}
