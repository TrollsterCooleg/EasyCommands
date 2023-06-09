package me.cooleg.easycommands;

import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public interface Command {

    boolean rootCommand(CommandSender commandSender, String alias);
    boolean noMatch(CommandSender commandSender, String alias, String[] args);


    @Nonnull String name();
    default String description() {return null;}
    default String usage() {return null;}
    default List<String> aliases() {return null;}

}
