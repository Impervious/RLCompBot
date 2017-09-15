package jish;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.impl.events.shard.DisconnectedEvent;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RLCompBot {

    public static RLCompBot bot;

    public static IDiscordClient client;
    public static IGuild guild;
    public static IChannel channel;

    public static void main(String[] args) throws Exception {
        bot = new RLCompBot();
    }

    public RLCompBot() {
        connect();
        client.getDispatcher().registerListener(this);
    }

    public void connect() {
        Optional<String> token = Util.getBotToken();
        if (!token.isPresent()) {
            System.out.println("Add your token to token.txt");
            System.out.println("Shutting down...");
            System.exit(0);
            return;
        }
        ClientBuilder cB = new ClientBuilder();
        cB.withToken(token.get());
        cB.setMaxReconnectAttempts(50);
        try {
            client = cB.login();
        } catch (DiscordException e) {
            e.printStackTrace();
        }
    }

    @EventSubscriber
    public void onReadyEvent(ReadyEvent e) {

        System.out.println("Connected.");
    }

    @EventSubscriber
    public void onDisconnectEvent(DisconnectedEvent event) {
        System.out.println("BOT DISCONNECTED");
        System.out.println("Reason: " + event.getReason());
    }

    @EventSubscriber
    public void onMessageEvent(MessageReceivedEvent event) {
        IMessage msg = event.getMessage();
        String text = msg.getContent();

        if (text.startsWith("!")) {
            String cmd = text.substring(1).split(" ")[0].toLowerCase();

            if(cmd.equalsIgnoreCase("bronze")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.BRONZE.ID));
                        sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(cmd.equalsIgnoreCase("silver")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.SILVER.ID));
                        sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(cmd.equalsIgnoreCase("gold")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.GOLD.ID));
                        sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(cmd.equalsIgnoreCase("platinum") || cmd.equalsIgnoreCase("plat")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.PLATINUM.ID));
                        sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(cmd.equalsIgnoreCase("diamond") || cmd.equalsIgnoreCase("dia") || cmd.equalsIgnoreCase("diam")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.DIAMOND.ID));
                        sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(cmd.equalsIgnoreCase("champion") || cmd.equalsIgnoreCase("champ")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.CHAMPION.ID));
                        sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(cmd.equalsIgnoreCase("gc")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.GRAND_CHAMPION.ID));
                        sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if(cmd.equalsIgnoreCase("ranks")) {
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

                RequestBuffer.request(() -> event.getChannel().sendMessage(builder.build()));
            }
        }
    }

    public static void sendMessage(IChannel channel, String message){
        try {
            channel.sendMessage(message);
        } catch(Exception e){}
    }

    public static void deleteMessage(IMessage message) {
        try {
            message.delete();
        } catch(Exception e) {}
    }

    @EventSubscriber
    public void userJoined(UserJoinEvent e) throws DiscordException {
        System.out.println("User joined");
        IUser joinGuy = e.getUser();
        String user = e.getUser().getStringID();
        String server = e.getGuild().getStringID();
        sendMessage(e.getClient().getChannelByID(Long.parseLong("338051063720443915")), "Welcome to the RL Competitive server " + joinGuy.toString() + "!");
        sendMessage(e.getClient().getChannelByID(Long.parseLong("338051063720443915")), "To get started on the server please do !ranks and do the command that corresponds to your competitive rank!");
    }

    @EventSubscriber
    public void botMention(MentionEvent e) {
        IMessage msg = e.getMessage();

        EmbedBuilder builder = new EmbedBuilder();

        builder.withAuthorName("RLCompBot");
        builder.withAuthorIcon("https://i.imgur.com/QRVYlDC.png");
        builder.appendField("Hello!", "Hello I am RLCompBot. I was created by <@73463573900173312>", false);
        builder.appendField("What do I do?", "Right now I greet new members to the server and assign roles! I plan on maybe doing more in the future.", false);
        builder.withColor(255, 30, 229);
        builder.withTimestamp(System.currentTimeMillis());

        RequestBuffer.request(() -> e.getChannel().sendMessage(builder.build()));
    }
}