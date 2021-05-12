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
/**
 * @class DatabaseHelper
 * Define y manipula base de datos
 * @author JosephJimenez
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "databaseSmartHomeTec";
    // Table Names
    private static final String TABLE_CLIENT = "TABLE_CLIENT";
    private static final String TABLE_DEVICE = "TABLE_DEVICE";
    private static final String TABLE_DEVICE_TYPE = "TABLE_DEVICE_TYPE";
    private static final String TABLE_ROOM = "TABLE_ROOM";
    private static final String TABLE_CONTROL = "TABLE_CONTROL";
    // Common column names
    private static final String KEY_EMAIL = "email_user";
    private static final String KEY_ROOM_NAME = "room_name";
    private static final String KEY_DEVICE_TYPE = "type";
    private static final String KEY_DEVICE_SERIALNUMBER = "serialNumber";
    // CLIENT Table - column names
    private static final String COLUMN_CLIENT_NAME = "name";
    private static final String COLUMN_CLIENT_FLastName = "primaryLastName";
    private static final String COLUMN_CLIENT_SLastName = "secondaryLastName";
    private static final String COLUMN_CLIENT_PASSWORD = "password_user";
    private static final String COLUMN_CLIENT_CONTINENT = "continent";
    private static final String COLUMN_CLIENT_COUNTRY = "country";
    private static final String COLUMN_CLIENT_DELIVERY = "deliveryAddress";
    // ROOM Table - column names
    private static final String COLUMN_ROOM_OCCUPIED = "occupied";
    // DEVICE Table - column names
    private static final String COLUMN_DEVICE_NAME = "name_device";
    private static final String COLUMN_DEVICE_CONSUMPTION = "consumption";
    private static final String COLUMN_DEVICE_BRAND = "brand";
    private static final String COLUMN_DEVICE_ASSOCIATED = "associated";
    private static final String COLUMN_DEVICE_OWNER = "owner";
    private static final String COLUMN_DEVICE_DISTRIBUTOR = "idDistributor";
    private static final String COLUMN_DEVICE_PRICE = "price";
    private static final String COLUMN_DEVICE_CREATION = "date_created";
    // DEVICE TYPE Table - column names
    private static final String COLUMN_DEVICE_DESCRIPTION = "description";
    private static final String COLUMN_DEVICE_WARRANTY = "warranty";
    // DEVICE TYPE Table - column names
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
            + "(" + KEY_EMAIL + " TEXT ," + KEY_ROOM_NAME + " TEXT PRIMARY KEY,"
            + COLUMN_ROOM_OCCUPIED + " TEXT" + ")";
    // DEVICE table create statement
    private static final String CREATE_TABLE_DEVICE = "CREATE TABLE " + TABLE_DEVICE
            + "(" + KEY_DEVICE_SERIALNUMBER + " INTEGER PRIMARY KEY,"
            + COLUMN_DEVICE_NAME + " TEXT," + COLUMN_DEVICE_CONSUMPTION
            + " TEXT," + COLUMN_DEVICE_BRAND + " TEXT," + COLUMN_DEVICE_ASSOCIATED
            + " TEXT," + KEY_DEVICE_TYPE + " TEXT," + COLUMN_DEVICE_OWNER + " TEXT,"
            + " TEXT,"+ COLUMN_DEVICE_DISTRIBUTOR + " INTEGER," + COLUMN_DEVICE_PRICE + " INTEGER," + KEY_ROOM_NAME + " TEXT," + COLUMN_DEVICE_CREATION+ " TEXT" + ")";
    // DEVICE_TYPE table create statement
    private static final String CREATE_TABLE_DEVICE_TYPE = "CREATE TABLE " + TABLE_DEVICE_TYPE
            + "(" +KEY_DEVICE_TYPE+ " TEXT PRIMARY KEY ," + COLUMN_DEVICE_DESCRIPTION + " TEXT ,"
            + COLUMN_DEVICE_WARRANTY + " TEXT" + ")";
    // CONTROL table create statement
    private static final String CREATE_TABLE_CONTROL = "CREATE TABLE " + TABLE_CONTROL
            + "(" +COLUMN_CONTROL_ID_DATE+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_DEVICE_SERIALNUMBER + " INTEGER, "+ COLUMN_CONTROL_DATE + " TEXT ,"
            + COLUMN_CONTROL_TOTAL_TIME + " INTEGER" + ")";
    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_ROOM);
        db.execSQL(CREATE_TABLE_DEVICE);
        db.execSQL(CREATE_TABLE_DEVICE_TYPE);
        db.execSQL(CREATE_TABLE_CONTROL);
        //populated first data CLIENT table
        db.execSQL("INSERT INTO " +TABLE_CLIENT+ "(" +KEY_EMAIL+ "," +COLUMN_CLIENT_NAME+ "," +COLUMN_CLIENT_FLastName
                + "," +COLUMN_CLIENT_SLastName+ "," +COLUMN_CLIENT_PASSWORD+ "," +COLUMN_CLIENT_CONTINENT
                + "," +COLUMN_CLIENT_COUNTRY+ "," +COLUMN_CLIENT_DELIVERY+ ")" +
                "values('admin@admin','adminName','adminFLastName','adminSLastName','admin','adminContinent','adminCountry','adminAddress')");
        //populated first data ROOM table
        db.execSQL("INSERT INTO " +TABLE_ROOM+ "(" +KEY_EMAIL+ "," +KEY_ROOM_NAME+ "," +COLUMN_ROOM_OCCUPIED+ ")" +
                "values('admin@admin','DefaulRoom',null)");
        //populated first data DEVICE table
        db.execSQL("INSERT INTO " +TABLE_DEVICE+ "(" +KEY_DEVICE_SERIALNUMBER+ "," +COLUMN_DEVICE_NAME+ "," +COLUMN_DEVICE_CONSUMPTION
                +"," +COLUMN_DEVICE_BRAND+ "," +COLUMN_DEVICE_ASSOCIATED+ "," +KEY_DEVICE_TYPE+ "," +COLUMN_DEVICE_OWNER
                + "," +COLUMN_DEVICE_DISTRIBUTOR+"," +COLUMN_DEVICE_PRICE+ "," +KEY_ROOM_NAME + "," + COLUMN_DEVICE_CREATION+ ")" +
                "values(0000000,'DefaultName','0W','DefaultBrand','DefaulAssociated','DefaultType','DefaultOwner',234411,110000,'DefaulRoom','DefaultDate')");
        //populated first data DEVICE_TYPE table
        db.execSQL("INSERT INTO " +TABLE_DEVICE_TYPE+ "(" +KEY_DEVICE_TYPE+ "," +COLUMN_DEVICE_DESCRIPTION+ "," +COLUMN_DEVICE_WARRANTY+ ")" +
                "values('DefaultType','descriptDevice','yyyy/MM/dd HH:mm:ss')");
        //populated first data CONTROL table
        db.execSQL("INSERT INTO " +TABLE_CONTROL+ "(" +COLUMN_CONTROL_ID_DATE+ ","  +KEY_DEVICE_SERIALNUMBER+ "," +COLUMN_CONTROL_DATE+ "," +COLUMN_CONTROL_TOTAL_TIME+ ")" +
                "values(0,0000000,'HOY',0)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVICE_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTROL);
        onCreate(db);
    }
    /**
     * Funcion addRoom que añade un cuarto
     * @param context contexto
     * @param name nombre del cuarto
     * @param email email del usuario
     * @param occupied ocupado del cuarto
     */
    public void addRoom(@Nullable Context context, String name, String email, boolean occupied){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_EMAIL,email);
        cv.put(KEY_ROOM_NAME,name);
        cv.put(COLUMN_ROOM_OCCUPIED,occupied);
        long result = db.insert(TABLE_ROOM,null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed adding room", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Room Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Funcion addDevice que añade un Device
     * @param context contexto
     * @param serialNumber numero serial de dispositivo
     * @param consume consumo de dispositivo
     * @param brand marca de dispositivo
     * @param type Nombretipo de dispositivo
     * @param description descripcion de dispositivo
     * @param room cuarto de dispositivo
     * @param owner dueño de dispositivo
     * @param date_created creacion de dispositivo
     */
    public void addDevice(@Nullable Context context, int serialNumber, String consume, String brand, String type, String description, String room, String owner, String date_created){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_DEVICE_SERIALNUMBER,serialNumber);
        cv.put(COLUMN_DEVICE_NAME,"name");
        cv.put(COLUMN_DEVICE_CONSUMPTION,consume);
        cv.put(COLUMN_DEVICE_BRAND,brand);
        cv.put(COLUMN_DEVICE_ASSOCIATED,"associated");
        cv.put(KEY_DEVICE_TYPE, type);
        cv.put(COLUMN_DEVICE_OWNER,owner);
        cv.put(COLUMN_DEVICE_DISTRIBUTOR,"distributor");
        cv.put(COLUMN_DEVICE_PRICE,description);
        cv.put(KEY_ROOM_NAME,room);
        cv.put(COLUMN_DEVICE_CREATION,date_created);

        long result = db.insert(TABLE_DEVICE,null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed adding devices", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Funcion addDeviceType que añade un DeviceType
     * @param context contexto
     * @param typeName Nombretipo de dispositivo
     * @param description descripcionTipo de dispositivo
     * @param warranty garantiaTipo de dispositivo
     */
    public void addDeviceType(@Nullable Context context, String typeName, String description, String warranty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_DEVICE_TYPE,typeName);
        cv.put(COLUMN_DEVICE_DESCRIPTION,description);
        cv.put(COLUMN_DEVICE_WARRANTY, String.valueOf(warranty));
        long result = db.insert(TABLE_DEVICE_TYPE,null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed adding deviceType", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added deviceType Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Funcion addControl que añade un dispositivo en la base de control de dispositivos
     * @param context contexto
     * @param serialNumber numero serial del dispositivo
     * @param date fecha de uso de dispositivo
     * @param time cantidad de tiempo usado
     */
    public void addControl(@Nullable Context context, int serialNumber, String date, int time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_DEVICE_SERIALNUMBER,serialNumber);
        cv.put(COLUMN_CONTROL_DATE,date);
        cv.put(COLUMN_CONTROL_TOTAL_TIME, String.valueOf(time));
        long result = db.insert(TABLE_CONTROL,null,cv);
        if(result == -1){
            Toast.makeText(context, "Adding Time Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Time Successfully", Toast.LENGTH_SHORT).show();
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
     * Funcion getRooms obtiene los cuartos para que el usuario pueda elegir
     * @return el conjunto de cuartos
     */
    public List<String> getRooms(){
        List<String> rooms = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_ROOM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                rooms.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return rooms;
    }
}
