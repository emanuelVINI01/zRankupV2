package com.zrankupv2;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class RankPlaceholder extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "zrankupv2";
    }

    @Override
    public String getAuthor() {
        return "emanuelVINI";
    }

    @Override
    public String getVersion() {
        return "1.1";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("rank")){
            return RankAPI.getRank(Bukkit.getPlayer(player.getUniqueId())).getRankname();
        }
        if(params.equalsIgnoreCase("proximo")){
            if(RankAPI.getRank(Bukkit.getPlayer(player.getUniqueId())) == zRankupV2.ultimorank){
                return zRankupV2.ultimorank.getRankname();
            }
            return RankAPI.getNextRank(Bukkit.getPlayer(player.getUniqueId())).getRankname();
        }
        return null;
    }
}
