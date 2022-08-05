package sunext.lootclear.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import sunext.lootclear.LootClear;
import sunext.lootclear.managers.PathManager;

import java.util.Objects;

public class MoveItemEvent implements Listener {

    private final LootClear plugin = LootClear.getInstance();

    @EventHandler
    public void onMoveItem(InventoryMoveItemEvent e) {
        for (String world : PathManager.WORLD_LIST) {
            if (!Objects.equals(Objects.requireNonNull(e.getSource().getLocation()).getWorld(), Bukkit.getWorld(world))) {
                return;
            }

            break;
        }

        if (PathManager.ASYNC) {

            Bukkit.getScheduler().runTaskAsynchronously(plugin, ()
                    ->  {
                for (Material item : plugin.getItemManager().getClearItem()) {
                    if (e.getItem().getType().equals(item))
                        e.getItem().setType(Material.AIR);

                }
            });

            return;
        }

        for (Material item : plugin.getItemManager().getClearItem()) {
            if (e.getItem().getType().equals(item))
                e.getItem().setType(Material.AIR);

        }
    }

}
