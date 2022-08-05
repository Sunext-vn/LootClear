package sunext.lootclear.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import sunext.lootclear.LootClear;
import sunext.lootclear.managers.PathManager;
import sunext.lootclear.utils.Others;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements TabExecutor {

    private final LootClear plugin = LootClear.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        if (!sender.hasPermission(PathManager.ADMIN_PERMISSION) || !sender.isOp())
            return false;

        switch (args.length) {
            case 0:
                sender.sendMessage("");
                sender.sendMessage(Others.color(" &6/LootClear &areload &7- Cập nhật lại config.yml"));
                sender.sendMessage("");
                break;
            case 1:
                if ("reload".equals(args[0])) {
                    plugin.getPathManager().register();

                    sender.sendMessage(Others.color("&cLootClear &6> &aCập nhật lại config thành công!"));
                }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> result = new ArrayList<>();

        if (sender.hasPermission(PathManager.ADMIN_PERMISSION)) {
            if (args.length == 1) {
                if ("reload".startsWith(args[0].toLowerCase()))
                    result.add("reload");

                return result;
            }
        }

        return null;
    }
}
