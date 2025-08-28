package com.jonahseguin.drink.qg;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class ConfigMessage {

    private String unknowsub, pleasechoosesub;
    private List<String> errormessage;

    public ConfigMessage() {
        this.unknowsub = ChatColor.RED + "Unknown sub-command: %arg%.  Use '/%label% help' for available commands.";
        this.pleasechoosesub = ChatColor.RED + "Please choose a sub-command.  Use '/%label% help' for available commands.";
        this.errormessage = Arrays.asList(
                "&7&m--------------------------------",
                "&bHelp &7- &6/%name%",
                "&7/%name% %arg% &7%usage% &7- &f%description%",
                "&7&m--------------------------------");
    }

    public ConfigMessage(String unknowsub, List<String> errormessage, String pleasechoosesub) {
        this.unknowsub = unknowsub;
        this.errormessage = errormessage;
        this.pleasechoosesub = pleasechoosesub;
    }

    public String getUnknowsub() {
        return unknowsub;
    }

    public String getPleasechoosesub() {
        return pleasechoosesub;
    }

    public List<String> getErrormessage() {
        return errormessage;
    }
}
