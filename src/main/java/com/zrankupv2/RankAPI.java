package com.zrankupv2;

import org.bukkit.entity.Player;

public class RankAPI {
    public static RankBase getRank(Player p){
        RankBase rank = zRankupV2.ranks.get(0);
        for(RankBase r : zRankupV2.ranks){
            if(p.hasPermission(r.getPermissao())){
                rank = r;
            }
        }
        return rank;
    }
    public static RankBase getNextRank(Player p){
        try{
            return zRankupV2.ranks.get(zRankupV2.ranks.indexOf(getRank(p))+1);
        }catch (Exception e){
            return zRankupV2.ultimorank;
        }
    }
}
