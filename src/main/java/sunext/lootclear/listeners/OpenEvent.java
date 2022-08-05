package sunext.lootclear.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import sunext.lootclear.LootClear;

public class OpenEvent implements Listener {

    private final LootClear plugin = LootClear.getInstance();

    @EventHandler
    public void onOpen(PlayerInteractEvent e) {
        plugin.getItemManager().eventExecute(e.getClickedBlock());
    }

}
