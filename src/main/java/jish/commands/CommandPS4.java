package jish.commands;

import jish.RLCompBot;
import jish.Roles;
import jish.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;

import java.util.List;

public class CommandPS4 implements Command  {
    @Override
    public String getName() {
        return "ps4";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        if(!isPrivate) {
            if(message.getChannel().getStringID().equals("366773418822991873")) {
                try {
                    message.getAuthor().addRole(message.getGuild().getRoleByID(Roles.XB1.ID));
                    Util.deleteMessage(message);
                    Util.sendMessage(message.getChannel(), message.getAuthor() + ", you are now in the PlayStation group. Let a staff member know if you have any questions.");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
