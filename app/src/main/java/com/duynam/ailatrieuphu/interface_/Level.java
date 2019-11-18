package com.duynam.ailatrieuphu.interface_;

public class Level {

    public static String level(long diem){
        if (diem >= 1000000){
            return "Cấp 2";
        } else if (diem >= 10000000){
            return "Cấp 3";
        } else if (diem >= 150000000){
            return "Cấp 4";
        } else if (diem >= 1500000000){
            return "Cấp 5";
        }
        return "Cấp 1";
    }

}
