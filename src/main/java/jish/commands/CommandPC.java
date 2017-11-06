package jish.commands;

import jish.RLCompBot;
import jish.Roles;
import jish.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;

import java.util.List;

public class CommandPC implements Command {
    @Override
    public String getName() {
        return "pc";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        IChannel add_platform = client.getChannelByID(RLCompBot.ADD_PLATFORM_ID);

        if(!isPrivate) {
            if(message.getChannel().equals(add_platform)) {
                try {
                    Util.deleteMessage(message);
                    message.getAuthor().addRole(message.getGuild().getRoleByID(Roles.PC.ID));
                    Util.sendMessage(message.getChannel(), message.getAuthor() + ", you are now in the PC group. Let a staff member know if you have any questions.");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
