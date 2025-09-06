package com.jonahseguin.drink.command;

import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@Getter
@Setter
public class DrinkHelpService {

    private final DrinkCommandService commandService;
    private HelpFormatter helpFormatter;

    public DrinkHelpService(DrinkCommandService commandService) {
        this.commandService = commandService;
        this.helpFormatter = (sender, container) -> {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m--------------------------------"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bHelp &7- &6/" + container.getName()));
            for (DrinkCommand c : container.getCommands().values()) {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&7/" + container.getName() + (!c.getName().isEmpty() ? " &e" + c.getName() : "") + " &7" + c.getMostApplicableUsage()
                                + " &7- &f" + c.getShortDescription()));
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m--------------------------------"));
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

}
