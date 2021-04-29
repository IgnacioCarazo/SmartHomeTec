package com.smarthometec.mobileapp.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
/**
 * @class DBHelper
 * Clase constructura de la base de datos para los usuarios
 * @author JosephJimenez
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    /**
     * Crea base de datos inexistente
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userstable(id_user text  PRIMARY KEY NOT NULL," + "password_user text NOT NULL)");
        db.execSQL("insert into userstable(id_user,password_user) values('admin','admin')");
    }
    /**
     * Administra versiones de la base de datos creada
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table userstable(id_user text  PRIMARY KEY NOT NULL," + "password_user text NOT NULL)");
        db.execSQL("insert into userstable(id_user,password_user) values('admin','admin')");
    }
}
