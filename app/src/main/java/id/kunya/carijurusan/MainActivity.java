package id.kunya.carijurusan;

import androidx.appcompat.app.AppCompatActivity;
import id.kunya.carijurusan.utils.DatabaseHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by muhammad on 03/01/2020.
 */
public class MainActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    String TAG = "CariJurusan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        Button start = findViewById(R.id.start);
        Button about = findViewById(R.id.about);
        Button exit = findViewById(R.id.exit);

        myDb = new DatabaseHelper(this);

        try {
            myDb.CopyDataBaseFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        myDb.truncateMyData();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, IDQuestionsActivity.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jurusan = new Intent(MainActivity.this, InfoJurusanActivity.class);
                startActivity(jurusan);

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
