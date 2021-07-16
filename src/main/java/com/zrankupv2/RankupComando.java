package com.zrankupv2;

import com.zcash.CashManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankupComando implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            return true;
        }
        if(commandSender.hasPermission(zRankupV2.ultimorank.permissao)){
            commandSender.sendMessage(zRankupV2.getPlugin(zRankupV2.class).getConfig().getString("ultimorank"));
            return true;
        }
        boolean podeUpar = false;
        int money = RankAPI.getRank(((Player) commandSender).getPlayer()).getCusto();
        int cash = RankAPI.getRank(((Player) commandSender).getPlayer()).getCash();
        boolean temmoney = zRankupV2.econ.getBalance(((Player) commandSender).getPlayer()) >= money;
        boolean temcash = false;
            if(zRankupV2.zcash){
                temcash = CashManager.getCash(commandSender.getName()) >= cash;
                podeUpar = temmoney && temcash;
            }else{
                podeUpar = temmoney;
            }
        if(podeUpar){
            RankBase nextrank = RankAPI.getNextRank(((Player) commandSender).getPlayer());
            zRankupV2.econ.withdrawPlayer(((Player) commandSender).getPlayer(),money);
            if(zRankupV2.zcash){
                CashManager.setCash(commandSender.getName(),CashManager.getCash(commandSender.getName())-cash);
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),nextrank.getSetarperm().replace("%player%",commandSender.getName()));
            commandSender.sendMessage(zRankupV2.getPlugin(zRankupV2.class).getConfig().getString("upou").replace("%rankname%",nextrank.getRankname()));
            if(zRankupV2.getPlugin(zRankupV2.class).getConfig().getBoolean("mensagens.chat.ativar")){
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.sendMessage(zRankupV2.getPlugin(zRankupV2.class).getConfig().getString("mensagens.chat.aviso").replace("%player%",commandSender.getName()).replace("%rankname%",nextrank.getRankname()).replace("&","§"));
                }
            }
            if(zRankupV2.getPlugin(zRankupV2.class).getConfig().getBoolean("mensagens.actionbar.ativar")){
                for(Player p : Bukkit.getOnlinePlayers()){
                    ActionBar b = new ActionBar(zRankupV2.getPlugin(zRankupV2.class).getConfig().getString("mensagens.actionbar.aviso").replace("%player%",commandSender.getName()).replace("%rankname%",nextrank.getRankname()).replace("&","§"));
                    b.sendToPlayer(p);
                }
            }
        }else{
            commandSender.sendMessage(zRankupV2.getPlugin(zRankupV2.class).getConfig().getString("naotem"));
        }
        return false;
    }
}
