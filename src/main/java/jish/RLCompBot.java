package jish;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.impl.events.shard.DisconnectedEvent;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.DiscordException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RLCompBot {

    public static RLCompBot bot;

    public static IDiscordClient client;
    public static IGuild guild;

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
        client.changePlayingText("Memes");
    }

    @EventSubscriber
    public void onDisconnectEvent(DisconnectedEvent event) {
        System.out.println("BOT DISCONNECTED");
        System.out.println("Reason: " + event.getReason());
    }

    @EventSubscriber
    public void onMessageEvent(MessageReceivedEvent e) {
        IMessage msg = e.getMessage();
        String text = msg.getContent();

        if (text.startsWith("!")) {
            String cmd = text.substring(1).split(" ")[0].toLowerCase();
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
        sendMessage(e.getClient().getChannelByID(Long.parseLong("338051063720443915")), "To get started on the server");
    }
}