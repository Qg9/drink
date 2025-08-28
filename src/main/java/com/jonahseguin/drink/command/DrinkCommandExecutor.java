package com.jonahseguin.drink.command;

import com.jonahseguin.drink.qg.ConfigMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class DrinkCommandExecutor implements CommandExecutor {

    private final DrinkCommandService commandService;
    private final DrinkCommandContainer container;
    private final ConfigMessage message;

    public DrinkCommandExecutor(DrinkCommandService commandService, DrinkCommandContainer container, ConfigMessage message) {
        this.commandService = commandService;
        this.container = container;
        this.message = message;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase(container.getName())) {
            try {
                Map.Entry<DrinkCommand, String[]> data = container.getCommand(args);
                if (data != null && data.getKey() != null) {
                    if (args.length > 0) {
                        if (args[args.length - 1].equalsIgnoreCase("help") && !data.getKey().getName().equalsIgnoreCase("help")) {
                            // Send help if they ask for it, if they registered a custom help sub-command, allow that to override our help menu
                            commandService.getHelpService().sendHelpFor(sender, container);
                            return true;
                        }
                    }
                    commandService.executeCommand(sender, data.getKey(), label, data.getValue());
                } else {
                    if (args.length > 0) {
                        if (args[args.length - 1].equalsIgnoreCase("help")) {
                            // Send help if they ask for it, if they registered a custom help sub-command, allow that to override our help menu
                            commandService.getHelpService().sendHelpFor(sender, container);
                            return true;
                        }
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                message.getUnknowsub()
                        ));
                    } else {
                        if (container.isDefaultCommandIsHelp()) {
                            commandService.getHelpService().sendHelpFor(sender, container);
                        }
                        else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    message.getUnknowsub()
                            ));                        }
                    }
                }
                return true;
            }
            catch (Exception ex) {
                sender.sendMessage(ChatColor.RED + "An exception occurred while performing this command.");
                ex.printStackTrace();
            }
        }
        return false;
    }
}
