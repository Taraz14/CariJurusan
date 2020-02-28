package id.kunya.carijurusan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InfoJurusanActivity extends AppCompatActivity {
    CardView card_univ,card_teknik,card_pertanian,card_ekonomi,card_keguruan,card_hukum,card_perikanan,card_sosialdanpolitik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jurusan);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        card_univ = findViewById(R.id.card_univ);
        card_teknik = findViewById(R.id.card_teknik);
        card_pertanian = findViewById(R.id.card_pertanian);
        card_ekonomi = findViewById(R.id.card_ekonomi);
        card_keguruan = findViewById(R.id.card_keguruan);
        card_hukum = findViewById(R.id.card_hukum);
        card_perikanan = findViewById(R.id.card_perikanan);
        card_sosialdanpolitik = findViewById(R.id.card_sosialdanpolitik);

        card_univ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                intent.putExtra("fakultas", "Universitas Muhammadiyah Sorong");
                intent.putExtra("from", "main");
                startActivity(intent);
            }
        });

        card_teknik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                intent.putExtra("fakultas", "Fakultas Teknik");
                intent.putExtra("from", "main");
                startActivity(intent);
            }
        });

        card_pertanian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                intent.putExtra("fakultas", "Fakultas Pertanian");
                intent.putExtra("from", "main");
                startActivity(intent);
            }
        });

        card_ekonomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                intent.putExtra("fakultas", "Fakultas Ekonomi");
                intent.putExtra("from", "main");
                startActivity(intent);
            }
        });

        card_keguruan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                intent.putExtra("fakultas", "Fakultas Keguruan dan Ilmu Pendidikan");
                intent.putExtra("from", "main");
                startActivity(intent);
            }
        });

        card_hukum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                intent.putExtra("fakultas", "Fakultas Hukum");
                intent.putExtra("from", "main");
                startActivity(intent);
            }
        });

        card_perikanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                intent.putExtra("fakultas", "Fakultas Perikanan");
                intent.putExtra("from", "main");
                startActivity(intent);
            }
        });

        card_sosialdanpolitik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailInfoJurusanActivity.class);
                intent.putExtra("fakultas", "Fakultas Ilmu Sosial dan Ilmu Politik");
                intent.putExtra("from", "main");
                startActivity(intent);
            }
        });


    }
}
