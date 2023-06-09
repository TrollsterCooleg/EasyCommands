package me.cooleg.easycommands.exceptions;

public class CommandUnregisteredException extends RuntimeException{

    public CommandUnregisteredException() {
        super("Command registered through EasyCommands doesn't have its command code implemented!");
    }

}
