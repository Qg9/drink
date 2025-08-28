package com.jonahseguin.drink.command;

import com.jonahseguin.drink.qg.ConfigMessage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class DrinkHelpService21 {

    private final DrinkCommandService commandService;
    private HelpFormatter helpFormatter;

    public DrinkHelpService21(DrinkCommandService commandService, ConfigMessage messages) {
        this.commandService = commandService;
        this.helpFormatter = (sender, container) -> {

            for (String msg : messages.getErrormessage()) {
                String replaced = msg.replace("%name%", container.getName());

                if (replaced.contains("%arg%")) {
                    for (DrinkCommand c : container.getCommands().values()) {
                        TextComponent comp = new TextComponent(ChatColor.translateAlternateColorCodes('&',
                                replaced.replace("%arg%", c.getName())
                                        .replace("%usage%", c.getMostApplicableUsage())
                                        .replace("%description%", c.getShortDescription())
                        ));
                        comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(ChatColor.GRAY + "/" + container.getName() + " " + c.getName() + " - " + ChatColor.WHITE + c.getDescription())));
                        comp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + container.getName() + " " + c.getName()));
                        sender.spigot().sendMessage(comp);
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
        String usage = ChatColor.RED + "Command Usage: /" + container.getName() + " ";
        if (command.getName().length() > 0) {
            usage += command.getName() + " ";
        }
        if (command.getUsage() != null && command.getUsage().length() > 0) {
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
