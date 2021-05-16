package com.smarthometec.mobileapp.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.smarthometec.mobileapp.Login.db;
import static com.smarthometec.mobileapp.Login.dbHelper;
/**
 * @class DatabaseHelper
 * Define y manipula base de datos
 * @author JosephJimenez
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    public static final String SERVER_URL = "https://10.0.2.2:5001/";
    private static final String DATABASE_NAME = "databaseSmartHomeTec";
    // Table Names
    private static final String TABLE_CLIENT = "TABLE_CLIENT";
    private static final String TABLE_DEVICE = "TABLE_DEVICE";
    private static final String TABLE_ROOM = "TABLE_ROOM";
    private static final String TABLE_CONTROL = "TABLE_CONTROL";
    // Common column names
    private static final String KEY_EMAIL = "userEmail";
    private static final String KEY_ROOM_NAME = "room_name";
    private static final String KEY_DEVICE_SERIALNUMBER = "serialNumber";
    // CLIENT Table - column names
    private static final String COLUMN_CLIENT_NAME = "name";
    private static final String COLUMN_CLIENT_FLastName = "primaryLastName";
    private static final String COLUMN_CLIENT_SLastName = "secondaryLastName";
    private static final String COLUMN_CLIENT_PASSWORD = "password";
    private static final String COLUMN_CLIENT_CONTINENT = "continent";
    private static final String COLUMN_CLIENT_COUNTRY = "country";
    private static final String COLUMN_CLIENT_DELIVERY = "deliveryAddress";
    // DEVICE Table - column names
    private static final String COLUMN_DEVICE_DESCRIPTION = "description";
    private static final String COLUMN_DEVICE_CONSUMPTION = "consumption";
    private static final String COLUMN_DEVICE_BRAND = "brand";
    private static final String COLUMN_DEVICE_TYPE = "type";
    private static final String COLUMN_DEVICE_CREATION = "createdDate";
    private static final String COLUMN_DEVICE_ACTIVE = "active";
    // Control Table - column names
    private static final String COLUMN_CONTROL_DATE = "date";
    private static final String COLUMN_CONTROL_TOTAL_TIME = "time";
    private static final String COLUMN_CONTROL_ID_DATE = "id";
    // Table Create Statements
    // CLIENT table create statement
    private static final String CREATE_TABLE_CLIENT = "CREATE TABLE " + TABLE_CLIENT
            + "(" + KEY_EMAIL + " TEXT PRIMARY KEY," + COLUMN_CLIENT_NAME
            + " TEXT," + COLUMN_CLIENT_FLastName + " TEXT," + COLUMN_CLIENT_SLastName
            + " TEXT," + COLUMN_CLIENT_PASSWORD + " TEXT," + COLUMN_CLIENT_CONTINENT + " TEXT,"
            + COLUMN_CLIENT_COUNTRY + " TEXT," + COLUMN_CLIENT_DELIVERY + " TEXT" + ")";
    // ROOM table create statement
    private static final String CREATE_TABLE_ROOM = "CREATE TABLE " + TABLE_ROOM
            + "(" + KEY_ROOM_NAME + " TEXT PRIMARY KEY ," + KEY_EMAIL + " TEXT, "
            + " FOREIGN KEY (" +KEY_EMAIL+ ") REFERENCES "+TABLE_CLIENT+"("+KEY_EMAIL+"));";
    // DEVICE table create statement
    private static final String CREATE_TABLE_DEVICE = "CREATE TABLE " + TABLE_DEVICE
            + "(" + KEY_DEVICE_SERIALNUMBER + " INTEGER PRIMARY KEY,"+  COLUMN_DEVICE_DESCRIPTION + " TEXT,"  +  COLUMN_DEVICE_CONSUMPTION + " TEXT,"
            + COLUMN_DEVICE_BRAND + " TEXT," + COLUMN_DEVICE_TYPE + " TEXT,"+ COLUMN_DEVICE_CREATION+ " TEXT,"+ KEY_EMAIL+ " TEXT,"+ COLUMN_DEVICE_ACTIVE+ " TEXT,"
            + KEY_ROOM_NAME + " TEXT,"
            + " FOREIGN KEY (" +KEY_ROOM_NAME+ ") REFERENCES "+TABLE_ROOM+"("+KEY_ROOM_NAME+"));";
    // CONTROL table create statement
    private static final String CREATE_TABLE_CONTROL = "CREATE TABLE " + TABLE_CONTROL
            + "(" +COLUMN_CONTROL_ID_DATE+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_CONTROL_TOTAL_TIME + " INTEGER, "+ COLUMN_CONTROL_DATE + " TEXT ,"
            + KEY_DEVICE_SERIALNUMBER + " INTEGER,"
            + " FOREIGN KEY (" +KEY_DEVICE_SERIALNUMBER+ ") REFERENCES "+TABLE_DEVICE+"("+KEY_DEVICE_SERIALNUMBER+"));";
    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_ROOM);
        db.execSQL(CREATE_TABLE_DEVICE);
        db.execSQL(CREATE_TABLE_CONTROL);
        //populated first data CLIENT table
        db.execSQL("INSERT INTO " +TABLE_CLIENT+ "(" +KEY_EMAIL+ "," +COLUMN_CLIENT_NAME+ "," +COLUMN_CLIENT_FLastName
                + "," +COLUMN_CLIENT_SLastName+ "," +COLUMN_CLIENT_PASSWORD+ "," +COLUMN_CLIENT_CONTINENT
                + "," +COLUMN_CLIENT_COUNTRY+ "," +COLUMN_CLIENT_DELIVERY+ ")" +
                "values('admin@admin','adminName','adminFLastName','adminSLastName','admin','adminContinent','adminCountry','adminAddress')");
        //populated first data ROOM table
        db.execSQL("INSERT INTO " +TABLE_ROOM+ "(" +KEY_EMAIL+ "," +KEY_ROOM_NAME + ")" +
                "values('admin@admin','DefaultRoom')");
        //populated first data DEVICE table
        db.execSQL("INSERT INTO " +TABLE_DEVICE+ "(" +KEY_DEVICE_SERIALNUMBER+ "," +COLUMN_DEVICE_DESCRIPTION+ "," +COLUMN_DEVICE_CONSUMPTION
                +"," +COLUMN_DEVICE_BRAND+ "," +COLUMN_DEVICE_TYPE+ "," + COLUMN_DEVICE_CREATION+ "," + KEY_EMAIL+ "," + COLUMN_DEVICE_ACTIVE+ ","  +KEY_ROOM_NAME +  ")" +
                "values(0000000,'defaultDescript','0W','DefaultBrand','DefaultType','DefaultDate','DefaultRoom','admin@admin','true')");
        db.execSQL("INSERT INTO " +TABLE_CONTROL+ "(" +COLUMN_CONTROL_ID_DATE+ ","  +COLUMN_CONTROL_TOTAL_TIME+ "," +COLUMN_CONTROL_DATE+ "," +KEY_DEVICE_SERIALNUMBER+ ")" +
                "values(0,0,'HOY',0000000)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTROL);
        onCreate(db);
    }
    public void insertClient(@Nullable Context context, String userEmail, String name, String primaryLastName, String secondaryLastName, String password, String continent, String country, String deliveryAddress){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_EMAIL,userEmail);
        cv.put(COLUMN_CLIENT_NAME,name);
        cv.put(COLUMN_CLIENT_FLastName,primaryLastName);
        cv.put(COLUMN_CLIENT_SLastName,secondaryLastName);
        cv.put(COLUMN_CLIENT_PASSWORD,password);
        cv.put(COLUMN_CLIENT_CONTINENT, continent);
        cv.put(COLUMN_CLIENT_COUNTRY,country);
        cv.put(COLUMN_CLIENT_DELIVERY,deliveryAddress);
        long result = db.insert(TABLE_CLIENT,null,cv);
        if(result == -1){
            System.out.println("Failed adding Client at LocalDatabase");
        }else{
            //Toast.makeText(context, "Added Client at LocalDatabase Successfully", Toast.LENGTH_SHORT).show();
            System.out.println("Added Client at LocalDatabase Successfully");
        }
    }
    /**
     * Funcion insertRoom que añade un cuarto
     * @param context contexto
     * @param name nombre del cuarto
     * @param email email del usuario
     */
    public void insertRoom(@Nullable Context context, String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_EMAIL,email);
        cv.put(KEY_ROOM_NAME,name);
        long result = db.insert(TABLE_ROOM,null,cv);
        if(result == -1){
            System.out.println("Failed adding Room at LocalDatabase");
        }else{
            System.out.println("Added Room at LocalDatabase Successfully");
        }
    }
    /**
     * Funcion insertDevice que añade un Device
     * @param context contexto
     * @param serialNumber numero serial de dispositivo
     * @param consume consumo de dispositivo
     * @param brand marca de dispositivo
     * @param type Nombretipo de dispositivo
     * @param description descripcion de dispositivo
     * @param room cuarto de dispositivo
     * @param date_created creacion de dispositivo
     */
    public void insertDevice(@Nullable Context context, int serialNumber, String description, String consume, String brand, String type, String date_created,String room,String userEmail, boolean active ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_DEVICE_SERIALNUMBER,serialNumber);
        cv.put(COLUMN_DEVICE_DESCRIPTION,description);
        cv.put(COLUMN_DEVICE_CONSUMPTION,consume);
        cv.put(COLUMN_DEVICE_BRAND,brand);
        cv.put(COLUMN_DEVICE_TYPE, type);
        cv.put(KEY_EMAIL,brand);
        cv.put(COLUMN_DEVICE_ACTIVE, type);
        cv.put(COLUMN_DEVICE_CREATION,date_created);
        cv.put(KEY_ROOM_NAME,room);
        long result = db.insert(TABLE_DEVICE,null,cv);
        if(result == -1){
            System.out.println("Failed adding a Device at LocalDatabase");
        }else{
            System.out.println("Added a Device in LocalDatabase Successfully");
        }
    }
    /**
     * Funcion insertControl que añade un dispositivo en la base de control de dispositivos
     * @param context contexto
     * @param serialNumber numero serial del dispositivo
     * @param date fecha de uso de dispositivo
     * @param time cantidad de tiempo usado
     */
    public void insertControl(@Nullable Context context, int serialNumber, String date, int time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_DEVICE_SERIALNUMBER,serialNumber);
        cv.put(COLUMN_CONTROL_DATE,date);
        cv.put(COLUMN_CONTROL_TOTAL_TIME, String.valueOf(time));
        long result = db.insert(TABLE_CONTROL,null,cv);
        if(result == -1){
            System.out.println("Failed adding a Device at LocalDatabase");
        }else{
            System.out.println("Added Time Control in LocalDatabase Successfully");
        }
    }
    /**
     * Funcion readAllData obtiene valores de la tabla especifica
     * @param table tabla especificada
     * @return cursor que son los valores obtenidos
     */
    public Cursor readAllData(String table){
        String query = "select * from " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
    /**
     * Borra todos los datos de una tabla
     * @param table tabla para borrar sus datos
     * @return
     */
    public Cursor deleteAllData(String table) {
        String query = "DELETE FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor getDeviceDataOfRoom(String room){
        String query = "SELECT * FROM TABLE_DEVICE WHERE " + KEY_ROOM_NAME + "= '"+room+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
}
 /*
           String query = "INSERT INTO TABLE_CLIENT(userEmail,name,primaryLastName,secondaryLastName,password,continent,country,deliveryAddress) SELECT "
                   + "'" + userEmail+ "','" +name+ "','" +primaryLastName+ "','" +secondaryLastName+ "','" +password+ "','" +continent+ "','" +country+ "','" +deliveryAddress +"' WHERE NOT EXISTS(SELECT userEmail FROM TABLE_CLIENT WHERE userEmail = '"+ userEmail+ "')";
           SQLiteDatabase db = this.getWritableDatabase();
           if(db!=null){
               db.rawQuery(query,null);
           }
     */