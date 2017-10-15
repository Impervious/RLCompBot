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
            try {
                if(args.length == 1) {
                    if (args[0].equalsIgnoreCase("wow")) {
                        Util.sendMessage(message.getChannel(), "wow it worked");
                    } else {
                        if (message.getAuthor().getStringID().equals("73463573900173312")) {
                            Util.sendMessage(message.getChannel(), "test");
                        }
                    }
                } else {
                    Util.sendMessage(message.getChannel(), "finally");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}