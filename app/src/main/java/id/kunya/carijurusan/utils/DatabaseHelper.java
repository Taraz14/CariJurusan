package id.kunya.carijurusan.utils;

import android.annotation.SuppressLint;
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "tb_methode");
        onCreate(db);
    }

    public List<Question> getQuestionsOne(){
        List <Question> questionList = new ArrayList<Question>();

        SQLiteDatabase db;
        db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE ID BETWEEN 1 and 5" ;
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
                q.setWeight_criteria(cursor.getString(5));
                //add question in list
                questionList.add(q);

                //loop all rows
            }while (cursor.moveToNext());
        }
        return questionList;
    }

    //get all question in listview
    public List<Question> getQuestionsTwo(){
        List <Question> questionList = new ArrayList<Question>();

        SQLiteDatabase db;
        db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE ID ORDER BY RANDOM()";
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE ID BETWEEN 6 and 15" ;
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
                q.setWeight_criteria(cursor.getString(5));
                //add question in list
                questionList.add(q);

                //loop all rows
            }while (cursor.moveToNext());
        }
        return questionList;
    }

    public List<Question> getQuestionsThree(){
        List <Question> questionList = new ArrayList<Question>();

        SQLiteDatabase db;
        db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE ID ORDER BY RANDOM()";
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE ID BETWEEN 16 and 50" ;
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
                q.setWeight_criteria(cursor.getString(5));
                //add question in list
                questionList.add(q);

                //loop all rows
            }while (cursor.moveToNext());
        }
        return questionList;
    }

}