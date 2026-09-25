package dev.kryzen.kryzzCore;

import dev.kryzen.kryzzCore.commands.HealCommand;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class KryzzCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Kryzz Core has been enabled");

        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            Commands commands = event.registrar();

            commands.register(HealCommand.create(), "heals the player");

        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Kryzz Core has been disabled");
    }
}
