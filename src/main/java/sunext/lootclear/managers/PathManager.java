package sunext.lootclear.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import sunext.lootclear.LootClear;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PathManager {

    private final LootClear plugin = LootClear.getInstance();

    public static Boolean ASYNC = true;

    public static String ADMIN_PERMISSION = "";

    public static List<String> WORLD_LIST = new ArrayList<>();
    public static List<String> CHEST_LIST = new ArrayList<>();
    public static List<String> ITEM_LIST = new ArrayList<>();

    public void register() {
        File config_file = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(config_file);

        WORLD_LIST.clear();
        CHEST_LIST.clear();
        ITEM_LIST.clear();

        ASYNC = config.getBoolean("async");

        ADMIN_PERMISSION = config.getString("admin-permission");

        WORLD_LIST = config.getStringList("world-apply");
        CHEST_LIST = config.getStringList("apply-to");
        ITEM_LIST = config.getStringList("items");
    }

}
