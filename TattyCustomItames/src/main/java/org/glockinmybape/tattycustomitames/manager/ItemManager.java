package org.glockinmybape.tattycustomitames.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.glockinmybape.tattycustomitames.datas.Item;

public class ItemManager {
    private static Map<String, Item> items = new HashMap();

    public static void create(String items, Item item) {
        ItemManager.items.putIfAbsent(items, item);
    }

    public static Item getItems(String item) {
        return (Item)items.get(item);
    }

    public static Collection<Item> getItems() {
        return items.values();
    }
}
