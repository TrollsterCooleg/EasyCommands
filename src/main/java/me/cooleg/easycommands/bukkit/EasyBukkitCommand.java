package me.cooleg.easycommands.bukkit;

import me.cooleg.easycommands.exceptions.CommandUnregisteredException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class EasyBukkitCommand extends Command {

    protected EasyBukkitCommand() {
        super("placeholdercommandname");
    }

    public EasyBukkitCommand initialize(@Nonnull String name, @Nullable String description, @Nullable String usageMessage, @Nullable List<String> aliases) {
        setName(name);
        if (description != null) {
            setDescription(description);
        }
        if (usageMessage != null) {
            setUsage(usageMessage);
        }
        if (aliases != null) {
            setAliases(aliases);
        }

        return this;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        throw new CommandUnregisteredException();
    }
}
