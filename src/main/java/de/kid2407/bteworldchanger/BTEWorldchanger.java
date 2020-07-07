package de.kid2407.bteworldchanger;

import de.kid2407.bteworldchanger.commands.FederalStateCommand;
import de.kid2407.bteworldchanger.commands.LobbyCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public final class BTEWorldchanger extends Plugin {

    private static final TextComponent PREFIX = new TextComponent(ChatColor.GREEN + "[BTE-Worldchanger] " + ChatColor.WHITE);

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new LobbyCommand());
        getProxy().getPluginManager().registerCommand(this, new FederalStateCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static TextComponent getPREFIX() {
        return (TextComponent) PREFIX.duplicate();
    }
}
