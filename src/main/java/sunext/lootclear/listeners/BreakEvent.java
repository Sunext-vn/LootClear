package sunext.lootclear.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import sunext.lootclear.LootClear;

public class BreakEvent implements Listener {

    private final LootClear plugin = LootClear.getInstance();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        plugin.getItemManager().eventExecute(e.getBlock());
    }


}
