package jish;

import jish.commands.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RLCompBot {

    public static RLCompBot bot;

    public static IDiscordClient client;
    public static IGuild guild;
    public static IChannel channel;

    private List<Command> registeredCommands = new ArrayList<>();

    private static final Pattern COMMAND_PATTERN = Pattern.compile("(?s)^!([^\\s]+) ?(.*)", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) throws Exception {
        bot = new RLCompBot();


    }

    public RLCompBot() {
        registerCommand(new CommandTest());
        registerCommand(new CommandBronze());
        registerCommand(new CommandSilver());
        registerCommand(new CommandGold());
        registerCommand(new CommandTest());
        registerCommand(new CommandTest());
        registerCommand(new CommandTest());
        registerCommand(new CommandTest());

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
        IGuild guild = null;
        String text = msg.getContent();

        if(event.getMessage().getAuthor().isBot()) return;

        boolean isPrivate = msg.getChannel().isPrivate();
        if(!isPrivate) guild = msg.getGuild();

        Matcher matcher = COMMAND_PATTERN.matcher(text);
        if (matcher.matches()) {

            String baseCommand = matcher.group(1).toLowerCase();
            Optional<Command> command = registeredCommands.stream().filter(com -> com.getName().equalsIgnoreCase(baseCommand)).findFirst();

            if (command.isPresent()) {
                String args = matcher.group(2);
                String[] argsArr = args.isEmpty() ? new String[0] : args.split(" ");
                command.get().execute(bot, client, argsArr, guild, msg, isPrivate);
            }
        }/*
            if(cmd.equalsIgnoreCase("platinum") || cmd.equalsIgnoreCase("plat")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.PLATINUM.ID));
                        event.getAuthor().removeRole(event.getGuild().getRoleByID(Roles.UNRANKED.ID));
                        event.getAuthor().removeRole(event.getGuild().getRoleByID(Roles.GOLD.ID));
                        Util.deleteMessage(msg);
                        Util.sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(cmd.equalsIgnoreCase("diamond") || cmd.equalsIgnoreCase("dia") || cmd.equalsIgnoreCase("diam")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.DIAMOND.ID));
                        event.getAuthor().removeRole(event.getGuild().getRoleByID((Roles.UNRANKED.ID)));
                        event.getAuthor().removeRole(event.getGuild().getRoleByID(Roles.PLATINUM.ID));
                        Util.deleteMessage(msg);
                        Util.sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(cmd.equalsIgnoreCase("champion") || cmd.equalsIgnoreCase("champ")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.CHAMPION.ID));
                        event.getAuthor().removeRole(event.getGuild().getRoleByID(Roles.UNRANKED.ID));
                        event.getAuthor().removeRole(event.getGuild().getRoleByID(Roles.DIAMOND.ID));
                        Util.deleteMessage(msg);
                        Util.sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(cmd.equalsIgnoreCase("gc")) {
                if(msg.getChannel().getStringID().equals("338051063720443915") || msg.getChannel().getStringID().equals("348989205193555968")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.GRAND_CHAMPION.ID));
                        event.getAuthor().removeRole(event.getGuild().getRoleByID(Roles.UNRANKED.ID));
                        event.getAuthor().removeRole(event.getGuild().getRoleByID(Roles.CHAMPION.ID));
                        Util.deleteMessage(msg);
                        Util.sendMessage(msg.getChannel(), msg.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if(cmd.equalsIgnoreCase("pc")) {
                if(msg.getChannel().getStringID().equals("366773418822991873")) {
                    try {
                        Util.deleteMessage(msg);
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.PC.ID));
                        Util.sendMessage(msg.getChannel(), msg.getAuthor() + ", you are now in the PC group. Let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if(cmd.equalsIgnoreCase("xb1") || cmd.equalsIgnoreCase("xbox") || cmd.equalsIgnoreCase("xbone")) {
                if(msg.getChannel().getStringID().equals("366773418822991873")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.XB1.ID));
                        Util.deleteMessage(msg);
                        Util.sendMessage(msg.getChannel(), msg.getAuthor() + ", you are now in the Xbox group. Let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if(cmd.equalsIgnoreCase("ps4") || cmd.equalsIgnoreCase("playstation") || cmd.equalsIgnoreCase("ps")) {
                if(msg.getChannel().getStringID().equals("366773418822991873")) {
                    try {
                        event.getAuthor().addRole(event.getGuild().getRoleByID(Roles.XB1.ID));
                        Util.deleteMessage(msg);
                        Util.sendMessage(msg.getChannel(), msg.getAuthor() + ", you are now in the PlayStation group. Let a staff member know if you have any questions.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if(cmd.equalsIgnoreCase("platforms")) {
                EmbedBuilder builder = new EmbedBuilder();

                builder.withAuthorName("RLCompBot");
                builder.withAuthorIcon("https://i.imgur.com/QRVYlDC.png");
                builder.appendField("Available Platforms", "Platforms:", false);
                builder.appendField("!pc", "Grants you the PC group", true);
                builder.appendField("!xb1 | !xbox | !xbone", "Grants you the Xbox group", false);
                builder.appendField("!ps4 | !playstation | !ps", "Grants you the PS4 group", false);
                builder.withColor(255, 30, 229);
                builder.withTimestamp(System.currentTimeMillis());

                RequestBuffer.request(() -> event.getChannel().sendMessage(builder.build()));
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
        }*/
    }

    public void registerCommand(Command command) {
        if(!registeredCommands.contains(command)) {
            registeredCommands.add(command);
            System.out.println("Command registered: " + command.getName());
        }
    }

    @EventSubscriber
    public void userJoined(UserJoinEvent e) throws DiscordException {
        System.out.println("User joined");
        IUser joinGuy = e.getUser();
        String user = e.getUser().getStringID();
        String server = e.getGuild().getStringID();
        Util.sendMessage(e.getClient().getChannelByID(Long.parseLong("338051063720443915")), "Welcome to the RL Competitive server " + joinGuy.toString() + "!");
        Util.sendMessage(e.getClient().getChannelByID(Long.parseLong("338051063720443915")), "To get started on the server please do !ranks and do the command that corresponds to your competitive rank!");
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