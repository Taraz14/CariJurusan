package id.kunya.carijurusan;

import android.annotation.SuppressLint;
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
    private String arrDataMethod[][], arrDataBobotAlt[][], arrDataBobot[][];
    private float v1;
    String firstOption, secondOption, thirdOption;


    @SuppressLint("SetTextI18n")
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
                clearAppData();
                finish();
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }
        });

        arrDataMethod = myDb.getFromMethodData();
        arrDataBobotAlt = myDb.getFromBobotAltData();


        for (int b=0;b<32;b++){

            arrDataBobot = myDb.getFromMethodDataByID(arrDataBobotAlt[b][2]);

            v1 += Float.parseFloat(arrDataBobot[0][6]) * Float.parseFloat(arrDataBobotAlt[b][1]);
            Log.i("Cari Jurusan", String.valueOf(arrDataBobotAlt[b][2]) +  " "+ String.valueOf(arrDataBobot[0][6]) +  " * " + String.valueOf(arrDataBobotAlt[b][1]) + " = " + String.valueOf(Float.parseFloat(arrDataMethod[b][6]) * Float.parseFloat(arrDataBobotAlt[b][1])));
//            Log.i("CariJurusan", String.valueOf(b) +  " " + v1);
        }

        TextView result=findViewById(R.id.result);
        TextView final_result=findViewById(R.id.final_result);

        result.setText(String.valueOf(v1));

        pilihanJurusan(v1);

        final_result.setText("Selamat,, \n\n" + " Jurusan yang tepat berdasarkan kemampuanmu menjawab pertanyaan ini adalah " +
                firstOption + "\n\n\n Alternatif jurusan yang lain adalah : \n" + "1. "+secondOption+"\n" +
                "2. "+thirdOption );
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

    private void clearAppData() {
        myDb.truncateMyData();
        try {
            myDb.CopyDataBaseFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(ResultActivity.this, IDQuestionsActivity.class));
    }
}
