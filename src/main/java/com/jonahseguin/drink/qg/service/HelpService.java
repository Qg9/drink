package com.jonahseguin.drink.qg.service;

import com.jonahseguin.drink.command.DrinkCommand;
import com.jonahseguin.drink.command.DrinkCommandContainer;
import com.jonahseguin.drink.command.DrinkCommandService;
import com.jonahseguin.drink.command.HelpFormatter;
import org.bukkit.command.CommandSender;

public interface HelpService {

    DrinkCommandService getCommandService();
    HelpFormatter getHelpFormatter();
    String getUsageMessage(DrinkCommandContainer container, DrinkCommand command);
    void sendUsageMessage(CommandSender sender, DrinkCommandContainer container, DrinkCommand command);
    void sendHelpFor(CommandSender sender, DrinkCommandContainer container);
}
