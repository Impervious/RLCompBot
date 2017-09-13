package jish;

import org.apache.commons.io.FileUtils;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class Util {

    public static File botPath;

    static {
        try {
            botPath = new File(RLCompBot.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    static Optional<String> getBotToken() {
        try {
            File tokenFile = new File(botPath, "token.txt");
            if(tokenFile.exists()) {
                String token = FileUtils.readFileToString(tokenFile, (String) null);
                if(!token.equalsIgnoreCase("TOKEN") && !token.isEmpty()) {
                    return Optional.of(token);
                } else {
                    return Optional.empty();
                }
            } else {
                FileUtils.writeStringToFile(tokenFile, "TOKEN", (String) null);
                return Optional.empty();
            }
        } catch(IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
