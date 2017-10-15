package jish.commands;

import jish.RLCompBot;
import jish.Roles;
import jish.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;

public class CommandDiamond implements Command {
    @Override
    public String getName() {
        return "diamond";
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        if(!isPrivate) {
            if(message.getChannel().getStringID().equals("338051063720443915") || message.getChannel().getStringID().equals("348989205193555968")) {
                try {
                    message.getAuthor().addRole(message.getGuild().getRoleByID(Roles.DIAMOND.ID));
                    message.getAuthor().removeRole(message.getGuild().getRoleByID((Roles.UNRANKED.ID)));
                    message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.PLATINUM.ID));
                    Util.deleteMessage(message);
                    Util.sendMessage(message.getChannel(), message.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
