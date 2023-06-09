package me.cooleg.easycommands;

import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public interface Command {

    boolean rootCommand(CommandSender commandSender, String alias);
    boolean noMatch(CommandSender commandSender, String alias, String[] args);


    @Nonnull String name();
    String description();
    String usage();
    List<String> aliases();

}
