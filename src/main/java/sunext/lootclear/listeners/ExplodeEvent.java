package sunext.lootclear.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import sunext.lootclear.LootClear;

public class ExplodeEvent implements Listener {

    private final LootClear plugin = LootClear.getInstance();

    @EventHandler
    public void onExplode(BlockExplodeEvent e) {
        plugin.getItemManager().eventExecute(e.getBlock());
    }

}
