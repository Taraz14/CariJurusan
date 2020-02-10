package id.kunya.carijurusan;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
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

import java.awt.font.TextAttribute;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import id.kunya.carijurusan.utils.DatabaseHelper;
import id.kunya.carijurusan.utils.Question;

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

    private String a1="a1";
    private String soal_id, soal, answer_a, answer_b, bobot_a, bobot_b, bobot_list_a, bobot_list_b;
    private float pa1;

    Random randomQuests;
    Boolean isAnswer = FALSE;
    int random;
    String TAG = "CariJurusan";
    List<Question> questionList;
    int quid = 0;
    Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initViews();
        myDb = new DatabaseHelper(this);
        myDb.openDataBase();
//        try {
//            myDb.CopyDataBaseFromAsset();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        questionList = myDb.getAllQuestions();

        //random question
        randomQuests = new Random();
        Collections.shuffle(questionList);
        currentQuestion = questionList.get(quid);
        setQuestionView();

        prev_button.setVisibility(View.GONE);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(IDQuestionsActivity.this, currentQuestion.getPrev_questions() + "  " + currentQuestion.getNext_questions(), Toast.LENGTH_SHORT).show();
                clearAppData();
                finish();
                startActivity(new Intent(IDQuestionsActivity.this, MainActivity.class));
            }
        });
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnselectedButton(btns_a);
                setUnselectedButton(btns_b);

                if (isAnswer){
                    if(quid<32){
                        prev_button.setVisibility(View.VISIBLE);
                        isAnswer = FALSE;
                        currentQuestion = questionList.get(quid);
                        setQuestionView();
                        if(quid==32) {
                            next_button.setText("Done");
                        }
                    }else{
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
//                Toast.makeText(IDQuestionsActivity.this, currentQuestion.getPrev_questions(), Toast.LENGTH_SHORT).show();
//                if(!currentQuestion.getPrev_questions().equals("NULL")) {
//                    showQuestionById(currentQuestion.getPrev_questions());
//                }
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

                isDataAvailable=myDb.showDataAlternatifByID(Integer.parseInt(currentQuestion.getId()));
                setDataBobotAlternatifAsFloat(bobot_a,bobot_a,bobot_b);
//                Toast.makeText(IDQuestionsActivity.this, String.valueOf(pa1 * Float.parseFloat(currentQuestion.getWeight_criteria())), Toast.LENGTH_SHORT).show();

                if (isDataAvailable.getCount()==0) {
                    Log.i(TAG, "Insert answer : " + String.valueOf(currentQuestion.getId()));
                    addAnswer(a1, String.valueOf(currentQuestion.getId()), btns_a.getText().toString());
                    addBobotAlternatif(String.valueOf(pa1),String.valueOf(currentQuestion.getId()));
                }else {
                    Log.i(TAG, "Update answer : " + String.valueOf(currentQuestion.getId()));
                    Cursor updateAnswer = myDb.updateDataAlternatif(
                            a1, String.valueOf(currentQuestion.getId()), btns_a.getText().toString()
                    );

                    Cursor updateBobotAlternatif = myDb.updateDataBobotAlternatif(
                            String.valueOf(pa1),
                            String.valueOf(currentQuestion.getId())
                    );
                    if(updateAnswer.getCount()==0 && updateBobotAlternatif.getCount()==0 ){
                        Log.i(TAG,"FALSE");
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

                isDataAvailable=myDb.showDataAlternatifByID(Integer.parseInt(currentQuestion.getId()));
                setDataBobotAlternatifAsFloat(bobot_b,bobot_a,bobot_b);
//                Toast.makeText(IDQuestionsActivity.this, String.valueOf(pa1 * Float.parseFloat(currentQuestion.getWeight_criteria())), Toast.LENGTH_SHORT).show();

                if (isDataAvailable.getCount()==0) {
                    Log.i(TAG, "Insert answer : " + String.valueOf(currentQuestion.getId()));
                    addAnswer(a1,String.valueOf(currentQuestion.getId()), btns_b.getText().toString());
                    addBobotAlternatif(String.valueOf(pa1),String.valueOf(currentQuestion.getId()));
                }else {
                    Log.i(TAG, "Update answer : " + String.valueOf(currentQuestion.getId()));
                    Cursor updateAnswer = myDb.updateDataAlternatif(a1, String.valueOf(currentQuestion.getId()), btns_b.getText().toString()
                    );

                    Cursor updateBobotAlternatif = myDb.updateDataBobotAlternatif(
                            String.valueOf(pa1),
                            String.valueOf(currentQuestion.getId())
                    );
                    if(updateAnswer.getCount()==0 && updateBobotAlternatif.getCount()==0 ){
                        Log.d(TAG,"FALSE");
                    }
                }

                setDefaultValue();
            }
        });

        scrollText.setMovementMethod(new ScrollingMovementMethod());

    }

    private void setQuestionView(){

        soal_id = String.valueOf(currentQuestion.getId());
        soal = currentQuestion.getQuestion();
        String[] splitting_answer=currentQuestion.getAnswer().split("#");
        answer_a=splitting_answer[0];
        answer_b=splitting_answer[1];

        scrollText.setText(soal);
        btns_a.setText(answer_a);
        btns_b.setText(answer_b);
//        soal_title.setText("Soal ke "+  soal_id);
        soal_title.setText("Soal ke "+  (quid+1));

        quid++;
    }

    public void bobotInitialize() {

        String[] splitting_bobot=currentQuestion.getWeight_answer().split("#");
        bobot_a=splitting_bobot[0].trim();
        bobot_b=splitting_bobot[1].trim();

        String[] splitting_bobot_list=currentQuestion.getDescription().split("#");
        bobot_list_a=splitting_bobot_list[0];
        bobot_list_b=splitting_bobot_list[1];
    }

    public void setDataBobotAlternatifAsFloat(String bobot_jawaban,String bobot_jawaban_a,String bobot_jawaban_b) {
        if (Integer.parseInt(bobot_jawaban_a)>Integer.parseInt(bobot_jawaban_b)){
            pa1=Float.parseFloat(bobot_jawaban)/Float.parseFloat(bobot_jawaban_a);
        } else {
            pa1=Float.parseFloat(bobot_jawaban)/Float.parseFloat(bobot_jawaban_b);
        }
    }

    public void setDefaultValue() {
        a1="a1";
    }

    public void checkBobotAnswer(String bobot_list, String new_bobot) {
        if(bobot_list.contains(a1)){
            a1 = new_bobot;
        }
    }

    public void isQuestionAnswered(int prev_next_id) {
        isDataAvailable = myDb.showDataAlternatifByID(prev_next_id);
        if (isDataAvailable.getCount() != 0) {
            while (isDataAvailable.moveToNext()){
                String answerColumn= isDataAvailable.getString(3);
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

    public void addAnswer(String a1, String soal_id, String answer){
        boolean isInserted = myDb.insertAlternatif(a1,soal_id, answer);
        if (isInserted != true) {
            Toast.makeText(getApplicationContext(), "Gagal, Coba Lagi", Toast.LENGTH_SHORT).show();
        }
    }

    public void addBobotAlternatif(String a1, String soal_id){
        boolean isInserted = myDb.insertBobotAlternatif(a1, soal_id);
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

}
