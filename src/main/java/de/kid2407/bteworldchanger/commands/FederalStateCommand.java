package de.kid2407.bteworldchanger.commands;

import de.kid2407.bteworldchanger.BTEWorldchanger;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: Tobias Franz
 * Date: 07.07.2020
 * Time: 07:23
 */
public class FederalStateCommand extends Command implements TabExecutor {

    private static final String SERVER_1 = "Server1";
    private static final String SERVER_2 = "Server2";
    private static final String SERVER_3 = "Server3";
    private static final String SERVER_4 = "Server4";
    private static final String SERVER_5 = "Server5";

    private final HashMap<String, String> federalStates = new HashMap<>();

    public FederalStateCommand() {
        super("bundesland", null, "bl");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            TextComponent message = new TextComponent("Kann nur von einem Spieler benutzt werden.");
            message.setColor(ChatColor.YELLOW);
            sender.sendMessage(BTEWorldchanger.getPREFIX(), message);
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length == 0) {
            TextComponent message = new TextComponent("Du musst ein Bundesland angeben!");
            message.setColor(ChatColor.YELLOW);
            player.sendMessage(BTEWorldchanger.getPREFIX(), message);
            return;
        }

        String newState = args[0];
        String newServer = getServerForState(newState);
        String currentServer = player.getServer().getInfo().getName();

        if (newServer == null) {
            TextComponent message = new TextComponent("Unbekanntes Bundesland!");
            message.setColor(ChatColor.RED);
            player.sendMessage(BTEWorldchanger.getPREFIX(), message);
            return;
        }

        if (newServer.equalsIgnoreCase(currentServer)) {
            TextComponent message = new TextComponent("Du bist bereits auf dem richtigen Server.");
            player.sendMessage(BTEWorldchanger.getPREFIX(), message);
            return;
        }

        TextComponent message = new TextComponent("Verbinde nach " + newServer);
        player.sendMessage(BTEWorldchanger.getPREFIX(), message);

        ServerInfo target = ProxyServer.getInstance().getServerInfo(newServer);
        player.connect(target);
    }

    private String getServerForState(String state) {
        if (federalStates.isEmpty()) {
            generateFederalStatesMap();
        }

        return federalStates.getOrDefault(state, null);
    }

    private void generateFederalStatesMap() {
        federalStates.put("bw", SERVER_3);
        federalStates.put("by", SERVER_2);
        federalStates.put("be", SERVER_1);
        federalStates.put("bb", SERVER_1);
        federalStates.put("hb", SERVER_2);
        federalStates.put("hh", SERVER_4);
        federalStates.put("he", SERVER_2);
        federalStates.put("mv", SERVER_4);
        federalStates.put("ni", SERVER_1);
        federalStates.put("nw", SERVER_3);
        federalStates.put("rp", SERVER_4);
        federalStates.put("sl", SERVER_4);
        federalStates.put("sn", SERVER_5);
        federalStates.put("st", SERVER_2);
        federalStates.put("sh", SERVER_4);
        federalStates.put("th", SERVER_4);

        federalStates.put("Baden-Württemberg", SERVER_3);
        federalStates.put("Bayern", SERVER_2);
        federalStates.put("Berlin", SERVER_1);
        federalStates.put("Brandenburg", SERVER_1);
        federalStates.put("Bremen", SERVER_2);
        federalStates.put("Hamburg", SERVER_4);
        federalStates.put("Hessen", SERVER_2);
        federalStates.put("Mecklenburg-Vorpommern", SERVER_4);
        federalStates.put("Niedersachsen", SERVER_1);
        federalStates.put("Nordrhein-Westfalen", SERVER_3);
        federalStates.put("Rheinland-Pfalz", SERVER_4);
        federalStates.put("Saarland", SERVER_4);
        federalStates.put("Sachsen", SERVER_5);
        federalStates.put("Sachsen-Anhalt", SERVER_2);
        federalStates.put("Schleswig-Holstein", SERVER_4);
        federalStates.put("Thüringen", SERVER_4);
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        if (federalStates.isEmpty()) {
            generateFederalStatesMap();
        }

        Set<String> keys = federalStates.keySet();

        if (args.length > 0) {
            return keys.stream().filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        }

        return keys;
    }
}
