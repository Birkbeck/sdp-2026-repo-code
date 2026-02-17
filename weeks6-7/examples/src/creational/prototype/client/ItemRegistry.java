package creational.prototype.client;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private final Map<String, Item> map = new HashMap<>();

    public ItemRegistry() {
        loadCache();
    }

    public Item createBasicItem(String type) {
        return map.get(type).clone();
    }

    private void loadCache() {
        Book book = new Book("Design Patterns", 20.00);
        map.put("Book", book);
        CD cd = new CD("Various", 10.00);
        map.put("CD", cd);
    }
}
