package jish.commands;

import jish.RLCompBot;
import jish.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Arrays;
import java.util.List;

public class CommandTest implements Command {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("weener");
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        if(!isPrivate) {
            if (message.getAuthor().getStringID().equals("73463573900173312")) {
                try {
                    if(args.length == 1) {
                        if (args[0].equalsIgnoreCase("wow")) {
                            Util.sendMessage(message.getChannel(), "The correct format is: ```!rank <rank name>```");
                        } else {
                            Util.sendMessage(message.getChannel(), "Correct format is: r4yswdfg");
                        }
                    } else {
                        Util.sendMessage(message.getChannel(), "use the correct format idiot");
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else {
                Util.sendMessage(message.getChannel(), "not for you");
            }
        }
    }
}