package jish.commands;

import jish.RLCompBot;
import jish.Roles;
import jish.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;

public class CommandPC implements Command {
    @Override
    public String getName() {
        return "pc";
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        if(!isPrivate) {
            if(message.getChannel().getStringID().equals("366773418822991873")) {
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
