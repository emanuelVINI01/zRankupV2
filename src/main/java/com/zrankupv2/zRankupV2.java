package com.zrankupv2;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class zRankupV2 extends JavaPlugin {
    public static List<RankBase> ranks = new ArrayList<>();
    public static RankBase ultimorank;
    public static boolean zcash = false;
    public static Economy econ;
    public static boolean byLoader = false;
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    @Override
    public void onEnable() {

        super.onEnable();
        saveDefaultConfig();
        zcash = getServer().getPluginManager().isPluginEnabled("zCash");
        for (String key : getConfig().getConfigurationSection("ranks").getKeys(false)) {
            ConfigurationSection block = getConfig().getConfigurationSection("ranks." + key);
            ranks.add(new RankBase(key,block.getInt("money"),block.getInt("cash"),block.getString("permissao"),block.getString("setarperm")));
        }
        ultimorank = ranks.get(ranks.size()-1);
        getLogger().info(String.format("O ultimo rank e %s.", ultimorank.getRankname()));
        getLogger().info("Ranks:");
        for(RankBase r : ranks){
            getLogger().info("   "+r.getRankname());
        }
        getCommand("rankup").setExecutor(new RankupComando());
        if(!zcash){
            getLogger().warning("zCash não encontrado, desabilitando modo com cash.");
        }
        new RankPlaceholder().register();
        setupEconomy();
        getLogger().info("\nAutor: emanuel VINI\nVersão: 1.1\nSuporte: emanuel VINI#8000");
    }
}
