package org.glockinmybape.tattycustomitames.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.glockinmybape.tattycustomitames.datas.IPlayer;

public class PlayerManager {
    private static Map<Player, IPlayer> players = new HashMap();

    public static void create(Player player, IPlayer iPlayer) {
        players.putIfAbsent(player, iPlayer);
    }

    public static IPlayer getPlayer(Player player) {
        return (IPlayer)players.get(player);
    }

    public static Collection<IPlayer> getPlayers() {
        return players.values();
    }
}
