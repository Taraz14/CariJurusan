package id.kunya.carijurusan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailInfoJurusanActivity extends AppCompatActivity {
    String fakultas,from;
    private static final int MAX_STEP_UNIV = 4;
    private static final int MAX_STEP_FAKULTAS = 5;

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private Button btnNext;

    private ImageView[] dots;

    private int about_images_univ[] = {
            R.drawable.logo,
            0,
            0,
            R.drawable.univ_new
    };

    private String about_title_univ[] = {
            "Universitas Muhammadiyah Sorong",
            "Visi",
            "Misi"
    };

    private String about_description_univ[] = {
            "",
            "Menjadi Universitas Unggul Dalam Pengembangan Ipteks dan Imtaq yang berkemajuan pada Tahun 2031.",
            "1.\tMenyelenggarakan Pendidikan dan pengajaran yang berbasis hasil penelitian untuk memberi Kontribusi terhadap upaya pemecahan masalah masyarakat.\n" +
                    "2.\tMelaksanakan Kegiatan Penelitian Penelitian danPengembanganIpteks.\n" +
                    "3.\tMelaksanakan pengabdian kepada masyarakat berbasis hasil penelitian untuk memberi kontribusi terhadap upaya pemecahan masalah masyarakat.\n" +
                    "4.\tPengembangan kualitas sumberdaya manusia dan sistem informasi untuk menunjang pelaksanaan caturdharma pergutuan Tinggi Muhammadiyah.\n" +
                    "5.\tMengembangkan Sistem tat kelola Lembaga  dan memperluas jejaring kerjasama.\n" +
                    "6.\tMengembangkan pembinaan Al-Islam kemuhammadiyahan yang berorientasi pada Imtaq.\n"
    };

    private int about_images_ekonomi[] = {
            R.drawable.ekonomi,
            0,
            0,
            0,
            R.drawable.ekonomi_new
    };

    private String about_title_ekonomi[] = {
            "Fakultas Ekonomi",
            "Visi",
            "Misi",
            "Program Studi"
    };

    private String about_description_ekonomi[] = {
            "",
            "Mewujudkan fakultas ekonomi yang unggul dan memiliki kecerdasan intelektual,emosional,spiritual, dan berjiwa entrepreneur yang islami.",
            "1.\tMenyelenggarakan Pendidikan yang menghasilkan sarjana ekonomi yang berkualitas berlandaskan nilai-nilai islam.\n" +
                    "2.\tMenyelenggarakan Pendidikan yang menghasilkan sarjana ekonomi dengan kemampuan entrepreneur yang unggul.\n" +
                    "3.\tMembangun keilmuan dengan penelitian pada bidang ekonomi untuk daerah Papua, Papua Barat dan Indonesia.\n" +
                    "4.\tMenyelenggarakan pengabdian masyarakat dengan membangun perekonomian di daerah Papua dan Papua Barat berlandaskam nilai0nilai ke Islaman.\n" +
                    "5.\tMembina kerja sama dengan berbagai pihak dengan menciptakan tata kelola yang amanah dan Profesional.\n",
            "-\tManajemen (s1), Akreditasi program Studi C, Staff pengajar sebanyak 7 dosen dengan tingkat Pendidikan Strata Dua (s2) dan Strata Tiga (s3)."
    };

    private int about_images_teknik[] = {
            R.drawable.teknik,
            0,
            0,
            0,
            R.drawable.teknik_new
    };

    private String about_title_teknik[] = {
            "Fakultas Teknik",
            "Visi",
            "Misi",
            "Program Studi"
    };

    private String about_description_teknik[] = {
            "",
            "Mewujudkan Fakultas Teknik Universitas Muhammadiyah sorong yang Berkualitas, Kompetitif dan Diridhoi Allah SWT.",
            "1.\tMembentuk dan membina civitas akademika Fakultas Teknik yang mempunyai dasar  keilmuan yang kuat, dan berpotensi menjadi tenaga pekerja yang professional di bidangnya.\n" +
                    "2.\tMenyelenggarakan Pendidikan dan pengajaran yang menghasilkan sarjana Teknik berkualitas akademik seta siap saing dalam dunia kerja.\n" +
                    "3.\tMelaksanakan kegiatan penelitian dan pengabdian masyarakat yang menghasilkan produk penelitian yang berdaya guna berteknologi serba guna.\n",
            "-\tTeknik Sipil(s1), Akreditasi Program Studi C , Staff pengajar sebanyak 13 dosen dengan tingkat Pendidikan Starata Dua (s2) dan Strata Tiga (s3).\n" +
                    "-\tTeknik Industri(s1) Akreditasi Program studi C, Staff pengajar sebanyak  7 dosen dengan tingkat Pendidikan Strata Dua(s2) dan Strata Tiga (s3).\n" +
                    "-\tTeknik Informatika(s1) Akreditasi program studi C , Staff pengajar sebanyak 4 dosen dengan tingkat Pendidikan Strata Dua (s2) dan Strata Tiga (s3).\n"
    };

    private int about_images_pertanian[] = {
            R.drawable.pertanian,
            0,
            0,
            0,
            R.drawable.pertanian_new
    };

    private String about_title_pertanian[] = {
            "Fakultas Pertanian",
            "Visi",
            "Misi",
            "Program Studi"
    };

    private String about_description_pertanian[] = {
            "",
            "Menjadikan Fakultas Unggul dalam penerapan ilmu pengetahuan dan teknologi di bidang pertanian berlandaskan Imtaq pada tahun 2021.",
            "1.\tMenyelenggrakan Pendidikan dan pengajaran di bidang pertanian dan kehutanan yang bebas Ipteks.\n" +
                    "2.\tMelaksanakan kegiatan penelitian dan pengembangan Iptek di bidang pertanian  dan kehutanan.\n" +
                    "3.\tMelaksanakan pengabdian kepada masyarakat berbasis Hasil penelitian di bidang pertanian dan kehutanan untuk memberi kontribusi terhadap upaya pemecahan masalah masyarakat.\n" +
                    "4.\tMengembangkan kualitas sumber daya manusia dan sistem informasi di bidang pertanian dan kehutanan untuk menunjang pelaksanaan catur dharma Perguruan Tinggi Muhammadiyah.\n" +
                    "5.\tMengembangkan sistem tatakelola akademik dan memperluas jejaring kerjasama di bidang pertanian dan kehutanan.\n" +
                    "6.\tMengembangkan Pembinaan Al-Islam dan kemuhammadiyahan di bidang pertanian dan kehutanan yang berorientasi pada Imtaq.\n",
            "-\tAgroteknologi(s1) Akreditasi Program Studi B, staff pengajar sebanyak 6 dosen dengan tingkat Pendidikan Strata Dua (s2) dan Strata Tiga(s3).\n" +
                    "-\tKehutanan (s1) Akreditasi Program Studi C , Staff pengajar sebanyak 7 dosen dengan tingkat Pendidikan Strata Dua (s2) dan Strata Tiga (S3). \n"
    };

    private int about_images_ilmusosial[] = {
            R.drawable.ilmusosial,
            0,
            0,
            0,
            R.drawable.ilmusosial_new
    };

    private String about_title_ilmusosial[] = {
            "Fakultas Ilmu Sosial dan Ilmu Politik",
            "Visi",
            "Misi",
            "Program Studi"
    };

    private String about_description_ilmusosial[] = {
            "",
            "Menjadikan fakultas yang unggul di tingkat local maupun nasional dalam menghasilkan sarjana yang memiliki kecerdasan Intelektual,emosional,spiritual,dan berjiwa entrepreneur yang nasionalis pada tahun 2020.",
            "1.\tMenyelenggrakan Pendidikan dan pengajaran yang bermutu untukmenghasilkan sarjanayang unggul dan kompetitif.\n" +
                    "2.\tMelakukan riset dan pengkajian berbagai fenomena penyelenggaraan Administrasi  Negara,Otonomi Daerah,Otonomi Khusus dan desa.\n" +
                    "3.\tMelkaukan pemberdayaan kepada masyarakat dalambentuk pelatihan,pendampingan,kuliah kerja,penyuluhan,dan dalam bentuk lainnya yang relevan.\n" +
                    "4.\tMengintegrasikan Nilai-Nilai Islam dan kemuhammadiyahan dalam Ilmu pengetahuan dan teknologi,menuju keluhuran Akhlak,dan kematangan professional.\n",
            "-\tIlmu Administrasi Negara (S1) , Akreditasi Prgram Studi  B , Staff pengajar Sebanyak 17 dosen, dengan tingkat Pendidikan Strata Dua(s2) dan Strata Tiga (S3).\n" +
                    "-\tIlmu Pemerintahan (s1), Akreditasi Program Studi B, Staff Pengajar sebanyak 10 dosen dengan tingkat Pendidikan Strata 2 dan strata 3.\n" +
                    "-\tSosiologi(s1), Akreditasi Jurusan B ,Staff pengajar sebanyak 8 dosen dengan tingkat Pendidikan Starata Dua(s2) dan Strata Tiga (S3).\n"
    };

    private int about_images_keguruan[] = {
            R.drawable.keguruan,
            0,
            0,
            0,
            R.drawable.keguruan_new
    };

    private String about_title_keguruan[] = {
            "Fakultas Keguruan dan Ilmu Pendidikan",
            "Visi",
            "Misi",
            "Program Studi"
    };

    private String about_description_keguruan[] = {
            "",
            "Menjadi fakultas yang utama dan unggul demi terwujudnya sarjana keguruan dan Ilmu Pendidikan yang memiliki kecerdasan Intelektual, Emosional, Spiritual, dan berjiawa entrepreneur yang Islami.",
            "1.\tMenyelenggarakan Pendidikan dan pengajaran yang bermutu untuk menghasilkan sarjana keguruan dan ilmu Pendidikan yang unggul dan kompettitif.\n" +
                    "2.\tMengembangkan dan menyelenggarakan penelitian dan pengajaran dengan memanfaatkan aplikasi teknologi Informasi.\n" +
                    "3.\tMelaksanakan kegiatan pengabdian pada masyarakat sebagai salah satu proses pemantapan dan pemanfaatan ilmu untuk masyarakat, khususnya yang berkaitan dengan keguruan dan ilmu Pendidikan.\n" +
                    "4.\tMengintegrasikan nilai-nilai Islam dan kemuahammadiyahan dalam ilmu pengetahuan dan teknologi, menuju keluhuran akhla, dan kematangan professional.\n",
            "-\tPendidikan Bahasa Inggris(s1) Akreditasi Program Studi B, Staff pengajar sebanyak 16 dosen dengan tingkat Pendidikan Strata Dua (s2) dan Strata Tiga(s3).\n" +
                    "-\tPendidikan Matematika (s2) Akreditasi Program Studi C, Staff pengajar sebanyak 10 dosen dengan tingkat Pendidikan Strata Dua (s2) dan Strata Tiga(s3).\n"
    };

    private int about_images_perikanan[] = {
            R.drawable.perikanan,
            0,
            0,
            0,
            R.drawable.perikanan_new
    };

    private String about_title_perikanan[] = {
            "Fakultas Perikanan",
            "Visi",
            "Misi",
            "Program Studi"
    };

    private String about_description_perikanan[] = {
            "",
            "Menjadi Fakultas yang unggul, Bersaing dalam pengembangan Sumber Daya Manusia,Ilmu Pengetahuan,Teknologi dan Seni berdasarkan Iman dan Taqwa dalam pengelolaan sumber daya kelautan dan perikanan yang berkelanjutan Tahun 2020.",
            "1.\tMenghasilkan sumber daya manusia yang unggul bersaing di bidang kelautan dan perikanan melalui penyelenggaraan Pendidikan tinggi.\n" +
                    "2.\tMenyelenggarakan Pendidikan bertaraf nasional yang berorientasi pada kebutuhan pengembangan kelautan dan perikanan.\n" +
                    "3.\tMenyelenggarakan penelitian dasar maupun terapannya secara terpadu dan bertaraf nasional guna menunjang pengembangan iptek untuk kemaslahatan umat.\n" +
                    "4.\tMeningkatkan peran fakultas perikanan untuk pemanfaatan iptek guna menunjang kesejahteran dan kenyamanan masyarakat dalam aspek material dan spiritual.\n" +
                    "5.\tMenjadikan fakultas perikanan sebagai pusat layanan penelitian,pelatihan, dan konsultasi dalam bidang manajemen sumber daya perairan dan pengolahan hasil perikanan serta terapannya.\n",
            "-\tManajemen Sumber Daya Perairan(s1) Akreditasi Program Studi C, Staff Pengajar sebanyak 8 dosen dengan tingkat Pendidikan Strata Dua (s2) dan Strata Tiga(s3).\n" +
                    "-\tPengelola Hasil Perikanan (s1) Akreditasi Program Studi B , Staff pengajar sebanyak 7 dosen dengan tingkat Pendidikan Strata Dua (s2) dan Strata Tiga (s3).\n"
    };

    private int about_images_hukum[] = {
            R.drawable.hukum,
            0,
            0,
            0,
            R.drawable.hukum_new
    };

    private String about_title_hukum[] = {
            "Fakultas Hukum",
            "Visi",
            "Misi",
            "Program Studi"
    };

    private String about_description_hukum[] = {
            "",
            "Menjadi Pusat Unggulan  Pedidikan Tinggi Ilmu Hukum yang Berkemajuan di Kawasan Timur Indonesia Pada tahun 2020.",
            "1.\tMenyelengarakan pembinaan iman dan taqwa sebagai wujud pengabdian kepada Allah SWT.\n" +
                    "2.\tMenyelenggarakan Pendidikan dan pengajaran di bidang ilmu hokum untuk menghasilkan lulusan yang berkualitas dan menjunjung tinggi nilai-nilai keadilan.\n" +
                    "3.\tMenyelenggarakan kegiatan penelitian di bidang ilmu hokum yang terkait dengan penerapan dan pengembangan hokum.\n" +
                    "4.\tMenyelenggarakan kegiatan pengabdian kepada Masyarakat di bidang ilmu hokum dalam menjalankan profesinya guna meningkatkan layanan kepada masyarakat.\n" +
                    "5.\tMengembangkan Ilmu hokum yang berkualitas berlandaskan nilai Imtaq dan kearifan Lokal.\n" +
                    "6.\tMengembangkan sumber daya manusia (SDM) hokum yang berkarakter dan berkualitas berlandaskan nilai-nilai keimanan.\n" +
                    "7.\tMengembangkan ilmu bagi kemajuan penegakan hokum berlandaskan nilai – nilai keadilan.\n" +
                    "8.\tMenyediakan lingkungan belajar yang kondunsif berlandaskan nilai- nilai kebersamaan.\n",
            "-\tIlmu Hukum (S1), Akreditasi program Studi  B , Staff Pengajaran sebanyak 14 dosen dengan tingkat Pendidikan Strata Dua (s2) dan Strata Tiga (s3)."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info_jurusan);

        Bundle extras = DetailInfoJurusanActivity.this.getIntent().getExtras();
        fakultas = extras.getString("fakultas");
        from = extras.getString("from");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(fakultas);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                if (from.equals("main")){
//                    finish();
//                } else {
//
//                }
            }
        });

        initComponent();
    }

    private void initComponent() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        btnNext = (Button) findViewById(R.id.btn_lanjut);

        // adding bottom dots
        bottomProgressDots(0,fakultas);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() + 1;
                if (fakultas.equals("Universitas Muhammadiyah Sorong")){
                    if (current < MAX_STEP_UNIV) {
                        // move to next screen
                        viewPager.setCurrentItem(current);
                    } else {
                        finish();
//                        if (from.equals("main")){
//                            finish();
//                        } else {
//
//                        }
                    }
                } else {
                    if (current < MAX_STEP_FAKULTAS) {
                        // move to next screen
                        viewPager.setCurrentItem(current);
                    } else {
                        finish();
//                        if (from.equals("main")){
//                            finish();
//                        } else {
//
//                        }
                    }
                }
            }
        });

        ((ImageButton)findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                if (from.equals("main")){
//                    finish();
//                } else {
//
//                }
            }
        });

    }

    private void bottomProgressDots(int current_index, String fakultasku) {
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        if (fakultasku.equals("Universitas Muhammadiyah Sorong")){
            dots = new ImageView[MAX_STEP_UNIV];
        } else {
            dots = new ImageView[MAX_STEP_FAKULTAS];
        }

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.colorPurpleDark), PorterDuff.Mode.SRC_IN);
        }
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position,fakultas);

            if (fakultas.equals("Universitas Muhammadiyah Sorong")){
                if (position == MAX_STEP_UNIV - 1) {
                    btnNext.setText("MENGERTI");
                    btnNext.setBackgroundColor(getResources().getColor(R.color.colorPurpleDark));
                    btnNext.setTextColor(Color.WHITE);

                } else {
                    btnNext.setText("LANJUT");
                    btnNext.setBackgroundColor(getResources().getColor(R.color.grey_10));
                    btnNext.setTextColor(getResources().getColor(R.color.grey_90));
                }
            } else {
                if (position == MAX_STEP_FAKULTAS - 1) {
                    btnNext.setText("MENGERTI");
                    btnNext.setBackgroundColor(getResources().getColor(R.color.colorPurpleDark));
                    btnNext.setTextColor(Color.WHITE);

                } else {
                    btnNext.setText("LANJUT");
                    btnNext.setBackgroundColor(getResources().getColor(R.color.grey_10));
                    btnNext.setTextColor(getResources().getColor(R.color.grey_90));
                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_stepper_wizard, container, false);
            ImageView image = view.findViewById(R.id.image);
            ImageView image_new = view.findViewById(R.id.image_new);
            TextView title = view.findViewById(R.id.title);
            TextView description = view.findViewById(R.id.description);

            try {
                switch(fakultas) {
                    case "Universitas Muhammadiyah Sorong":
                        title.setText(about_title_univ[position]);
                        break;
                    case "Fakultas Ekonomi":
                        title.setText(about_title_ekonomi[position]);
                        break;
                    case "Fakultas Teknik":
                        title.setText(about_title_teknik[position]);
                        break;
                    case "Fakultas Pertanian":
                        title.setText(about_title_pertanian[position]);
                        break;
                    case "Fakultas Ilmu Sosial dan Ilmu Politik":
                        title.setText(about_title_ilmusosial[position]);
                        break;
                    case "Fakultas Keguruan dan Ilmu Pendidikan":
                        title.setText(about_title_keguruan[position]);
                        break;
                    case "Fakultas Perikanan":
                        title.setText(about_title_perikanan[position]);
                        break;
                    case "Fakultas Hukum":
                        title.setText(about_title_hukum[position]);
                        break;
                    default:
                        title.setText("Error");
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e){
                e.getMessage();
                title.setVisibility(View.GONE);
            }

            try {
                switch(fakultas) {
                    case "Universitas Muhammadiyah Sorong":
                        description.setText(about_description_univ[position]);
                        break;
                    case "Fakultas Ekonomi":
                        description.setText(about_description_ekonomi[position]);
                        break;
                    case "Fakultas Teknik":
                        description.setText(about_description_teknik[position]);
                        break;
                    case "Fakultas Pertanian":
                        description.setText(about_description_pertanian[position]);
                        break;
                    case "Fakultas Ilmu Sosial dan Ilmu Politik":
                        description.setText(about_description_ilmusosial[position]);
                        break;
                    case "Fakultas Keguruan dan Ilmu Pendidikan":
                        description.setText(about_description_keguruan[position]);
                        break;
                    case "Fakultas Perikanan":
                        description.setText(about_description_perikanan[position]);
                        break;
                    case "Fakultas Hukum":
                        description.setText(about_description_hukum[position]);
                        break;
                    default:
                        description.setText("Error");
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e){
                e.getMessage();
                description.setVisibility(View.GONE);
            }

            try {
                switch(fakultas) {
                    case "Universitas Muhammadiyah Sorong":
                        if (position==0 || position==3) {
                            if (position == 3) {
                                image.setVisibility(View.GONE);
                                image_new.setVisibility(View.VISIBLE);
                                image_new.setImageResource(about_images_univ[position]);
                            }
                            image.setImageResource(about_images_univ[position]);
                        } else {
                            image.setVisibility(View.GONE);
                        }
                        break;
                    case "Fakultas Ekonomi":
                        if (position==0 || position==4) {
                            if (position == 4) {
                                image.setVisibility(View.GONE);
                                image_new.setVisibility(View.VISIBLE);
                                image_new.setImageResource(about_images_ekonomi[position]);
                            }
                            image.setImageResource(about_images_ekonomi[position]);
                        } else {
                            image.setVisibility(View.GONE);
                        }
                        break;
                    case "Fakultas Teknik":
                        if (position==0 || position==4) {
                            if (position == 4) {
                                image.setVisibility(View.GONE);
                                image_new.setVisibility(View.VISIBLE);
                                image_new.setImageResource(about_images_teknik[position]);
                            }
                            image.setImageResource(about_images_teknik[position]);
                        } else {
                            image.setVisibility(View.GONE);
                        }
                        break;
                    case "Fakultas Pertanian":
                        if (position==0 || position==4) {
                            if (position == 4) {
                                image.setVisibility(View.GONE);
                                image_new.setVisibility(View.VISIBLE);
                                image_new.setImageResource(about_images_pertanian[position]);
                            }
                            image.setImageResource(about_images_pertanian[position]);
                        } else {
                            image.setVisibility(View.GONE);
                        }
                        break;
                    case "Fakultas Ilmu Sosial dan Ilmu Politik":
                        if (position==0 || position==4) {
                            if (position == 4) {
                                image.setVisibility(View.GONE);
                                image_new.setVisibility(View.VISIBLE);
                                image_new.setImageResource(about_images_ilmusosial[position]);
                            }
                            image.setImageResource(about_images_ilmusosial[position]);
                        } else {
                            image.setVisibility(View.GONE);
                        }
                        break;
                    case "Fakultas Keguruan dan Ilmu Pendidikan":
                        if (position==0 || position==4) {
                            if (position == 4) {
                                image.setVisibility(View.GONE);
                                image_new.setVisibility(View.VISIBLE);
                                image_new.setImageResource(about_images_keguruan[position]);
                            }
                            image.setImageResource(about_images_keguruan[position]);
                        } else {
                            image.setVisibility(View.GONE);
                        }
                        break;
                    case "Fakultas Perikanan":
                        if (position==0 || position==4) {
                            if (position == 4) {
                                image.setVisibility(View.GONE);
                                image_new.setVisibility(View.VISIBLE);
                                image_new.setImageResource(about_images_perikanan[position]);
                            }
                            image.setImageResource(about_images_perikanan[position]);
                        } else {
                            image.setVisibility(View.GONE);
                        }
                        break;
                    case "Fakultas Hukum":
                        if (position==0 || position==4) {
                            if (position == 4) {
                                image.setVisibility(View.GONE);
                                image_new.setVisibility(View.VISIBLE);
                                image_new.setImageResource(about_images_hukum[position]);
                            }
                            image.setImageResource(about_images_hukum[position]);
                        } else {
                            image.setVisibility(View.GONE);
                        }
                        break;
                    default:
                        image_new.setVisibility(View.GONE);
                        image.setVisibility(View.GONE);
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                e.getMessage();
                image_new.setVisibility(View.GONE);
                image.setVisibility(View.GONE);
            }

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            if (fakultas.equals("Universitas Muhammadiyah Sorong")){
                return MAX_STEP_UNIV;
            } else {
                return MAX_STEP_FAKULTAS;
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
//        super.onBackPressed();
//        if (from.equals("main")){
//            finish();
//        } else {
//
//        }
    }
}
