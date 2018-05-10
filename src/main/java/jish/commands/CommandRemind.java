package jish.commands;

import jish.RLCompBot;
import jish.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.util.List;

public class CommandRemind implements Command  {
    @Override
    public String getName() {
        return "remind";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        IChannel test_general = client.getChannelByID(RLCompBot.TEST_GENERAL_ID);
        IChannel announce = client.getChannelByID(RLCompBot.ANNOUNCEMENTS_ID);
        IChannel edit_rank = client.getChannelByID(RLCompBot.EDIT_RANK_ID);
        IChannel add_platform = client.getChannelByID(RLCompBot.ADD_PLATFORM_ID);
        IUser user = message.getAuthor();

        if(!isPrivate) {
            if(message.getChannel().equals(test_general)) {
                if(user.getLongID() == RLCompBot.PRESS_A_ID || user.getLongID() == RLCompBot.IMPERVIOUS_ID) {

                    Util.sendMessage(announce, "@everyone");
                    EmbedBuilder builder = new EmbedBuilder();

                    builder.withAuthorName("RLCompBot");
                    builder.withAuthorIcon("https://i.imgur.com/QRVYlDC.png");
                    builder.appendField("**Platform Reminder!**", " *Please make sure to set your platform if you haven't already in the " + add_platform.mention() + " channel!*", false);
                    builder.appendField("**Rank Reminder!**", " *Please make sure to set your rank if you haven't already in the " + edit_rank.mention() + " channel!*", false);
                    builder.withColor(255, 30, 229);
                    builder.withTimestamp(System.currentTimeMillis());

                    Util.sendEmbed(message.getChannel(), builder.build());
                }
            } else {
                Util.sendMessage(message.getChannel(), "This command can only be used in the staff channel.");
            }
        } else {
            Util.sendPM(message.getAuthor().getOrCreatePMChannel(), "This command can't be used in a private message.");
        }
    }
}