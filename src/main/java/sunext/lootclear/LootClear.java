package sunext.lootclear;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import sunext.lootclear.commands.MainCommand;
import sunext.lootclear.listeners.BreakEvent;
import sunext.lootclear.listeners.ExplodeEvent;
import sunext.lootclear.listeners.MoveItemEvent;
import sunext.lootclear.listeners.OpenEvent;
import sunext.lootclear.managers.ItemManager;
import sunext.lootclear.managers.PathManager;

import java.util.Objects;

@Getter
public final class LootClear extends JavaPlugin {

    private static LootClear plugin;

    private PathManager pathManager;
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        pathManager = new PathManager();
        pathManager.register();

        itemManager = new ItemManager();
        itemManager.register();

        Objects.requireNonNull(getServer().getPluginCommand("LootClear")).setExecutor(new MainCommand());

        getServer().getPluginManager().registerEvents(new BreakEvent(), this);
        getServer().getPluginManager().registerEvents(new OpenEvent(), this);
        getServer().getPluginManager().registerEvents(new ExplodeEvent(), this);
        getServer().getPluginManager().registerEvents(new MoveItemEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static LootClear getInstance() {
        return plugin;
    }
}
