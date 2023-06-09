package me.cooleg.easycommands.exceptions;

public class RegistryCreationFailedException extends RuntimeException {

    public RegistryCreationFailedException() {
        super("Failed to create command registry for EasyCommands!");
    }

}
