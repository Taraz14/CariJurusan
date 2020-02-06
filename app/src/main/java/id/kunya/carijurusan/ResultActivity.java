package id.kunya.carijurusan;

import android.app.ActivityManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import id.kunya.carijurusan.utils.DatabaseHelper;

/**
 * Created by muhammad on 09/01/2020.
 */
public class ResultActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private String arrDataMethod[][], arrDataBobotAlt[][];
    private float v1, v2, v3, v4, v5, v6, v7, v8;
    String firstOption, secondOption, thirdOption, option4th, option5th, option6th, option7th, option8th;
    String firstInformation, secongInformation, thirdInformation, info4th, info5th, info6th, info7th, info8th;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        myDb.openDataBase();
        /*
        * BOBOT KRITERIA C1 * BOBOT ALT C1 + BOBOT KRITERIA C2 * BOBOT ALT C2
         */
        ImageView clearData = findViewById(R.id.clearData);
        ImageView home = findViewById(R.id.home);

        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAppData();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }
        });

        arrDataMethod = myDb.getFromMethodData();
        arrDataBobotAlt = myDb.getFromBobotAltData();


        for (int b=0;b<arrDataBobotAlt.length;b++){
            v1 += Float.parseFloat(arrDataMethod[b][6]) * Float.parseFloat(arrDataBobotAlt[b][1]);
            Log.i("Cari Jurusan", String.valueOf(0) + "  " + String.valueOf(Float.parseFloat(arrDataMethod[b][6]) * Float.parseFloat(arrDataBobotAlt[b][1])));
            v2 += Float.parseFloat(arrDataMethod[b][6]) * Float.parseFloat(arrDataBobotAlt[b][2]);
            v3 += Float.parseFloat(arrDataMethod[b][6]) * Float.parseFloat(arrDataBobotAlt[b][3]);
            v4 += Float.parseFloat(arrDataMethod[b][6]) * Float.parseFloat(arrDataBobotAlt[b][4]);
            v5 += Float.parseFloat(arrDataMethod[b][6]) * Float.parseFloat(arrDataBobotAlt[b][5]);
            v6 += Float.parseFloat(arrDataMethod[b][6]) * Float.parseFloat(arrDataBobotAlt[b][6]);
            v7 += Float.parseFloat(arrDataMethod[b][6]) * Float.parseFloat(arrDataBobotAlt[b][7]);
            v8 += Float.parseFloat(arrDataMethod[b][6]) * Float.parseFloat(arrDataBobotAlt[b][8]);
        }

        TextView result=findViewById(R.id.result);
        TextView final_result=findViewById(R.id.final_result);

        float totalListValue[] ={v1, v2, v3, v4, v5, v6, v7, v8};

        Arrays.sort(totalListValue);

        result.setText(String.valueOf(v1));

        HashMap<Float, String> datas=new HashMap<>();
        datas.put(v1, "Teknik");
        datas.put(v2, "Pertanian");
        datas.put(v3, "Perikanan");
        datas.put(v4, "Fkip Matematika");
        datas.put(v5, "Fkip bhs. Inggris");
        datas.put(v6, "Fisip");
        datas.put(v7, "Hukum");
        datas.put(v8, "Ekonomi");

//        setFilterAsValueFirst(totalListValue[7], datas);
//        setFilterAsValueSecond(totalListValue[6], datas);
//        setFilterAsValueThird(totalListValue[5], datas);
//        setFilterAsValue4th(totalListValue[4], datas);
//        setFilterAsValue5th(totalListValue[3], datas);
//        setFilterAsValue6th(totalListValue[2], datas);
//        setFilterAsValue7th(totalListValue[1], datas);
//        setFilterAsValue8th(totalListValue[0], datas);

        pilihanJurusan(v1);

        final_result.setText("Selamat,, \n\n" + " Jurusan yang tepat berdasarkan kemampuanmu menjawab pertanyaan ini adalah " +
                firstOption + "\n\n\n Alternatif jurusan yang lain adalah : \n" + "1. "+secondOption+"\n" +
                "2. "+thirdOption );



//        final_result.setText("Berdasarkan perhitungan, Alternatif terbaik adalah : \n\n" +
//                "1. "+firstOption+"\n" +
//                "2. "+secondOption+"\n" +
//                "3. "+thirdOption+"\n" +
//                "4. "+option4th+"\n" +
//                "5. "+option5th+"\n" +
//                "6. "+option6th+"\n" +
//                "7. "+option7th+"\n" +
//                "8. "+option8th);
    }

    public void pilihanJurusan(float bobot_nilai_user){
        if (bobot_nilai_user >= 0.81){
            firstOption = "\"TEKNIK\"";
            secondOption = "\"FKIP MATEMATIKA\"";
            thirdOption = "\"PERTANIAN\"";
        }
        else if (bobot_nilai_user >= 0.79){
            firstOption = "\"FKIP MATEMATIKA\"";
            secondOption = "\"PERTANIAN\"";
            thirdOption = "\"PERIKANAN\"";
        }
        else if (bobot_nilai_user >= 0.77){
            firstOption = "\"PERTANIAN\"";
            secondOption = "\"PERIKANAN\"";
            thirdOption = "\"BAHASA INGGRIS\"";
        }
        else if (bobot_nilai_user >= 0.51){
            firstOption = "\"PERIKANAN\"";
            secondOption = "\"BAHASA INGGRIS\"";
            thirdOption = "\"FISIP\"";
        }
        else if (bobot_nilai_user >= 0.47){
            firstOption = "\"BAHASA INGGRIS\"";
            secondOption = "\"FISIP\"";
            thirdOption = "\"EKONOMI\"";
        }
        else if (bobot_nilai_user >= 0.45){
            firstOption = "\"FISIP\"";
            secondOption = "\"EKONOMI\"";
            thirdOption =  "\"HUKUM\"";
        }
        else if (bobot_nilai_user >= 0.42){
            firstOption = "\"EKONOMI\"";
            secondOption = "\"HUKUM\"";
            thirdOption = "-";
        }
        else if (bobot_nilai_user >= 0){
            firstOption = "\"HUKUM\"";
            secondOption = "-";
            thirdOption = "-";
        }
        else {
            firstOption = "Mohon Maaf";
        }

    }

    public void setFilterAsValueFirst(float comparison, HashMap<Float, String> datas){
        if (comparison == v1) {
            firstOption="" + datas.get(v1);
            firstInformation="" + comparison;
        }
        if (comparison == v2) {
            firstOption="" + datas.get(v2);
            firstInformation="" + comparison;
        }
        if (comparison == v3) {
            firstOption="" + datas.get(v3);
            firstInformation="" + comparison;
        }
        if (comparison == v4) {
            firstOption="" + datas.get(v4);
            firstInformation="" + comparison;
        }
        if (comparison == v5) {
            firstOption="" + datas.get(v5);
            firstInformation="" + comparison;
        }
        if (comparison == v6) {
            firstOption="" + datas.get(v6);
            firstInformation="" + comparison;
        }
        if (comparison == v7) {
            firstOption="" + datas.get(v7);
            firstInformation="" + comparison;
        }
        if (comparison == v8) {
            firstOption="" + datas.get(v8);
            firstInformation="" + comparison;
        }
    }

    public void setFilterAsValueSecond(float comparison, HashMap<Float, String> datas){
        if (comparison == v1) {
            secondOption="" + datas.get(v1);
            secongInformation="" + comparison;
        }
        if (comparison == v2) {
            secondOption="" + datas.get(v2);
            secongInformation="" + comparison;
        }
        if (comparison == v3) {
            secondOption="" + datas.get(v3);
            secongInformation="" + comparison;
        }
        if (comparison == v4) {
            secondOption="" + datas.get(v4);
            secongInformation="" + comparison;
        }
        if (comparison == v5) {
            secondOption="" + datas.get(v5);
            secongInformation="" + comparison;
        }
        if (comparison == v6) {
            secondOption="" + datas.get(v6);
            secongInformation="" + comparison;
        }
        if (comparison == v7) {
            secondOption="" + datas.get(v7);
            secongInformation="" + comparison;
        }
        if (comparison == v8) {
            secondOption="" + datas.get(v8);
            secongInformation="" + comparison;
        }
    }
    public void setFilterAsValueThird(float comparison, HashMap<Float, String> datas){
        if (comparison == v1) {
            thirdOption="" + datas.get(v1);
            thirdInformation="" + comparison;
        }
        if (comparison == v2) {
            thirdOption="" + datas.get(v2);
            thirdInformation="" + comparison;
        }
        if (comparison == v3) {
            thirdOption="" + datas.get(v3);
            thirdInformation="" + comparison;
        }
        if (comparison == v4) {
            thirdOption="" + datas.get(v4);
            thirdInformation="" + comparison;
        }
        if (comparison == v5) {
            thirdOption="" + datas.get(v5);
            thirdInformation="" + comparison;
        }
        if (comparison == v6) {
            thirdOption="" + datas.get(v6);
            thirdInformation="" + comparison;
        }
        if (comparison == v7) {
            thirdOption="" + datas.get(v7);
            thirdInformation="" + comparison;
        }
        if (comparison == v8) {
            thirdOption="" + datas.get(v8);
            thirdInformation="" + comparison;
        }
    }

    public void setFilterAsValue4th(float comparison, HashMap<Float, String> datas){
        if (comparison == v1) {
            option4th="" + datas.get(v1);
            info4th="" + comparison;
        }
        if (comparison == v2) {
            option4th="" + datas.get(v2);
            info4th="" + comparison;
        }
        if (comparison == v3) {
            option4th="" + datas.get(v3);
            info4th="" + comparison;
        }
        if (comparison == v4) {
            option4th="" + datas.get(v4);
            info4th="" + comparison;
        }
        if (comparison == v5) {
            option4th="" + datas.get(v5);
            info4th="" + comparison;
        }
        if (comparison == v6) {
            option4th="" + datas.get(v6);
            info4th="" + comparison;
        }
        if (comparison == v7) {
            option4th="" + datas.get(v7);
            info4th="" + comparison;
        }
        if (comparison == v8) {
            option4th="" + datas.get(v8);
            info4th="" + comparison;
        }
    }

    public void setFilterAsValue5th(float comparison, HashMap<Float, String> datas){
        if (comparison == v1) {
            option5th="" + datas.get(v1);
            info5th="" + comparison;
        }
        if (comparison == v2) {
            option5th="" + datas.get(v2);
            info5th="" + comparison;
        }
        if (comparison == v3) {
            option5th="" + datas.get(v3);
            info5th="" + comparison;
        }
        if (comparison == v4) {
            option5th="" + datas.get(v4);
            info5th="" + comparison;
        }
        if (comparison == v5) {
            option5th="" + datas.get(v5);
            info5th="" + comparison;
        }
        if (comparison == v6) {
            option5th="" + datas.get(v6);
            info5th="" + comparison;
        }
        if (comparison == v7) {
            option5th="" + datas.get(v7);
            info5th="" + comparison;
        }
        if (comparison == v8) {
            option5th="" + datas.get(v8);
            info5th="" + comparison;
        }
    }

    public void setFilterAsValue6th(float comparison, HashMap<Float, String> datas){
        if (comparison == v1) {
            option6th="" + datas.get(v1);
            info6th="" + comparison;
        }
        if (comparison == v2) {
            option6th="" + datas.get(v2);
            info6th="" + comparison;
        }
        if (comparison == v3) {
            option6th="" + datas.get(v3);
            info6th="" + comparison;
        }
        if (comparison == v4) {
            option6th="" + datas.get(v4);
            info6th="" + comparison;
        }
        if (comparison == v5) {
            option6th="" + datas.get(v5);
            info6th="" + comparison;
        }
        if (comparison == v6) {
            option6th="" + datas.get(v6);
            info6th="" + comparison;
        }
        if (comparison == v7) {
            option6th="" + datas.get(v7);
            info6th="" + comparison;
        }
        if (comparison == v8) {
            option6th="" + datas.get(v8);
            info6th="" + comparison;
        }
    }
    public void setFilterAsValue7th(float comparison, HashMap<Float, String> datas){
        if (comparison == v1) {
            option7th="" + datas.get(v1);
            info7th="" + comparison;
        }
        if (comparison == v2) {
            option7th="" + datas.get(v2);
            info7th="" + comparison;
        }
        if (comparison == v3) {
            option7th="" + datas.get(v3);
            info7th="" + comparison;
        }
        if (comparison == v4) {
            option7th="" + datas.get(v4);
            info7th="" + comparison;
        }
        if (comparison == v5) {
            option7th="" + datas.get(v5);
            info7th="" + comparison;
        }
        if (comparison == v6) {
            option7th="" + datas.get(v6);
            info7th="" + comparison;
        }
        if (comparison == v7) {
            option7th="" + datas.get(v7);
            info7th="" + comparison;
        }
        if (comparison == v8) {
            option7th="" + datas.get(v8);
            info7th="" + comparison;
        }
    }
    public void setFilterAsValue8th(float comparison, HashMap<Float, String> datas){
        if (comparison == v1) {
            option8th="" + datas.get(v1);
            info8th="" + comparison;
        }
        if (comparison == v2) {
            option8th="" + datas.get(v2);
            info8th="" + comparison;
        }
        if (comparison == v3) {
            option8th="" + datas.get(v3);
            info8th="" + comparison;
        }
        if (comparison == v4) {
            option8th="" + datas.get(v4);
            info8th="" + comparison;
        }
        if (comparison == v5) {
            option8th="" + datas.get(v5);
            info8th="" + comparison;
        }
        if (comparison == v6) {
            option8th="" + datas.get(v6);
            info8th="" + comparison;
        }
        if (comparison == v7) {
            option8th="" + datas.get(v7);
            info8th="" + comparison;
        }
        if (comparison == v8) {
            option8th="" + datas.get(v8);
            info8th="" + comparison;
        }
    }

    private void clearAppData() {
        myDb.truncateMyData();
        try {
            myDb.CopyDataBaseFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(ResultActivity.this, IDQuestionsActivity.class));
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
