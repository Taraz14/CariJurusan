package id.kunya.carijurusan;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import id.kunya.carijurusan.utils.DatabaseHelper;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by muhammad on 05/01/2020.
 */
public class IDQuestionsActivity extends AppCompatActivity {

    //Component
    private ListView list_item;
    private String arrData[][], arrBobotData[][];
    private Button btns_a, btns_b;
    private int  next_id, prev_id, isShow=0;

    //General Class
    private ImageView refresh, back;
    private TextView next_button, scrollText, question_id, prev_button, soal_title;

    //Database Helper
    private DatabaseHelper myDb;
    private DecimalFormat format=new DecimalFormat("#.##");
    private Cursor isDataAvailable;

    private String a1="a1", a2="a2", a3="a3", a4="a4", a5="a5", a6="a6", a7="a7", a8="a8";
    private String soal_id, soal, answer_a, answer_b, bobot_a, bobot_b, bobot_list_a, bobot_list_b;
    private float pa1, pa2, pa3, pa4, pa5, pa6, pa7, pa8;

    Random randomQuests;
    Boolean isAnswer = FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initViews();
        myDb = new DatabaseHelper(this);
        myDb.openDataBase();
        try {
            myDb.CopyDataBaseFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        }

        randomQuests = new Random();

//        String randomQuestion = questions[randomQuests.nextInt(15)];

//        showQuestionById(String.valueOf(randomQuests.nextInt(32)));
        showQuestionById(question_id.getText().toString());
        isQuestionAnswered(Integer.parseInt(question_id.getText().toString()));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(IDQuestionsActivity.this, MainActivity.class));
            }
        });
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAnswer){
                    next_id=Integer.parseInt(question_id.getText().toString())+1;
                    if(next_id<=32) {
                        isAnswer = FALSE;
                        showQuestionById(String.valueOf(next_id));
                        isQuestionAnswered(next_id);
                        question_id.setText("" + next_id);
                        if(next_id==32) {
                            next_button.setText("Done");
                        }
                    }else {
                        finish();
                        startActivity(new Intent(IDQuestionsActivity.this, ResultActivity.class));
                    }
                } else {
                    Toast.makeText(IDQuestionsActivity.this, "Silahkan pilih jawaban terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prev_id=Integer.parseInt(question_id.getText().toString())-1;
                if(prev_id>=1) {
                    showQuestionById(String.valueOf(prev_id));
                    isQuestionAnswered(prev_id);
                    question_id.setText("" + prev_id);
                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(IDQuestionsActivity.this, "Memuat Ulang Soal", Toast.LENGTH_LONG).show();
                clearAppData();
                Intent intent=getIntent();
                finish();
                startActivity(intent);

            }
        });

        btns_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedButton(btns_a);
                setUnselectedButton(btns_b);

                bobotInitialize();
                checkBobotAnswer(bobot_list_a, bobot_a);
                checkBobotAnswer(bobot_list_b, bobot_b);

                isDataAvailable=myDb.showDataAlternatifByID(Integer.parseInt(question_id.getText().toString()));

                setDataBobotAlternatifAsFloat(bobot_a,bobot_a,bobot_b);

//                Toast.makeText(IDQuestionsActivity.this, String.valueOf(pa1), Toast.LENGTH_SHORT).show();

//                Log.d("MIX", pa1+"\n"+pa2+"\n"+
//                        pa3+"\n"+pa4+"\n"+
//                        pa5+"\n"+pa6+"\n"+
//                        pa7+"\n"+pa8);
//
                if (isDataAvailable.getCount()==0) {
                    addAnswer(a1, a2, a3, a4, a5, a6, a7, a8, question_id.getText().toString(), btns_a.getText().toString());
                    addBobotAlternatif(String.valueOf(pa1), String.valueOf(pa2),
                            String.valueOf(pa3), String.valueOf(pa4),
                            String.valueOf(pa5), String.valueOf(pa6),
                            String.valueOf(pa7), String.valueOf(pa8),
                            question_id.getText().toString());
                }else {
                    Cursor updateAnswer = myDb.updateDataAlternatif(
                            a1, a2, a3, a4, a5, a6, a7, a8, question_id.getText().toString(), btns_a.getText().toString()
                    );

                    Cursor updateBobotAlternatif = myDb.updateDataBobotAlternatif(
                            String.valueOf(pa1), String.valueOf(pa2),
                            String.valueOf(pa3), String.valueOf(pa4),
                            String.valueOf(pa5), String.valueOf(pa6),
                            String.valueOf(pa7), String.valueOf(pa8),
                            question_id.getText().toString()
                    );
                    if(updateAnswer.getCount()==0 && updateBobotAlternatif.getCount()==0 ){
                        Log.d("isUpdated : ","FALSE");
                    }
                }

                setDefaultValue();
            }
        });

        btns_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedButton(btns_b);
                setUnselectedButton(btns_a);


                bobotInitialize();
                checkBobotAnswer(bobot_list_a, bobot_b);
                checkBobotAnswer(bobot_list_b, bobot_a);

                isDataAvailable=myDb.showDataAlternatifByID(Integer.parseInt(question_id.getText().toString()));

                setDataBobotAlternatifAsFloat(bobot_b,bobot_a,bobot_b);

//                Toast.makeText(IDQuestionsActivity.this, String.valueOf(pa1), Toast.LENGTH_SHORT).show();

                if (isDataAvailable.getCount()==0) {
                    addAnswer(a1, a2, a3, a4, a5, a6, a7, a8, question_id.getText().toString(), btns_b.getText().toString());
                    addBobotAlternatif(String.valueOf(pa1), String.valueOf(pa2),
                            String.valueOf(pa3), String.valueOf(pa4),
                            String.valueOf(pa5), String.valueOf(pa6),
                            String.valueOf(pa7), String.valueOf(pa8),
                            question_id.getText().toString());
                }else {
                    Cursor updateAnswer = myDb.updateDataAlternatif(
                            a1, a2, a3, a4, a5, a6, a7, a8, question_id.getText().toString(), btns_b.getText().toString()
                    );

                    Cursor updateBobotAlternatif = myDb.updateDataBobotAlternatif(
                            String.valueOf(pa1), String.valueOf(pa2),
                            String.valueOf(pa3), String.valueOf(pa4),
                            String.valueOf(pa5), String.valueOf(pa6),
                            String.valueOf(pa7), String.valueOf(pa8),
                            question_id.getText().toString()
                    );
                    if(updateAnswer.getCount()==0 && updateBobotAlternatif.getCount()==0 ){
                        Log.d("isUpdated : ","FALSE");
                    }
                }

                setDefaultValue();
            }
        });

        scrollText.setMovementMethod(new ScrollingMovementMethod());

    }

    public void bobotInitialize() {

        String[] splitting_bobot=arrData[0][4].split("#");
        bobot_a=splitting_bobot[0].trim();
        bobot_b=splitting_bobot[1].trim();

        String[] splitting_bobot_list=arrData[0][5].split("#");
        bobot_list_a=splitting_bobot_list[0];
        bobot_list_b=splitting_bobot_list[1];
    }

    public void setDataBobotAlternatifAsFloat(String bobot_jawaban,String bobot_jawaban_a,String bobot_jawaban_b) {
        if (Integer.parseInt(bobot_jawaban_a)>Integer.parseInt(bobot_jawaban_b)){
            pa1=Float.parseFloat(bobot_jawaban)/Float.parseFloat(bobot_jawaban_a);
        } else {
            pa1=Float.parseFloat(bobot_jawaban)/Float.parseFloat(bobot_jawaban_b);
        }
//        pa1=Float.parseFloat(a1)/Float.parseFloat(a1);
        pa2=Float.parseFloat(a2)/Float.parseFloat(a1);
        pa3=Float.parseFloat(a3)/Float.parseFloat(a1);
        pa4=Float.parseFloat(a4)/Float.parseFloat(a1);
        pa5=Float.parseFloat(a5)/Float.parseFloat(a1);
        pa6=Float.parseFloat(a6)/Float.parseFloat(a1);
        pa7=Float.parseFloat(a7)/Float.parseFloat(a1);
        pa8=Float.parseFloat(a8)/Float.parseFloat(a1);
        Log.d("KOLUMN", "APPAAAA"+a8);
    }

    public void setDefaultValue() {
        a1="a1";
        a2="a2";
        a3="a3";
        a4="a4";
        a5="a5";
        a6="a6";
        a7="a7";
        a8="a8";
    }

    public void checkBobotAnswer(String bobot_list, String new_bobot) {
        if(bobot_list.contains(a1)){
            a1 = new_bobot;
        }

        if(bobot_list.contains(a2)){
            a2 = new_bobot;
        }

        if(bobot_list.contains(a3)){
            a3 = new_bobot;
        }

        if(bobot_list.contains(a4)){
            a4 = new_bobot;
        }

        if(bobot_list.contains(a5)){
            a5 = new_bobot;
        }

        if(bobot_list.contains(a6)){
            a6 = new_bobot;
        }

        if(bobot_list.contains(a7)){
            a7 = new_bobot;
        }
        if(bobot_list.contains(a8)){
            a8 = new_bobot;
        }
    }

    public void isQuestionAnswered(int prev_next_id) {
        isDataAvailable = myDb.showDataAlternatifByID(prev_next_id);
        if (isDataAvailable.getCount() != 0) {
            while (isDataAvailable.moveToNext()){
                String answerColumn= isDataAvailable.getString(10);
                String answer=answerColumn.trim().toLowerCase().replace(" ","");
                String btnA=btns_a.getText().toString().trim().toLowerCase().replace(" ","");
                String btnB=btns_b.getText().toString().trim().toLowerCase().replace(" ","");

                    if (btnA.equals(answer)) {
                        setSelectedButton(btns_a);
                        setUnselectedButton(btns_b);
                    } else if (btnB.equals(answer)) {
                        setSelectedButton(btns_b);
                        setUnselectedButton(btns_a);
                    }
                Log.d("KOLUMEN", answer+" "+btnA+" "+btnB);
            }
        }else {
            setUnselectedButton(btns_a);
            setUnselectedButton(btns_b);
        }
    }

    public void setSelectedButton(Button button) {
        isAnswer = TRUE;
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            button.setTextColor(Color.parseColor("#3D3E59"));
            button.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_primary_selected) );
        } else {
            button.setTextColor(Color.parseColor("#3D3E59"));
            button.setBackground(ContextCompat.getDrawable(this, R.drawable.btn_primary_selected));
        }
    }

    public void setUnselectedButton(Button button) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            button.setTextColor(Color.parseColor("#FFFFFF"));
            button.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_primary) );
        } else {
            button.setTextColor(Color.parseColor("#FFFFFF"));
            button.setBackground(ContextCompat.getDrawable(this, R.drawable.btn_primary));
        }
    }

    public void initViews() {
        list_item = findViewById(R.id.list_data);
        scrollText = findViewById(R.id.scrollText);
        next_button = findViewById(R.id.next);
        prev_button = findViewById(R.id.prev);
        btns_a = findViewById(R.id.answer_a);
        btns_b = findViewById(R.id.answer_b);
        soal_title = findViewById(R.id.soal);
        question_id = findViewById(R.id.question_id);
        refresh = findViewById(R.id.refresh);
        back = findViewById(R.id.back);
    }

    public void showQuestionById(String id) {
        arrData = myDb.getFromMethodDataByID(id);

        soal_id=arrData[0][0];
        soal=arrData[0][1];
        String[] splitting_answer=arrData[0][3].split("#");
        answer_a=splitting_answer[0];
        answer_b=splitting_answer[1];

//        scrollText.setText(soal_id+". "+soal);
        scrollText.setText(soal);
        btns_a.setText(answer_a);
        btns_b.setText(answer_b);
        soal_title.setText("Soal ke "+id);
//        soal_title.setText("Soal ke "+id);
    }

    public void addAnswer(String a1, String a2, String a3, String a4,
                        String a5, String a6, String a7, String a8, String soal_id, String answer){
        boolean isInserted = myDb.insertAlternatif(a1,
                a2,
                a3,
                a4,
                a5,a6, a7, a8, soal_id, answer);
        if (isInserted != true) {
            Toast.makeText(getApplicationContext(), "Gagal, Coba Lagi", Toast.LENGTH_SHORT).show();
        }
    }

    public void addBobotAlternatif(String a1, String a2, String a3, String a4,
                        String a5, String a6, String a7, String a8, String soal_id){
        boolean isInserted = myDb.insertBobotAlternatif(a1,
                a2,
                a3,
                a4,
                a5,a6, a7, a8, soal_id);
        if (isInserted != true) {
            Toast.makeText(getApplicationContext(), "Gagal, Coba Lagi", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearAppData() {
        myDb.truncateMyData();
        try {
            myDb.CopyDataBaseFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
