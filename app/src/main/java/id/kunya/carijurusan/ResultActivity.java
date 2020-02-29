package id.kunya.carijurusan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import androidx.appcompat.app.AppCompatActivity;

import id.kunya.carijurusan.utils.DatabaseHelper;

/**
 * Created by muhammad on 09/01/2020.
 */
public class ResultActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private float v1;
    JSONArray jsonArray = null;
    String firstOption, secondOption, thirdOption,bobot,resultbobot;
    String TAG = "CariJurusan";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        myDb = new DatabaseHelper(this);
        myDb.openDataBase();

        Bundle extras = ResultActivity.this.getIntent().getExtras();
        bobot = extras.getString("bobot");
        resultbobot = extras.getString("v1");

        try {
            jsonArray = new JSONArray(bobot);
            String[] strArr = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                strArr[i] = jsonArray.getString(i);
                v1 += Float.parseFloat(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

        TextView result=findViewById(R.id.result);
        TextView final_result=findViewById(R.id.final_result);
        TextView final_result_backup=findViewById(R.id.final_result_backup);
        TextView final_result_backup_two=findViewById(R.id.final_result_backup_two);

        result.setText(bobot + "  =  " + String.valueOf(v1));
        pilihanJurusan(v1);

//        final_result.setText("Selamat,, \n\n" + " Jurusan yang tepat berdasarkan kemampuanmu menjawab pertanyaan ini adalah " +
//                firstOption + "\n\n\n Alternatif jurusan yang lain adalah : \n" + "1. "+secondOption+"\n" +
//                "2. "+thirdOption );

        final_result.setText("Selamat,, \n\n" + " Jurusan yang tepat berdasarkan kemampuanmu menjawab pertanyaan ini adalah " +
                "\""+firstOption + "\"");

        final_result_backup.setText("1. " + secondOption);
        final_result_backup_two.setText("2. " +thirdOption);



        final_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ResultActivity.this, firstOption, Toast.LENGTH_SHORT).show();
                switch (firstOption) {
                    case "EKONOMI":
                        Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent.putExtra("fakultas", "Fakultas Ekonomi");
                        intent.putExtra("from", "result");
                        startActivity(intent);
                        break;
                    case "TEKNIK":
                        Intent intent1 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent1.putExtra("fakultas", "Fakultas Teknik");
                        intent1.putExtra("from", "result");
                        startActivity(intent1);
                        break;
                    case "PERTANIAN":
                        Intent intent2 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent2.putExtra("fakultas", "Fakultas Pertanian");
                        intent2.putExtra("from", "result");
                        startActivity(intent2);
                        break;
                    case "FISIP":
                        Intent intent3 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent3.putExtra("fakultas", "Fakultas Ilmu Sosial dan Ilmu Politik");
                        intent3.putExtra("from", "result");
                        startActivity(intent3);
                        break;
                    case "FKIP MATEMATIKA":
                    case "FKIP BAHASA INGGRIS":
                        Intent intent4 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent4.putExtra("fakultas", "Fakultas Keguruan dan Ilmu Pendidikan");
                        intent4.putExtra("from", "result");
                        startActivity(intent4);
                        break;
                    case "PERIKANAN":
                        Intent intent5 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent5.putExtra("fakultas", "Fakultas Perikanan");
                        intent5.putExtra("from", "result");
                        startActivity(intent5);
                        break;
                    case "HUKUM":
                        Intent intent6 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent6.putExtra("fakultas", "Fakultas Hukum");
                        intent6.putExtra("from", "result");
                        startActivity(intent6);
                        break;
                    case "-":
                    case "Mohon Maaf Ada Kesalahan":
                        Log.i(TAG,"Salah");
                        break;
                    default:
                        Intent intent7 = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent7);
                        break;
                }
            }
        });

        final_result_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (secondOption) {
                    case "EKONOMI":
                        Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent.putExtra("fakultas", "Fakultas Ekonomi");
                        intent.putExtra("from", "result");
                        startActivity(intent);
                        break;
                    case "TEKNIK":
                        Intent intent1 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent1.putExtra("fakultas", "Fakultas Teknik");
                        intent1.putExtra("from", "result");
                        startActivity(intent1);
                        break;
                    case "PERTANIAN":
                        Intent intent2 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent2.putExtra("fakultas", "Fakultas Pertanian");
                        intent2.putExtra("from", "result");
                        startActivity(intent2);
                        break;
                    case "FISIP":
                        Intent intent3 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent3.putExtra("fakultas", "Fakultas Ilmu Sosial dan Ilmu Politik");
                        intent3.putExtra("from", "result");
                        startActivity(intent3);
                        break;
                    case "FKIP MATEMATIKA":
                    case "FKIP BAHASA INGGRIS":
                        Intent intent4 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent4.putExtra("fakultas", "Fakultas Keguruan dan Ilmu Pendidikan");
                        intent4.putExtra("from", "result");
                        startActivity(intent4);
                        break;
                    case "PERIKANAN":
                        Intent intent5 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent5.putExtra("fakultas", "Fakultas Perikanan");
                        intent5.putExtra("from", "result");
                        startActivity(intent5);
                        break;
                    case "HUKUM":
                        Intent intent6 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent6.putExtra("fakultas", "Fakultas Hukum");
                        intent6.putExtra("from", "result");
                        startActivity(intent6);
                        break;
                    case "-":
                    case "Mohon Maaf Ada Kesalahan":
                        Log.i(TAG,"Salah");
                        break;
                    default:
                        Intent intent7 = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent7);
                        break;
                }
            }
        });

        final_result_backup_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (thirdOption) {
                    case "EKONOMI":
                        Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent.putExtra("fakultas", "Fakultas Ekonomi");
                        intent.putExtra("from", "result");
                        startActivity(intent);
                        break;
                    case "TEKNIK":
                        Intent intent1 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent1.putExtra("fakultas", "Fakultas Teknik");
                        intent1.putExtra("from", "result");
                        startActivity(intent1);
                        break;
                    case "PERTANIAN":
                        Intent intent2 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent2.putExtra("fakultas", "Fakultas Pertanian");
                        intent2.putExtra("from", "result");
                        startActivity(intent2);
                        break;
                    case "FISIP":
                        Intent intent3 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent3.putExtra("fakultas", "Fakultas Ilmu Sosial dan Ilmu Politik");
                        intent3.putExtra("from", "result");
                        startActivity(intent3);
                        break;
                    case "FKIP MATEMATIKA":
                    case "FKIP BAHASA INGGRIS":
                        Intent intent4 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent4.putExtra("fakultas", "Fakultas Keguruan dan Ilmu Pendidikan");
                        intent4.putExtra("from", "result");
                        startActivity(intent4);
                        break;
                    case "PERIKANAN":
                        Intent intent5 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent5.putExtra("fakultas", "Fakultas Perikanan");
                        intent5.putExtra("from", "result");
                        startActivity(intent5);
                        break;
                    case "HUKUM":
                        Intent intent6 = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                        intent6.putExtra("fakultas", "Fakultas Hukum");
                        intent6.putExtra("from", "result");
                        startActivity(intent6);
                        break;
                    case "-":
                    case "Mohon Maaf Ada Kesalahan":
                        Log.i(TAG,"Salah");
                        break;
                    default:
                        Intent intent7 = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent7);
                        break;
                }
            }
        });

    }

    public void pilihanJurusan(float bobot_nilai_user){
        if (bobot_nilai_user >= 0.81){
            firstOption = "TEKNIK";
            secondOption = "FKIP MATEMATIKA";
            thirdOption = "PERTANIAN";
        }
        else if (bobot_nilai_user >= 0.79){
            firstOption = "FKIP MATEMATIKA";
            secondOption = "PERTANIAN";
            thirdOption = "PERIKANAN";
        }
        else if (bobot_nilai_user >= 0.77){
            firstOption = "PERTANIAN";
            secondOption = "PERIKANAN";
            thirdOption = "FISIP";
        }
        else if (bobot_nilai_user >= 0.49){
            firstOption = "PERIKANAN";
            secondOption = "FISIP";
            thirdOption = "HUKUM";
        }
        else if (bobot_nilai_user >= 0.47){
            firstOption = "FISIP";
            secondOption = "HUKUM";
            thirdOption = "FKIP BAHASA INGGRIS";
        }
        else if (bobot_nilai_user >= 0.44){
            firstOption = "HUKUM";
            secondOption = "FKIP BAHASA INGGRIS";
            thirdOption =  "EKONOMI";
        }
        else if (bobot_nilai_user >= 0.39){
            firstOption = "FKIP BAHASA INGGRIS";
            secondOption = "EKONOMI";
            thirdOption = "-";
        }
        else if (bobot_nilai_user >= 0){
            firstOption = "EKONOMI";
            secondOption = "-";
            thirdOption = "-";
        }
        else {
            firstOption = "Mohon Maaf Ada Kesalahan";
            secondOption = "Mohon Maaf Ada Kesalahan";
            thirdOption = "Mohon Maaf Ada Kesalahan";
        }

    }

    private void clearAppData() {
        finish();
        startActivity(new Intent(ResultActivity.this, IDQuestionsActivity.class));
    }
}
