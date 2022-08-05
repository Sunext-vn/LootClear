package sunext.lootclear.managers;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;
import sunext.lootclear.LootClear;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ItemManager {

    private final LootClear plugin = LootClear.getInstance();

    public List<Material> clearItem = new ArrayList<>();

    public List<Material> chestType = new ArrayList<>();

    public void register() {
        for (String item : PathManager.ITEM_LIST) {
            clearItem.add(Objects.requireNonNull(Material.matchMaterial(item)));
        }

        for (String chest : PathManager.CHEST_LIST) {
            chestType.add(Objects.requireNonNull(Material.matchMaterial(chest)));
        }
    }

    public void eventExecute(Block block) {
        for (String world : PathManager.WORLD_LIST) {
            if (block.getWorld().equals(Bukkit.getWorld(world)))
                return;
        }

        for (Material chestType : chestType)
            if (block.getType().equals(chestType))
                return;


        if (PathManager.ASYNC) {

            Bukkit.getScheduler().runTaskAsynchronously(plugin, ()
                    -> removeItem(block));

            return;
        }

        removeItem(block);
    }

    private void removeItem(Block block) {
        BlockState state = block.getState();
        Container container = (Container) state;
        Inventory inventory = container.getInventory();

        for (Material item : plugin.getItemManager().getClearItem()) {
            if (inventory.contains(item))
                inventory.remove(item);
        }
    }

}
