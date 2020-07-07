package de.kid2407.bteworldchanger.commands;

import de.kid2407.bteworldchanger.BTEWorldchanger;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * User: Tobias Franz
 * Date: 07.07.2020
 * Time: 17:30
 */
public class LobbyCommand extends Command {

    private static final String SERVER_LOBBY = "Lobby";

    public LobbyCommand() {
        super("lobby", null, "l");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            TextComponent message = new TextComponent("/lobby kann nur von einem Spieler ausgeführt werden.");
            message.setColor(ChatColor.YELLOW);
            sender.sendMessage(BTEWorldchanger.getPREFIX(), message);
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (player.getServer().getInfo().getName().equals(SERVER_LOBBY)) {
            TextComponent message = new TextComponent("Du bist bereits in der Lobby!");
            message.setColor(ChatColor.YELLOW);
            player.sendMessage(BTEWorldchanger.getPREFIX(), message);
            return;
        }

        player.sendMessage(BTEWorldchanger.getPREFIX(), new TextComponent("Verbinde zur Lobby."));

        ServerInfo lobby = ProxyServer.getInstance().getServerInfo(SERVER_LOBBY);
        player.connect(lobby);
    }
}
