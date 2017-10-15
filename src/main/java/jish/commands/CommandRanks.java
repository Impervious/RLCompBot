package jish.commands;

import jish.RLCompBot;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.util.List;

public class CommandRanks implements Command {
    @Override
    public String getName() {
        return "ranks";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        if(!isPrivate) {
            EmbedBuilder builder = new EmbedBuilder();

            builder.withAuthorName("RLCompBot");
            builder.withAuthorIcon("https://i.imgur.com/QRVYlDC.png");
            builder.appendField("Available Ranks", "Ranks:", false);
            builder.appendField("bronze", "silver", true);
            builder.appendField("gold", "platinum | plat", false);
            builder.appendField("diamond | dia ", "champion | champ", false);
            builder.appendField("gc", "Usage example: ```!rank diamond```", false);
            builder.withColor(255, 30, 229);
            builder.withTimestamp(System.currentTimeMillis());

            RequestBuffer.request(() -> message.getChannel().sendMessage(builder.build()));
        }
    }
}
