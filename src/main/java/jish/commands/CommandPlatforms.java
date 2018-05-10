package jish.commands;

import jish.RLCompBot;
import jish.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.util.List;

public class CommandPlatforms implements Command {
    @Override
    public String getName() {
        return "platforms";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        if (!isPrivate) {
            EmbedBuilder builder = new EmbedBuilder();

            builder.withAuthorName("RLCompBot");
            builder.withAuthorIcon("https://i.imgur.com/QRVYlDC.png");
            builder.appendField("Available Platforms", "Platforms:", false);
            builder.appendField("!pc", "Grants you the PC group", true);
            builder.appendField("!xb1 | !xbox | !xbone", "Grants you the Xbox group", false);
            builder.appendField("!ps4 | !playstation | !ps", "Grants you the PS4 group", false);
            builder.withColor(255, 30, 229);
            builder.withTimestamp(System.currentTimeMillis());

            Util.sendEmbed(message.getChannel(), builder.build());
        }
    }
}