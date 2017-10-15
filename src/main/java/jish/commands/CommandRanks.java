package jish.commands;

import jish.RLCompBot;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

public class CommandRanks implements Command {
    @Override
    public String getName() {
        return "ranks";
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        if(!isPrivate) {
            EmbedBuilder builder = new EmbedBuilder();

            builder.withAuthorName("RLCompBot");
            builder.withAuthorIcon("https://i.imgur.com/QRVYlDC.png");
            builder.appendField("Available Commands", "Ranks:", false);
            builder.appendField("!bronze", "!silver", true);
            builder.appendField("!gold", "!platinum | !plat", false);
            builder.appendField("!diamond | !dia | !diam", "!champion | !champ", false);
            builder.appendField("!gc", "Let a staff member know if you have any questions!", false);
            builder.withColor(255, 30, 229);
            builder.withTimestamp(System.currentTimeMillis());

            RequestBuffer.request(() -> message.getChannel().sendMessage(builder.build()));
        }
    }
}
