package id.kunya.carijurusan.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by muhammad on 03/01/2020.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "database.db";
    public static final String TABLE_NAME="tb_methode";
    public static final String COL_1="ID";
    public static final String COL_2="SOAL";
    public static final String COL_3="BOBOT_PERSEN";
    public static final String COL_4="JAWABAN";
    public static final String COL_5="BOBOT_JAWABAN";
    public static final String COL_6="KETERANGAN";
    public static final String COL_7="BOBOT_KRITERIA";
    public static final String COL_8="BOBOT_ALTERNATIF";

    public static final String TABLE_NAME_1="tb_alternatif";
    public static final String COL2_1="ID";
    public static final String COL2_2="A1";
    public static final String COL2_3="A2";
    public static final String COL2_4="A3";
    public static final String COL2_5="A4";
    public static final String COL2_6="A5";
    public static final String COL2_7="A6";
    public static final String COL2_8="A7";
    public static final String COL2_9="A8";
    public static final String COL2_10="SOAL_ID";
    public static final String COL2_11="ANSWER";

    public static final String TABLE_NAME_2="tb_bobot_alternatif";
    public static final String COL3_1="ID";
    public static final String COL3_2="PA1";
    public static final String COL3_3="PA2";
    public static final String COL3_4="PA3";
    public static final String COL3_5="PA4";
    public static final String COL3_6="PA5";
    public static final String COL3_7="PA6";
    public static final String COL3_8="PA7";
    public static final String COL3_9="PA8";
    public static final String COL3_10="SOAL_ID";
    static Context ctx;

    private static final String DB_PATH_SUFFIX = "/databases/";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        ctx = context;

    }

    public void CopyDataBaseFromAsset() throws IOException {

        InputStream myInput = ctx.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = getDatabasePath();

        // if the path doesn't exist first, create it
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    private static String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, SOAL TEXT, BOBOT_PERSEN NUMERIC, JAWABAN TEXT, BOBOT_JAWABAN NUMERIC, KETERANGAN TEXT, BOBOT_KRITERIA TEXT, BOBOT_ALTERNATIF TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, A1 TEXT, A2 TEXT, A3 TEXT, A4 TEXT, A5 TEXT, A6 TEXT, A7 TEXT, A8 TEXT, SOAL_ID NUMERIC, ANSWER TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, PA1 TEXT, PA2 TEXT, PA3 TEXT, PA4 TEXT, PA5 TEXT, PA6 TEXT, PA7 TEXT, PA8 TEXT, SOAL_ID NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(db);
    }
    public boolean insertData(String name, String type, String weight, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,type);
        contentValues.put(COL_4,weight);
        contentValues.put(COL_5,notes);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }

//    public Cursor insertAlternatif(String a1, String a2, String a3){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor data=db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");
//        return data;
//    }
    public boolean insertAlternatif(String a1, String a2, String a3, String a4,
                                    String a5, String a6, String a7, String a8,
                                    String soal_id, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_2, a1);
        contentValues.put(COL2_3, a2);
        contentValues.put(COL2_4, a3);
        contentValues.put(COL2_5, a4);
        contentValues.put(COL2_6, a5);
        contentValues.put(COL2_7, a6);
        contentValues.put(COL2_8, a7);
        contentValues.put(COL2_9, a8);
        contentValues.put(COL2_10, soal_id);
        contentValues.put(COL2_11, answer);
        long result = db.insert(TABLE_NAME_1,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }

    public boolean insertBobotAlternatif(String a1, String a2, String a3, String a4,
                                    String a5, String a6, String a7, String a8,
                                    String soal_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3_2, a1);
        contentValues.put(COL3_3, a2);
        contentValues.put(COL3_4, a3);
        contentValues.put(COL3_5, a4);
        contentValues.put(COL3_6, a5);
        contentValues.put(COL3_7, a6);
        contentValues.put(COL3_8, a7);
        contentValues.put(COL3_9, a8);
        contentValues.put(COL3_10, soal_id);
        long result = db.insert(TABLE_NAME_2,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }

    public Cursor updateDataAlternatif(String a1, String a2, String a3, String a4,
                                       String a5, String a6, String a7, String a8, String soal_id, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ons=db.rawQuery("UPDATE " +TABLE_NAME_1+" SET A1='"+a1+"', A2='"+a2+"', A3='"+a3+"', A4='"+a4+"', A5='"+a5+"', A6='"+a6+"', A7='"+a7+"', A8='"+a8+"', ANSWER='"+answer+"' WHERE SOAL_ID='"+soal_id+"'", null);
        return ons;
    }

    public void truncateMyData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        db.execSQL("CREATE TABLE " + TABLE_NAME_1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, A1 TEXT, A2 TEXT, A3 TEXT, A4 TEXT, A5 TEXT, A6 TEXT, A7 TEXT, A8 TEXT, SOAL_ID NUMERIC, ANSWER TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, PA1 TEXT, PA2 TEXT, PA3 TEXT, PA4 TEXT, PA5 TEXT, PA6 TEXT, PA7 TEXT, PA8 TEXT, SOAL_ID NUMERIC)");

    }

    public Cursor updateDataBobotAlternatif(String a1, String a2, String a3, String a4,
                                       String a5, String a6, String a7, String a8, String soal_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ons=db.rawQuery("UPDATE " +TABLE_NAME_2+" SET PA1='"+a1+"', PA2='"+a2+"', PA3='"+a3+"', PA4='"+a4+"', PA5='"+a5+"', PA6='"+a6+"', PA7='"+a7+"', PA8='"+a8+"' WHERE SOAL_ID='"+soal_id+"'", null);
        return ons;
    }

    public Cursor showDataAlternatifByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ons=db.rawQuery("select * from " + TABLE_NAME_1+" where SOAL_ID="+id, null);
        return ons;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public Cursor removeDataById(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("delete from "+TABLE_NAME+" where ID="+id,null);
        return res;
    }

    public Cursor removeDataSettingById(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("delete from "+TABLE_NAME_1+" where ID="+id,null);
        return res;
    }

    public String[][] getFromMethodData() {
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase();
            String strSQL = "SELECT  * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        arrData[i][5] = cursor.getString(5);
                        arrData[i][6] = cursor.getString(6);
                        arrData[i][7] = cursor.getString(7);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }
    public String[][] getFromMethodDataByID(String id) {
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase();
            String strSQL = "SELECT  * FROM " + TABLE_NAME+" where ID='"+id+"'";
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        arrData[i][5] = cursor.getString(5);
                        arrData[i][6] = cursor.getString(6);
                        arrData[i][7] = cursor.getString(7);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }

    public Cursor SelectAllData(String type) {
        try {
            SQLiteDatabase db;
            db = this.getReadableDatabase();

            String strSQL = "SELECT  ID As _id , * FROM " + TABLE_NAME+" where TYPE='"+type+"'";
            Cursor cursor = db.rawQuery(strSQL, null);

            return cursor;

        } catch (Exception e) {
            return null;
        }

    }
 
    public String[][] getFromAltData() {
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase();
            String strSQL = "SELECT  * FROM " + TABLE_NAME_1;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        arrData[i][5] = cursor.getString(5);
                        arrData[i][6] = cursor.getString(6);
                        arrData[i][7] = cursor.getString(7);
                        arrData[i][8] = cursor.getString(8);
                        arrData[i][9] = cursor.getString(9);
                        arrData[i][10] = cursor.getString(10);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }
    public String[][] getFromBobotAltData() {
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase();
            String strSQL = "SELECT  * FROM " + TABLE_NAME_2;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i= 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        arrData[i][4] = cursor.getString(4);
                        arrData[i][5] = cursor.getString(5);
                        arrData[i][6] = cursor.getString(6);
                        arrData[i][7] = cursor.getString(7);
                        arrData[i][8] = cursor.getString(8);
                        arrData[i][9] = cursor.getString(9);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }

    public Cursor SelectSettingData() {
        try {
            SQLiteDatabase db;
            db = this.getReadableDatabase();

            String strSQL = "SELECT  ID As _id , * FROM " + TABLE_NAME_1;
            Cursor cursor = db.rawQuery(strSQL, null);

            return cursor;

        } catch (Exception e) {
            return null;
        }

    }

    public boolean updateData(String id, String name, String type, String weight, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,type);
        contentValues.put(COL_4,weight);
        contentValues.put(COL_5,notes);
        db.update(TABLE_NAME, contentValues, "id=?",new String[]{id});
        return true;

    }

    public boolean updateSetting(String id, String disease, String sympt, String weight, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_1,id);
        contentValues.put(COL2_2,disease);
        contentValues.put(COL2_3,sympt);
        contentValues.put(COL2_4,weight);
        contentValues.put(COL2_5,notes);
        db.update(TABLE_NAME_1, contentValues, "id=?",new String[]{id});
        return true;

    }
}