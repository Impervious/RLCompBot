package jish.commands;

import jish.RLCompBot;
import jish.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;

public class CommandTest implements Command {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        if(!isPrivate) {
            Util.sendMessage(message.getChannel(), "test");
        }
    }
}
