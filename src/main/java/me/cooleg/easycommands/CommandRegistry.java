package me.cooleg.easycommands;

import me.cooleg.easycommands.bukkit.EasyBukkitCommand;
import me.cooleg.easycommands.commandmeta.SubCommand;
import me.cooleg.easycommands.commandmeta.SubCommands;
import me.cooleg.easycommands.exceptions.RegistryCreationFailedException;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class CommandRegistry {

    private final CommandMap commandMap;

    public CommandRegistry() {
        try {
            Field mapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            mapField.setAccessible(true);
            commandMap = (CommandMap) mapField.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RegistryCreationFailedException();
        }

    }

    public void registerCommand(@Nonnull Command command) {
        final HashMap<String, Method> commands = new HashMap<>();
        for (Method method : command.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(SubCommand.class) && !method.isAnnotationPresent(SubCommands.class)) {continue;}
            if (method.getReturnType() != boolean.class) {
                Bukkit.getLogger().info(
                        "Command " + command.name() + " has a method called " +
                                method.getName() + " which claims to be a subcommand but doesn't return boolean. " +
                                "This means this subcommand had to be ignored.");
                continue;
            }
            if (!Arrays.equals(method.getParameterTypes(), new Class[]{CommandSender.class, String.class, String[].class})) {
                Bukkit.getLogger().info(
                        "Command " + command.name() + " has a method called " +
                                method.getName() + " which claims to be a subcommand but doesn't have the correct " +
                                "parameter types. Use the noMatch method as a reference. This means this subcommand had to be ignored.");
                continue;
            }
            method.setAccessible(true);
            SubCommand[] annotations = method.getAnnotationsByType(SubCommand.class);
            for (SubCommand subCommand : annotations) {
                commands.put(subCommand.value().toLowerCase().trim() + " ", method);
            }
        }

        final EasyBukkitCommand bukkitCommand = new EasyBukkitCommand() {
            @Override
            public boolean execute(CommandSender commandSender, String alias, String[] args) {
                if (args.length == 0) {return command.rootCommand(commandSender, alias);}
                StringBuilder matchString = new StringBuilder();
                for (String s : args) {
                    matchString.append(s);

                    matchString.append(" ");
                }

                Method longestMatch = null;
                int longestLength = 0;

                String argString = matchString.toString().toLowerCase();
                for (String s : commands.keySet()) {
                    if (s.length() <= longestLength) {continue;}
                    if (!argString.startsWith(s)) {continue;}
                    longestMatch = commands.get(s);
                    longestLength = s.length();
                }

                if (longestMatch == null) {return command.noMatch(commandSender, alias, args);}
                try {
                    return (boolean) longestMatch.invoke(command, commandSender, alias, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    return false;
                }

            }
        }.initialize(command.name(), command.description(), command.usage(), command.aliases());

        commandMap.register(command.name(), bukkitCommand);
    }


}
