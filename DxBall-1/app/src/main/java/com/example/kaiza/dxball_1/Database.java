package com.example.kaiza.dxball_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kaizar on 5/16/2016.
 */
public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="Dx_Ball.db";
    public static final String TABLE_NAME = "Score_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SCORE";
    SQLiteDatabase db;


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);

       // db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+"("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_2+" TEXT,"+COL_3+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }

    public boolean InsertScore(String name,String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();



        contentValues.put(COL_2,name);
        contentValues.put(COL_3,Integer.valueOf(score));

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1){
            return false;
        }else {
            return  true;
        }
    }
    public Cursor getTop5Scorer(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("Select * from "+TABLE_NAME+" ORDER BY "+COL_3+"  DESC limit 5",null);
        return  res;
    }

}
