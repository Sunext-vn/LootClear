package sunext.lootclear.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import sunext.lootclear.LootClear;

public class ExplodeEvent implements Listener {

    private final LootClear plugin = LootClear.getInstance();

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        for (Block block : e.blockList()) {
            plugin.getItemManager().eventExecute(block);
        }
    }

}
