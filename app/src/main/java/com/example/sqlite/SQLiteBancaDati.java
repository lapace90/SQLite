package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteBancaDati extends SQLiteOpenHelper {
    public static final String BASE_NOM = "LaMiaBancaDati.db";
    public static final int BASE_VERSION = 3;
    public static final String NOM_TABLE = "T_clients";
    public static final String COL0 = "IdCliente";
    public static final String COL1 = "Cognome";
    public static final String COL2 = "Nome";
    public static final String COL3 = "Eta";

    public SQLiteBancaDati(Context context) {
        super(context, BASE_NOM, null, BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE " + NOM_TABLE +
                " ("
                + COL0 + " integer primary key autoincrement,"
                + COL1 + " text not null,"
                + COL2 + " text not null,"
                + COL3 + " integer not null);";
        Log.d("DataBase", "strSql" + strSql);
        db.execSQL(strSql);
        Log.d("DataBase", "La tua Banca Dati é stata creata correttamente." + NOM_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DataBase", "oldVersion: " + oldVersion);
        Log.d("DataBase", "newVersion: " + newVersion);
        String strSql = "DROP TABLE IF EXISTS " + NOM_TABLE + ";";
        Log.d("DataBase", "Richiesta di Aggiornamento sql : " + strSql);
        db.execSQL(strSql);
        onCreate(db);
        Log.d("DataBase", "Method Upgrade Call: " + NOM_TABLE);
    }

    public void insertionCLIENTS(String Cognome, String Nome, Integer Eta) {
        Cognome = Cognome.replace("'", " ");
        Nome = Nome.replace("'", " ");
        String strSql = "INSERT INTO " + NOM_TABLE + "(" +COL1 + "," +COL2 + "," +COL3 + ")" + "values ('" + Cognome + "','" + Nome + "'," + Eta + ");";
        Log.d("DataBase", "StrSql insert: " +strSql);
        getWritableDatabase().execSQL(strSql);
        Log.d("DataBase", "Clienti inseriti con successo!");
    }

    public Cursor leggiTabella() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor myCursor = db.rawQuery("select * from " + NOM_TABLE, null);
        return myCursor;
    }


//    public Cursor leggiTabella(int idEta) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String strSql = "select * from " + NOM_TABLE + " where " + COL0 + " = " idEta + " or " + COL3 + " = " + idEta + ";";
//        Cursor myCursor = db.rawQuery("select * from " + NOM_TABLE, null);
//        return myCursor;
//    }
//
//    public Cursor leggiTabella(String nomeCompleto) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String strSql = "select * from " + NOM_TABLE + " where " + COL1 + " = "+ nomeCompleto + " or " + COL2 + " = " + nomeCompleto + ";";
//        Cursor myCursor = db.rawQuery("select * from " + NOM_TABLE, null);
//        return myCursor;
//    }
}
