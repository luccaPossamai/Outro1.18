package com.lucca.mohard.math;

public class Functions {

    //http://prntscr.com/1qyxx5n

    public static double earlyGameScale(double x){
        double fator = Math.min(x, 2000D) - 625;
        fator = pot(fator, 1D / 5D);
        double p1 = pot(625, 1D / 5D);
        fator = fator + p1;
        fator = fator * 420;
        return fator;
    }

    public static double lateGameScale(double x){
        double fator = Math.min(x, 2000D) / 210;
        double p1 = pot(1250, 1D / 5D);
        fator = fator - p1;
        double p2 = pot(fator, 5);
        fator = p2 + 1250;
        return fator;
    }
    public static double constantGameScale(double x){
        double fator = Math.min(x, 2000D);
        return 2 * fator;
    }

    private static double pot(double x, double y){
        return x < 0 ? (Math.pow(x * (-1), y)) * (-1) : Math.pow(x, y);
    }
}
