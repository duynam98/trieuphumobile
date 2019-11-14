package com.duynam.ailatrieuphu.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.duynam.ailatrieuphu.model.Cauhoi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataAdapter extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static String DB_NAME = "trieuphu_duan2";
    private SQLiteDatabase sqlDB, sqlDB1;
    private final Context context;
    private static String TABLE_NAME = "Tb_ailatrieuphu";

    public DataAdapter(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    public void create(){
        setDBpath();
        File file = new File(DB_PATH + DB_NAME);
        if (!file.exists()){
            this.getReadableDatabase();
            this.close();
            try{
                InputStream input = context.getAssets().open(DB_NAME);
                String filename = DB_PATH + DB_NAME;
                OutputStream output = new FileOutputStream(filename);
                byte[] mBuffer = new byte[1024];
                int length;
                while ((length = input.read(mBuffer)) > 0)
                    output.write(mBuffer, 0, length);
                output.flush();
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void open(){
        sqlDB = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public synchronized void close() {
        super.close();
        if(sqlDB != null){
            sqlDB.close();
        }
    }

    public void setDBpath(){
        if (Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }else{
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
    }

    public ArrayList<Cauhoi> getData() throws SQLException{
        sqlDB1 = this.getReadableDatabase();
        ArrayList<Cauhoi> cauhoilist = new ArrayList<>();
        Cursor cursor = sqlDB1.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()){
            cauhoilist.add(new Cauhoi(cursor.getString(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("cauhoi")),
                    cursor.getString(cursor.getColumnIndex("daA")),
                    cursor.getString(cursor.getColumnIndex("daB")),
                    cursor.getString(cursor.getColumnIndex("daC")),
                    cursor.getString(cursor.getColumnIndex("daD")),
                    cursor.getString(cursor.getColumnIndex("daDung"))));
        }
        return cauhoilist;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
