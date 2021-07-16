package com.zrankupv2;

public class RankBase {
    String rankname;
    int custo;
    int cash;
    String permissao;
    String setarperm;
    public RankBase(String rankname, int custo, int cash, String permissao, String setarperm){
        this.rankname = rankname;
        this.custo = custo;
        this.cash = cash;
        this.permissao = permissao;
        this.setarperm = setarperm;
    }

    public int getCusto() {
        return custo;
    }

    public int getCash() {
        return cash;
    }

    public String getPermissao() {
        return permissao;
    }

    public String getRankname() {
        return rankname;
    }

    public String getSetarperm() {
        return setarperm;
    }
}
