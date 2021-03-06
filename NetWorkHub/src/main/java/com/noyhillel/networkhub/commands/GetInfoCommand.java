package com.noyhillel.networkhub.commands;

import com.noyhillel.networkengine.exceptions.NewNetCommandException;
import com.noyhillel.networkengine.newcommand.CommandMeta;
import com.noyhillel.networkengine.newcommand.NetAbstractCommandHandler;
import com.noyhillel.networkengine.newcommand.Permission;
import com.noyhillel.networkengine.util.NetPlugin;
import com.noyhillel.networkengine.util.player.NetPlayer;
import com.noyhillel.networkhub.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Noy on 04/06/2014.
 */
@SuppressWarnings("Duplicates")
@Permission("hub.getinfo")
@CommandMeta(name = "getinfo", usage = "/getinfo <Online Player>", description = "The Get Usage command.")
public final class GetInfoCommand extends NetAbstractCommandHandler {

    @Override
    protected void playerCommand(Player player, String[] args) throws NewNetCommandException {
        if (args.length == 0) throw new NewNetCommandException("You have provided too few arguments!", NewNetCommandException.ErrorType.FEW_ARGUMENTS);
        if (args.length > 1) throw new NewNetCommandException("You have provided too many arguments!", NewNetCommandException.ErrorType.FEW_ARGUMENTS);
        Player target = Bukkit.getPlayerExact(args[0]);
        NetPlayer netPlayer = NetPlugin.getNetPlayerManager().getOnlinePlayer(target);
        if (netPlayer == null) throw new NewNetCommandException("An argument you have provided was null!", NewNetCommandException.ErrorType.NULL);
        String[] name = {"<name>", target.getName()};
        String[] nl = {"<nl>", "\n"};
        player.sendMessage(MessageManager.getFormat("whois.uuid", true, new String[]{"<uuid>", netPlayer.getUuid().toString()}, name, nl));
        player.sendMessage(MessageManager.getFormat("whois.nick", true, new String[]{"<nick>", netPlayer.getPlayer().getDisplayName()}, name));
        player.sendMessage(MessageManager.getFormat("whois.health", true, new String[]{"<health>", String.valueOf(netPlayer.getPlayer().getHealth())}, name));
        player.sendMessage(MessageManager.getFormat("whois.hunger", true, new String[]{"<hunger>", String.valueOf(netPlayer.getPlayer().getFoodLevel())},
                new String[]{"<saturation>", String.valueOf(player.getPlayer().getSaturation())}, name));
        player.sendMessage(MessageManager.getFormat("whois.location", true,
                new String[]{"<worldname>", netPlayer.getPlayer().getWorld().getName()},
                new String[] {"<x>", String.valueOf(netPlayer.getPlayer().getLocation().getBlockX())},
                new String[] {"<y>", String.valueOf(netPlayer.getPlayer().getLocation().getBlockY())},
                new String[] {"<z>", String.valueOf(netPlayer.getPlayer().getLocation().getBlockZ())}, name, nl));
        player.sendMessage(MessageManager.getFormat("whois.ip", true, new String[]{"<ip>", netPlayer.getIP()}, name));
        player.sendMessage(MessageManager.getFormat("whois.gamemode", true, new String[]{"<gamemode>", String.valueOf(netPlayer.getPlayer().getGameMode())}, name));
        player.sendMessage(MessageManager.getFormat("whois.op", true, new String[]{"<op>", String.valueOf(netPlayer.getPlayer().isOp())}, name));
        player.sendMessage(MessageManager.getFormat("whois.flying", true, new String[]{"<flying>", String.valueOf(netPlayer.getPlayer().getAllowFlight())}, name));
        //Nick, Health, Hunger, Exp, Location, IP, GameMode, OP, FlyMode
    }

    @Override
    protected void consoleCommand(ConsoleCommandSender sender, String[] args) throws NewNetCommandException {
        if (args.length == 0) throw new NewNetCommandException("You have provided too few arguments!", NewNetCommandException.ErrorType.FEW_ARGUMENTS);
        if (args.length > 1) throw new NewNetCommandException("You have provided too many arguments!", NewNetCommandException.ErrorType.FEW_ARGUMENTS);
        Player target = Bukkit.getPlayerExact(args[0]);
        NetPlayer netPlayer = NetPlugin.getNetPlayerManager().getOnlinePlayer(target);
        if (netPlayer == null) throw new NewNetCommandException("An argument you have provided was null!", NewNetCommandException.ErrorType.NULL);
        String[] name = {"<name>", target.getName()};
        String[] nl = {"<nl>", "\n"};
        sender.sendMessage(MessageManager.getFormat("whois.uuid", true, new String[]{"<uuid>", netPlayer.getUuid().toString()}, name, nl));
        sender.sendMessage(MessageManager.getFormat("whois.nick", true, new String[]{"<nick>", netPlayer.getPlayer().getDisplayName()}, name));
        sender.sendMessage(MessageManager.getFormat("whois.health", true, new String[]{"<health>", String.valueOf(netPlayer.getPlayer().getHealth())}, name));
        sender.sendMessage(MessageManager.getFormat("whois.hunger", true, new String[]{"<hunger>", String.valueOf(netPlayer.getPlayer().getFoodLevel())},
                new String[]{"<saturation>", String.valueOf(target.getPlayer().getSaturation())}, name));
        sender.sendMessage(MessageManager.getFormat("whois.location", true,
                new String[]{"<worldname>", netPlayer.getPlayer().getWorld().getName()},
                new String[] {"<x>", String.valueOf(netPlayer.getPlayer().getLocation().getBlockX())},
                new String[] {"<y>", String.valueOf(netPlayer.getPlayer().getLocation().getBlockY())},
                new String[] {"<z>", String.valueOf(netPlayer.getPlayer().getLocation().getBlockZ())}, name, nl));
        sender.sendMessage(MessageManager.getFormat("whois.ip", true, new String[]{"<ip>", netPlayer.getIP()}, name));
        sender.sendMessage(MessageManager.getFormat("whois.gamemode", true, new String[]{"<gamemode>", String.valueOf(netPlayer.getPlayer().getGameMode())}, name));
        sender.sendMessage(MessageManager.getFormat("whois.op", true, new String[]{"<op>", String.valueOf(netPlayer.getPlayer().isOp())}, name));
        sender.sendMessage(MessageManager.getFormat("whois.flying", true, new String[]{"<flying>", String.valueOf(netPlayer.getPlayer().getAllowFlight())}, name));
    }

    @Override
    public List<String> completeArgs(CommandSender sender, String[] args) {
        List<String> names = new ArrayList<>();
        if (args[0].equals("")) {
            names.addAll(Bukkit.getOnlinePlayers().stream().filter(player -> player.getName().startsWith(args[0])).map((Function<Player, String>) Player::getName).collect(Collectors.toList()));
            Collections.sort(names);
            return names;
        }
        return null;
    }
}