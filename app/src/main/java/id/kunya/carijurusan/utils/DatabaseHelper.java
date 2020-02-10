package id.kunya.carijurusan.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad on 03/01/2020.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "database.db";
    public static final String TABLE_NAME="tb_methode";
    public static final String TABLE_NAME_1="tb_alternatif";
    public static final String TABLE_NAME_2="tb_bobot_alternatif";
    public static final String COL_1="ID";
    public static final String COL_2="SOAL";
    public static final String COL_3="BOBOT_PERSEN";
    public static final String COL_4="JAWABAN";
    public static final String COL_5="BOBOT_JAWABAN";
    public static final String COL_6="KETERANGAN";
    public static final String COL_7="BOBOT_KRITERIA";
    public static final String COL_8="BOBOT_ALTERNATIF";

    public static final String COL2_1="ID";
    public static final String COL2_2="A1";
    public static final String COL2_10="SOAL_ID";
    public static final String COL2_11="ANSWER";

    public static final String COL3_1="ID";
    public static final String COL3_2="PA1";
    public static final String COL3_10="SOAL_ID";
    static Context ctx;
    private int rowCount = 0;

    String TAG = "CariJurusan";

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

        db.execSQL("CREATE TABLE " + "tb_methode" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, SOAL TEXT, BOBOT_PERSEN NUMERIC, JAWABAN TEXT, BOBOT_JAWABAN NUMERIC, KETERANGAN TEXT, BOBOT_KRITERIA TEXT, BOBOT_ALTERNATIF TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, A1 TEXT, SOAL_ID NUMERIC, ANSWER TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, PA1 TEXT, SOAL_ID NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "tb_methode");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(db);
    }

    public boolean insertAlternatif(String a1,String soal_id, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_2, a1);
        contentValues.put(COL2_10, soal_id);
        contentValues.put(COL2_11, answer);
        long result = db.insert(TABLE_NAME_1,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }

    public boolean insertBobotAlternatif(String a1,
                                    String soal_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PA1", a1);
        contentValues.put("SOAL_ID", soal_id);
        long result = db.insert(TABLE_NAME_2,null,contentValues);
        Log.i(TAG,contentValues.toString());
        if (result== -1)
            return false;
        else
            return true;
    }

    public Cursor updateDataAlternatif(String a1, String soal_id, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ons=db.rawQuery("UPDATE " +TABLE_NAME_1+" SET A1='"+a1+"', ANSWER='"+answer+"' WHERE SOAL_ID='"+soal_id+"'", null);
//        Log.i(TAG,"UPDATE " +TABLE_NAME_1+" SET A1='"+a1+"', ANSWER='"+answer+"' WHERE SOAL_ID='"+soal_id+"'");
        return ons;
    }

    public void truncateMyData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        db.execSQL("CREATE TABLE " + TABLE_NAME_1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, A1 TEXT, SOAL_ID NUMERIC, ANSWER TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, PA1 TEXT, SOAL_ID NUMERIC)");

    }

    public Cursor updateDataBobotAlternatif(String a1, String soal_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ons=db.rawQuery("UPDATE " +TABLE_NAME_2+" SET PA1='"+a1+"' WHERE SOAL_ID='"+soal_id+"'", null);
        Log.i(TAG,"UPDATE " +TABLE_NAME_2+" SET PA1='"+a1+"' WHERE SOAL_ID='"+soal_id+"'");
        return ons;
    }

    public Cursor showDataAlternatifByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ons = db.rawQuery("select * from " + TABLE_NAME_1+" where SOAL_ID="+id, null);
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

    //get all question in listview
    public List<Question> getAllQuestions(){
        List <Question> questionList = new ArrayList<Question>();

        SQLiteDatabase db;
        db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE ID ORDER BY RANDOM()";
        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;
        Cursor cursor = db.rawQuery(selectQuery,null);
        rowCount = cursor.getCount();

        if(cursor.moveToFirst()){
            do{
                Question q = new Question();
                q.setId(cursor.getString(0));
                q.setQuestion(cursor.getString(1));
                q.setWeight_percent(cursor.getString(2));
                q.setAnswer(cursor.getString(3));
                q.setWeight_answer(cursor.getString(4));
                q.setDescription(cursor.getString(5));
                q.setWeight_criteria(cursor.getString(6));
                q.setWeight_alternative(cursor.getString(7));
//                q.setPrev_questions("NULL");
//                q.setNext_questions("NULL");
                //add question in list
                questionList.add(q);

                //loop all rows
            }while (cursor.moveToNext());
        }
        return questionList;
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

    public String[][] getFromBobotAltData() {
        try {
            String arrData[][] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase();
            String strSQL = "SELECT  * FROM " + "tb_bobot_alternatif";
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

}