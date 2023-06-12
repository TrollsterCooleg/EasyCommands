package me.cooleg.easycommands.bukkit;

import me.cooleg.easycommands.exceptions.CommandUnregisteredException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class EasyBukkitCommand extends Command {

    protected EasyBukkitCommand() {
        super("placeholdercommandname");
    }

    public EasyBukkitCommand initialize(@Nonnull String name, @Nullable String permission, @Nullable String permissionMessage, @Nullable String description, @Nullable String usageMessage, @Nullable List<String> aliases) {
        setName(name.toLowerCase());
        if (description != null) {
            setDescription(description);
        }
        if (usageMessage != null) {
            setUsage(usageMessage);
        }
        if (aliases != null) {
            setAliases(aliases.stream().map(String::toLowerCase).collect(Collectors.toList()));
        }
        if (permission != null) {
            setPermission(permission.toLowerCase());
        }
        if (permissionMessage != null) {
            setPermissionMessage(permissionMessage);
        }


        return this;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        throw new CommandUnregisteredException();
    }
}
