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
import java.util.ArrayList;
import java.util.Arrays;
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
    private Button btns_a, btns_b,btns_c;

    //General Class
    private ImageView refresh, back;
    private TextView next_button, scrollText, question_id, soal_title;

    //Database Helper
    private DatabaseHelper myDb;
    private DecimalFormat format=new DecimalFormat("#.##");

    private String soal_id, soal, answer_a, answer_b, answer_c, bobot_a, bobot_b, bobot_c;
    private String x_bobot_a, x_bobot_b, x_bobot_c, x_bobot_kriteria;
    private float pa1;

    ArrayList<Float> listBobot = new ArrayList<Float>();
    Boolean isAnswer = FALSE;
    Boolean isSelectedA = FALSE;
    Boolean isSelectedB = FALSE;
    Boolean isSelectedC = FALSE;
    boolean isReset = TRUE;
    boolean isResetTwo = TRUE;
    private Float bobotA,bobotB,bobotC;
    private Float v1 = 0.0f;
    String bobotKriteria;
    int random;
    String TAG = "CariJurusan";
    List<Question> questionList;
    int quid = 0;
    int number = 1;
    Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initViews();

        myDb = new DatabaseHelper(this);
        myDb.openDataBase();

        questionList = myDb.getQuestionsOne();

        //random question
//        randomQuests = new Random();
//        Collections.shuffle(questionList);
        currentQuestion = questionList.get(quid);
//        bobotInitialize();
        setQuestionView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(IDQuestionsActivity.this, currentQuestion.getPrev_questions() + "  " + currentQuestion.getNext_questions(), Toast.LENGTH_SHORT).show();
                isReset = TRUE;
                finish();
                startActivity(new Intent(IDQuestionsActivity.this, MainActivity.class));
            }
        });
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnselectedButton(btns_a);
                setUnselectedButton(btns_b);
                setUnselectedButton(btns_c);

                if (isAnswer){
                    if (isSelectedA){
                        listBobot.add(bobotA);
                        v1 = v1 +  bobotA;
                    } else if (isSelectedB){
                        listBobot.add(bobotB);
                        v1 = v1 + bobotB;
                    } else if (isSelectedC){
                        listBobot.add(bobotC);
                        v1 = v1 + bobotC;
                    }

                    if(quid<20){
                        isAnswer = FALSE;
                        currentQuestion = questionList.get(quid);
//                        bobotInitialize();
                        setQuestionView();
                        if(quid==5 && isReset) {
                            isReset = FALSE;
                            quid = 0;
                            questionList.clear();
                            questionList.addAll(myDb.getQuestionsTwo());
                            Collections.shuffle(questionList);
                            currentQuestion = questionList.get(quid);
                        }
                        else if (quid==10 && isResetTwo){
//                            next_button.setText("SELESAI");
                                isResetTwo = FALSE;
                                quid = 0;
                                questionList.clear();
                                questionList.addAll(myDb.getQuestionsThree());
                                Collections.shuffle(questionList);
                                currentQuestion = questionList.get(quid);
                        } else if (quid==20){
                            next_button.setText("SELESAI");
                        }
                    }else{
                        finish();
                        Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                        intent.putExtra("bobot",Arrays.toString(listBobot.toArray()));
                        intent.putExtra("v1","test");
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(IDQuestionsActivity.this, "Silahkan pilih jawaban terlebih dahulu", Toast.LENGTH_SHORT).show();
                }

                Log.i(TAG, String.valueOf(v1));
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isReset = TRUE;
                Toast.makeText(IDQuestionsActivity.this, "Memuat Ulang Soal", Toast.LENGTH_LONG).show();
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
                setUnselectedButton(btns_c);

//                bobotInitialize();

                setDataBobotAlternatifAsFloat(x_bobot_a,x_bobot_a,x_bobot_b,"0");
                if (soal_id.equals("5")){
                    bobotKriteria = "0.04";
                } else {
                    bobotKriteria = currentQuestion.getWeight_criteria();
                }
//                Toast.makeText(IDQuestionsActivity.this, String.valueOf(pa1 * Float.parseFloat(x_bobot_kriteria)), Toast.LENGTH_SHORT).show();
//                Toast.makeText(IDQuestionsActivity.this, x_bobot_a + "  " + x_bobot_kriteria, Toast.LENGTH_SHORT).show();
                isSelectedA = TRUE;
                isSelectedB = FALSE;
                isSelectedC = FALSE;
                bobotA = pa1 * Float.parseFloat(x_bobot_kriteria);
//                Toast.makeText(IDQuestionsActivity.this, String.valueOf(bobotA) , Toast.LENGTH_SHORT).show();
            }
        });

        btns_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedButton(btns_b);
                setUnselectedButton(btns_a);
                setUnselectedButton(btns_c);

//                bobotInitialize();

                setDataBobotAlternatifAsFloat(x_bobot_b,x_bobot_a,x_bobot_b,"0");
                if (soal_id.equals("5")){
                    bobotKriteria = "0.04";
                } else {
                    bobotKriteria = currentQuestion.getWeight_criteria();
                }
//                Toast.makeText(IDQuestionsActivity.this, String.valueOf(pa1 * Float.parseFloat(x_bobot_kriteria)), Toast.LENGTH_SHORT).show();
//                Toast.makeText(IDQuestionsActivity.this, x_bobot_b + "  " + x_bobot_kriteria, Toast.LENGTH_SHORT).show();
                isSelectedA = FALSE;
                isSelectedB = TRUE;
                isSelectedC = FALSE;
                bobotB = pa1 * Float.parseFloat(x_bobot_kriteria);
//                Toast.makeText(IDQuestionsActivity.this, String.valueOf(bobotB) , Toast.LENGTH_SHORT).show();
            }
        });

        btns_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedButton(btns_c);
                setUnselectedButton(btns_a);
                setUnselectedButton(btns_b);

//                bobotInitialize();

                setDataBobotAlternatifAsFloat(x_bobot_c,x_bobot_a,x_bobot_b,x_bobot_c);
                if (soal_id.equals("5")){
                    bobotKriteria = "0.04";
                } else {
                    bobotKriteria = currentQuestion.getWeight_criteria();
                }
//                Toast.makeText(IDQuestionsActivity.this, String.valueOf(pa1 * Float.parseFloat(x_bobot_kriteria)), Toast.LENGTH_SHORT).show();
//                Toast.makeText(IDQuestionsActivity.this, x_bobot_c + "  " + x_bobot_kriteria, Toast.LENGTH_SHORT).show();
                isSelectedA = FALSE;
                isSelectedB = FALSE;
                isSelectedC = TRUE;
                bobotC = pa1 * Float.parseFloat(x_bobot_kriteria);
//                Toast.makeText(IDQuestionsActivity.this, String.valueOf(bobotC) , Toast.LENGTH_SHORT).show();
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
        soal_title.setText("Soal ke "+  number);
//        soal_title.setText("Soal ke "+ soal_id);

        String[] splitting_bobot=currentQuestion.getWeight_answer().split("#");
        x_bobot_a=splitting_bobot[0].trim();
        x_bobot_b=splitting_bobot[1].trim();
        x_bobot_kriteria = currentQuestion.getWeight_criteria();

        if (currentQuestion.getId().equals("3") || currentQuestion.getId().equals("4") || currentQuestion.getId().equals("5")){
            btns_c.setVisibility(View.VISIBLE);
            answer_c=splitting_answer[2];
            btns_c.setText(answer_c);
            x_bobot_c=splitting_bobot[2].trim();;
        } else {
            btns_c.setVisibility(View.GONE);
        }

        quid++;
        number++;
    }

    public void bobotInitialize() {
        String test = currentQuestion.getWeight_answer();
        String[] splitting_bobot=test.split("#");
        bobot_a=splitting_bobot[0].trim();
        bobot_b=splitting_bobot[1].trim();

        if (currentQuestion.getId().equals("3") || currentQuestion.getId().equals("4") || currentQuestion.getId().equals("5")){
            bobot_c=splitting_bobot[2].trim();
        }

    }

    public void setDataBobotAlternatifAsFloat(String bobot_jawaban,String bobot_jawaban_a,String bobot_jawaban_b,String bobot_jawaban_c) {
        if (bobot_jawaban_c.equals("0")){
            if (Integer.parseInt(bobot_jawaban_a) > Integer.parseInt(bobot_jawaban_b) && Integer.parseInt(bobot_jawaban_a) > Integer.parseInt(bobot_jawaban_c)) {
                //Here you determine second biggest, but you know that a is largest
                pa1 = Float.parseFloat(bobot_jawaban) / Float.parseFloat(bobot_jawaban_a);
            }

            if (Integer.parseInt(bobot_jawaban_b) > Integer.parseInt(bobot_jawaban_a) && Integer.parseInt(bobot_jawaban_b) > Integer.parseInt(bobot_jawaban_c)) {
                //Here you determine second biggest, but you know that b is largest
                pa1=Float.parseFloat(bobot_jawaban)/Float.parseFloat(bobot_jawaban_b);
            }

            if (Integer.parseInt(bobot_jawaban_c) > Integer.parseInt(bobot_jawaban_a) && Integer.parseInt(bobot_jawaban_c) > Integer.parseInt(bobot_jawaban_b)) {
                //Here you determine second biggest, but you know that c is largest
                pa1=Float.parseFloat(bobot_jawaban)/Float.parseFloat(bobot_jawaban_c);
            }
        } else {
            if (Integer.parseInt(bobot_jawaban_a)>Integer.parseInt(bobot_jawaban_b)){
                pa1=Float.parseFloat(bobot_jawaban)/Float.parseFloat(bobot_jawaban_a);
            } else {
                pa1=Float.parseFloat(bobot_jawaban)/Float.parseFloat(bobot_jawaban_b);
            }
        }
    }

    public void setSelectedButton(Button button) {
        isAnswer = TRUE;
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            button.setTextColor(Color.parseColor("#FFFFFF"));
            button.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.btn_primary_selected) );
        } else {
            button.setTextColor(Color.parseColor("#FFFFFF"));
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
        btns_a = findViewById(R.id.answer_a);
        btns_b = findViewById(R.id.answer_b);
        btns_c = findViewById(R.id.answer_c);
        soal_title = findViewById(R.id.soal);
        question_id = findViewById(R.id.question_id);
        refresh = findViewById(R.id.refresh);
        back = findViewById(R.id.back);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
