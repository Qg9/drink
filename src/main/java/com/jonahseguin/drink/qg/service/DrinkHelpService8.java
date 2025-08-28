package com.jonahseguin.drink.command;

import com.jonahseguin.drink.qg.ConfigMessage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class DrinkHelpService8 {

    private final DrinkCommandService commandService;
    private final ConfigMessage message;
    private HelpFormatter helpFormatter;

    public DrinkHelpService8(DrinkCommandService commandService, ConfigMessage messages) {
        this.commandService = commandService;
        this.message = messages;
        this.helpFormatter = (sender, container) -> {

            for (String msg : messages.getErrormessage()) {
                String replaced = msg.replace("%name%", container.getName());

                if (replaced.contains("%arg%")) {
                    for (DrinkCommand c : container.getCommands().values()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                replaced.replace("%arg%", c.getName())
                                        .replace("%usage%", c.getMostApplicableUsage())
                                        .replace("%description%", c.getShortDescription())
                        ));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', replaced));
                }
            }
        };
    }

    public void sendHelpFor(CommandSender sender, DrinkCommandContainer container) {
        this.helpFormatter.sendHelpFor(sender, container);
    }

    public void sendUsageMessage(CommandSender sender, DrinkCommandContainer container, DrinkCommand command) {
        sender.sendMessage(getUsageMessage(container, command));
    }

    public String getUsageMessage(DrinkCommandContainer container, DrinkCommand command) {
        String usage = ChatColor.RED + "Usage: /" + container.getName() + " ";
        if (!command.getName().isEmpty()) {
            usage += command.getName() + " ";
        }
        if (command.getUsage() != null && !command.getUsage().isEmpty()) {
            usage += command.getUsage();
        } else {
            usage += command.getGeneratedUsage();
        }
        return usage;
    }

    public DrinkCommandService getCommandService() {
        return commandService;
    }

    public HelpFormatter getHelpFormatter() {
        return helpFormatter;
    }

    public void setHelpFormatter(HelpFormatter helpFormatter) {
        this.helpFormatter = helpFormatter;
    }

}
