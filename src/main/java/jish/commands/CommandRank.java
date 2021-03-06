package jish.commands;

import jish.RLCompBot;
import jish.Roles;
import jish.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;

import java.util.List;

public class CommandRank implements Command {
    @Override
    public String getName() {
        return "rank";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void execute(RLCompBot bot, IDiscordClient client, String[] args, IGuild guild, IMessage message, boolean isPrivate) {
        IChannel newbie = client.getChannelByID(RLCompBot.NEWBIES_ID);
        IChannel edit_rank = client.getChannelByID(RLCompBot.EDIT_RANK_ID);
        IChannel test = client.getChannelByID(RLCompBot.TEST_GENERAL_ID);
        IChannel bot_test = client.getChannelByID(RLCompBot.BOT_TEST_ID);

        if (!isPrivate) {
            if (message.getChannel().equals(edit_rank) ||  message.getChannel().equals(newbie) || message.getChannel().equals(bot_test) || message.getChannel().equals(test)) {
                try {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("bronze")) {
                            message.getAuthor().addRole(message.getGuild().getRoleByID(Roles.BRONZE.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.UNRANKED.ID));
                            Util.deleteMessage(message);
                            Util.sendMessage(message.getChannel(), message.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                            Util.sendMessage(message.getChannel(), "Please make sure to set your platform in " + guild.getChannelByID(RLCompBot.ADD_PLATFORM_ID).mention() + " if you haven't already");
                        } else if (args[0].equalsIgnoreCase("silver")) {
                            message.getAuthor().addRole(message.getGuild().getRoleByID(Roles.SILVER.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.UNRANKED.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.BRONZE.ID));
                            Util.deleteMessage(message);
                            Util.sendMessage(message.getChannel(), message.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                            Util.sendMessage(message.getChannel(), "Please make sure to set your platform in " + guild.getChannelByID(RLCompBot.ADD_PLATFORM_ID).mention() + " if you haven't already");
                        } else if (args[0].equalsIgnoreCase("gold")) {
                            message.getAuthor().addRole(message.getGuild().getRoleByID(Roles.GOLD.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.UNRANKED.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.SILVER.ID));
                            Util.deleteMessage(message);
                            Util.sendMessage(message.getChannel(), message.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                            Util.sendMessage(message.getChannel(), "Please make sure to set your platform in " + guild.getChannelByID(RLCompBot.ADD_PLATFORM_ID).mention() + " if you haven't already");
                        } else if (args[0].equalsIgnoreCase("platinum") || args[0].equalsIgnoreCase("plat")) {
                            message.getAuthor().addRole(message.getGuild().getRoleByID(Roles.PLATINUM.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.UNRANKED.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.GOLD.ID));
                            Util.deleteMessage(message);
                            Util.sendMessage(message.getChannel(), message.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                            Util.sendMessage(message.getChannel(), "Please make sure to set your platform in " + guild.getChannelByID(RLCompBot.ADD_PLATFORM_ID).mention() + " if you haven't already");
                        } else if (args[0].equalsIgnoreCase("diamond") || args[0].equalsIgnoreCase("dia")) {
                            message.getAuthor().addRole(message.getGuild().getRoleByID(Roles.DIAMOND.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.UNRANKED.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.PLATINUM.ID));
                            Util.deleteMessage(message);
                            Util.sendMessage(message.getChannel(), message.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                            Util.sendMessage(message.getChannel(), "Please make sure to set your platform in " + guild.getChannelByID(RLCompBot.ADD_PLATFORM_ID).mention() + " if you haven't already");
                        } else if (args[0].equalsIgnoreCase("champion") || args[0].equalsIgnoreCase("champ")) {
                            message.getAuthor().addRole(message.getGuild().getRoleByID(Roles.CHAMPION.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.UNRANKED.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.DIAMOND.ID));
                            Util.deleteMessage(message);
                            Util.sendMessage(message.getChannel(), message.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                            Util.sendMessage(message.getChannel(), "Please make sure to set your platform in " + guild.getChannelByID(RLCompBot.ADD_PLATFORM_ID).mention() + " if you haven't already");
                        } else if (args[0].equalsIgnoreCase("gc")) {
                            message.getAuthor().addRole(message.getGuild().getRoleByID(Roles.GRAND_CHAMPION.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.UNRANKED.ID));
                            message.getAuthor().removeRole(message.getGuild().getRoleByID(Roles.CHAMPION.ID));
                            Util.deleteMessage(message);
                            Util.sendMessage(message.getChannel(), message.getAuthor() + ", you're all set! Please let a staff member know if you have any questions.");
                            Util.sendMessage(message.getChannel(), "Please make sure to set your platform in " + guild.getChannelByID(RLCompBot.ADD_PLATFORM_ID).mention() + " if you haven't already");
                        } else {
                            Util.sendMessage(message.getChannel(), "The correct format is ```!rank <rank name>```");
                            Util.sendMessage(message.getChannel(), "For a list of rank names checked the pinned message in this channel.");
                        }
                    }
                }  catch(Exception e){
                    e.printStackTrace();
                }
            } else {
                Util.sendMessage(message.getChannel(), "Please use the " + guild.getChannelByID(RLCompBot.ADD_PLATFORM_ID).mention() + " channel for that command.");
            }
        }
    }
}