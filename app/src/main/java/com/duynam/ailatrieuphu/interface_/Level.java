package com.duynam.ailatrieuphu.interface_;

public class Level {

    public static String level(long diem){
        if (diem <= 1000000){
            return "Cấp 1";
        } else if (10000000 <= diem || diem >= 10000000){
            return "Cấp 3";
        } else if (diem >= 150000000){
            return "Cấp 4";
        } else if (diem >= 1500000000){
            return "Cấp 5";
        }
        return "Cấp 1";
    }

    public static String covert_score(long diem){
        if (diem >= 1000000){
            String tien = diem/1000000.0 + " " + "triệu";
            return tien;
        } else if(diem >= 1000000000 || diem > 1000000){
            String tien = String.format("%.2f tỷ", diem/ 1000000000.0);
            return tien;
        }
        return diem+"";
    }

    public static long covert_long(String diem){
        String[] a = diem.split(" ");
        if (a[1].equals("triệu")){
            a[0].replace(".", "");
            return Long.parseLong(a[0] + "000000");
        }else {

        }
        return 0;
    }

    public static long exp(String level){
        if (level.equals("Cấp 1")){
            return 10000000;
        } else if (level.equals("Cấp 2")){
            return 50000000;
        } else if (level.equals("Cấp 3")){
            return 200000000;
        }
        return 10000000;
    }

}
