package me.cooleg.easycommands.commandmeta;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TabCompletes.class)
public @interface TabCompleter {
    String value();
}
