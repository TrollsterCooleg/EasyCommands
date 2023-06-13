package me.cooleg.easycommands;

import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public interface Command {

    boolean rootCommand(CommandSender commandSender, String alias);
    boolean noMatch(CommandSender commandSender, String alias, String[] args);

    default List<String> rootTabComplete(CommandSender commandSender, String alias, String[] args) {return Collections.emptyList();}

    @Nonnull String name();
    default String description() {return null;}
    default String usage() {return null;}
    default List<String> aliases() {return null;}
    default String permission() {return null;}
    default String permissionMessage() {return null;}

}
