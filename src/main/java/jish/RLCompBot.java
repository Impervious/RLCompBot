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

import org.reflections.Reflections;

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

    private static final Pattern COMMAND_PATTERN = Pattern.compile("^!([^\\s]+) ?(.*)", Pattern.CASE_INSENSITIVE);

    /*
     * Admin User ID's
     */

    public static final long PRESS_A_ID = 336733516102500352L;
    public static final long IMPERVIOUS_ID = 73463573900173312L;

    /*
     * Bot Testing Server Channel ID
     */

    public static final long TEST_GENERAL_ID = 247394948331077632L;

    /*
     * RLComp Channel ID's
     */

    public static final long STAFF_ID = 338103775350882304L;
    public static final long EDIT_RANK_ID = 348989205193555968L;
    public static final long ADD_PLATFORM_ID = 366773418822991873L;
    public static final long ANNOUNCEMENTS_ID = 338419393245478912L;
    public static final long NEWBIES_ID = 338051063720443915L;
    public static final long BOT_TEST_ID = 443883538417451029L;


    public static void main(String[] args) throws Exception {
        bot = new RLCompBot();
    }

    public RLCompBot() {
        registerAllCommands();
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

    public static RLCompBot getInstance() {
        return bot;
    }

    public static IDiscordClient getClient() {
        return client;
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
            Optional<Command> command = registeredCommands.stream().filter(com -> com.getName().equalsIgnoreCase(baseCommand) || (com.getAliases() != null && com.getAliases().contains(baseCommand))).findAny();

            if (command.isPresent()) {
                String args = matcher.group(2);
                String[] argsArr = args.isEmpty() ? new String[0] : args.split(" ");
                command.get().execute(bot, client, argsArr, guild, msg, isPrivate);
            }
        }
    }

    private void registerAllCommands() {
        new Reflections("jish.commands").getSubTypesOf(Command.class).forEach(commandImpl -> {
            try {
                Command command = commandImpl.newInstance();
                Optional<Command> existingCommand = registeredCommands.stream().filter(cmd -> cmd.getName().equalsIgnoreCase(command.getName())).findAny();
                if (!existingCommand.isPresent()) {
                    registeredCommands.add(command);
                    System.out.println("Registered command: " + command.getName());
                } else {
                    System.out.println("Attempted to register two commands with the same name: " + existingCommand.get().getName());
                    System.out.println("Existing: " + existingCommand.get().getClass().getName());
                    System.out.println("Attempted: " + commandImpl.getName());
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    @EventSubscriber
    public void userJoined(UserJoinEvent e) throws DiscordException {
        IUser joinGuy = e.getUser();
        Util.sendMessage(e.getClient().getChannelByID(NEWBIES_ID), "Welcome to the RL Competitive server " + joinGuy.toString() + "!");
        Util.sendMessage(e.getClient().getChannelByID(NEWBIES_ID), "To get started on the server please do !ranks and do the command that corresponds to your competitive rank!");
    }

    @EventSubscriber
    public void botMention(MentionEvent e) {
        IMessage msg = e.getMessage();
        if(msg.mentionsEveryone()) {
            return;
        } else {
            EmbedBuilder builder = new EmbedBuilder();

            builder.withAuthorName("RLCompBot");
            builder.withAuthorIcon("https://i.imgur.com/QRVYlDC.png");
            builder.appendField("Hello!", "Hello I am RLCompBot. I was created by <@73463573900173312>", false);
            builder.appendField("What do I do?", "Right now I greet new members to the server and assign roles and platforms! I plan on maybe doing more in the future.", false);
            builder.appendField("Source Code: ", "[`GitHub`](https://github.com/Impervious/RLCompBot)", true);
            builder.withColor(255, 30, 229);
            builder.withTimestamp(System.currentTimeMillis());

            RequestBuffer.request(() -> e.getChannel().sendMessage(builder.build()));
        }
    }
}