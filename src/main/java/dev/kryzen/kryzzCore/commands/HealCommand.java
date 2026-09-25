package dev.kryzen.kryzzCore.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class HealCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("heal")
                .requires(src -> src.getExecutor() instanceof Player)
                .then(
                        Commands.literal("full")
                                .executes(ctx -> {

                                    Player player = (Player) ctx.getSource().getExecutor();

                                    fullyHeal(player);

                                    return Command.SINGLE_SUCCESS;

                                })
                )
                .then(
                        Commands.argument("amount", DoubleArgumentType.doubleArg(1.0, 20.0))
                                .executes(ctx -> {

                                    Player player = (Player) ctx.getSource().getExecutor();
                                    double amt = DoubleArgumentType.getDouble(ctx, "amount");

                                    healByAmount(player, amt);

                                    return Command.SINGLE_SUCCESS;
                                })
                ).build();
    }

    private static void fullyHeal(Player player) {

        if (player == null) return;

        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.sendMessage(
                Component.text("You have been fully healed!").color(NamedTextColor.GREEN)
        );

        player.sendMessage(Component.text("Fully healed ").color(NamedTextColor.GREEN)
                .append(Component.text(player.getName())).color(NamedTextColor.YELLOW));

    }

    private static void healByAmount(Player player, double amt) {

        if (player == null) return;

        player.setHealth(amt);
        player.setFoodLevel(((int) amt));
        player.setSaturation((float) amt);
        player.sendMessage(
                Component.text("You have been healed!").color(NamedTextColor.GREEN)
        );

        player.sendMessage(Component.text(player.getName()).color(NamedTextColor.YELLOW)
                .append(Component.text(" got healed by ").color(NamedTextColor.GREEN))
                .append(Component.text(amt/2).color(NamedTextColor.RED))
                .append(Component.text(" hearts").color(NamedTextColor.GREEN)));

    }

}
