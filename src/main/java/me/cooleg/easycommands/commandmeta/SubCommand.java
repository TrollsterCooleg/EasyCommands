package me.cooleg.easycommands.commandmeta;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SubCommands.class)
public @interface SubCommand {
    String value();

}
