package com.noyhillel.survivalgames.command;

import com.noyhillel.networkengine.exceptions.NewNetCommandException;
import com.noyhillel.networkengine.newcommand.CommandMeta;
import com.noyhillel.networkengine.newcommand.NetAbstractCommandHandler;
import com.noyhillel.networkengine.newcommand.Permission;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by Noy on 10/07/2014.
 */
@Permission("survivalgames.setspawn")
@CommandMeta(name = "setspawn", description = "The SetSpawnCommand", usage = "/setspawn")
public class SetSpawnCommand extends NetAbstractCommandHandler { // NEEDS TO BE DONE DURING SETUP

    @Override
    protected void playerCommand(Player player, String[] args) throws NewNetCommandException {
        if (args.length > 0) throw new NewNetCommandException("Too many arguemnts.", NewNetCommandException.ErrorType.ManyArguments);
        Location loc = player.getLocation();
        player.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
        player.sendMessage(ChatColor.GREEN + "You have set the world spawn!");
    }
}