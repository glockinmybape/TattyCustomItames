package org.glockinmybape.tattycustomitames.datas;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.glockinmybape.tattycustomitames.manager.PlayerManager;

public class IPlayer {
    private List<Item> items = new ArrayList();

    public IPlayer(Player player) {
        PlayerManager.create(player, this);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public List<Item> getItems() {
        return this.items;
    }
}
