package sunext.lootclear.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import sunext.lootclear.LootClear;

public class OpenEvent implements Listener {

    private final LootClear plugin = LootClear.getInstance();

    @EventHandler
    public void onOpen(PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;

        plugin.getItemManager().eventExecute(e.getClickedBlock());
    }

}
