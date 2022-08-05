package sunext.lootclear.managers;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.minecart.StorageMinecart;
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
        System.out.println("--1");
        for (String world : PathManager.WORLD_LIST) {
            if (!block.getWorld().equals(Bukkit.getWorld(world))) {
                return;
            }

            break;
        }

        for (Material chestType : chestType) {
            if (!block.getType().equals(chestType)){
                return;
            }

            break;
        }

        if (PathManager.ASYNC) {

            Bukkit.getScheduler().runTaskAsynchronously(plugin, ()
                    -> removeItem(block));

            return;
        }


        removeItem(block);
    }

    private void removeItem(Block block) {
        Inventory inventory = null;

        switch (block.getType().toString()) {
            case "CHEST":
            case "TRAPPED_CHEST":
                Chest chest = (Chest) block.getState();
                inventory = chest.getInventory();
                break;

            case "STORAGE_MINECART":
                StorageMinecart storageMinecart = (StorageMinecart) block.getState();
                inventory = storageMinecart.getInventory();
                break;

            case "WHITE_SHULKER_BOX":
            case "ORANGE_SHULKER_BOX":
            case "MAGENTA_SHULKER_BOX":
            case "LIGHT_BLUE_SHULKER_BOX":
            case "YELLOW_SHULKER_BOX":
            case "LIME_SHULKER_BOX":
            case "PINK_SHULKER_BOX":
            case "GRAY_SHULKER_BOX":
            case "LIGHT_GRAY_SHULKER_BOX":
            case "CYAN_SHULKER_BOX":
            case "PURPLE_SHULKER_BOX":
            case "BLUE_SHULKER_BOX":
            case "BROWN_SHULKER_BOX":
            case "GREEN_SHULKER_BOX":
            case "RED_SHULKER_BOX":
            case "BLACK_SHULKER_BOX":
                ShulkerBox shulkerBox = (ShulkerBox) block.getState();
                inventory = shulkerBox.getInventory();
                break;
        }

        for (Material item : plugin.getItemManager().getClearItem()) {
            assert inventory != null;
            if (inventory.contains(item))
                inventory.remove(item);
        }
    }

}
