package com.example.proiect_12;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Calendar;
import android.os.Bundle;
//import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int aa_curent, ll_curent, zz_curent;
    int aa_nastere, ll_nastere, zz_nastere;
    private LinearLayout layout_rezultat, layout_present_day, layout_birth_day;
    DatePicker datePicker_present_day, datePicker_birth_day;
    Button calcul, refresh;
    TextView zi, luni, ani;

    void citire_data_nastere(){

        zz_nastere = datePicker_birth_day.getDayOfMonth();
        ll_nastere = datePicker_birth_day.getMonth() + 1;
        aa_nastere = datePicker_birth_day.getYear();
    }
    void citire_data_curenta(){

        zz_curent = datePicker_present_day.getDayOfMonth();
        ll_curent = datePicker_present_day.getMonth() + 1;
        aa_curent = datePicker_present_day.getYear();
    }
    void afisare_virsta(int a, int l, int z){
        zi.setText(String.valueOf(z));
        luni.setText(String.valueOf(l));
        ani.setText(String.valueOf(a));
    }
    void mesaj_ERROR(){
        ani.setText (" Data nașterii ");
        luni.setText("   mai mare");
        zi.setText  (" Data curentă!!!");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcul = findViewById(R.id.calcul);
        refresh = findViewById(R.id.refresh);

        datePicker_present_day = findViewById(R.id.datePicker_azi);
        datePicker_birth_day = findViewById(R.id.datePicker_nastere);

        layout_present_day = (LinearLayout) findViewById(R.id.present_day);
        layout_birth_day = (LinearLayout) findViewById(R.id.birth_day);
        layout_rezultat = (LinearLayout) findViewById(R.id.rezultat);

        zi   = (TextView) findViewById(R.id.virsta_zile);
        luni = (TextView) findViewById(R.id.virsta_luni);
        ani  = (TextView) findViewById(R.id.virsta_ani);

        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                citire_data_curenta();
                citire_data_nastere();
                layout_rezultat.setVisibility(View.VISIBLE);
                datePicker_present_day.setEnabled(false);
                datePicker_birth_day.setEnabled(false);
                calcul.setEnabled(false);

                int virsta_z=0, virsta_l=0, virsta_a=0;
            //Calculel zile
                if (zz_curent >= zz_nastere){virsta_z = zz_curent - zz_nastere;}
                else{
                    switch (ll_curent){
                        case 1:case 3:case 5:case 7:case 8:case 10:case 12: virsta_z = 31 + zz_curent - zz_nastere;
                            break;
                        case 4:case 6:case 9: case 11: virsta_z = 30 + zz_curent - zz_nastere;
                            break;
                        case 2 : if((aa_curent % 4 == 0) & (aa_curent % 400 == 0) & (aa_curent != 0)){  virsta_z = 29 + zz_curent - zz_nastere;}
                                 else{ virsta_z = 28 + zz_curent - zz_nastere;}
                            break;
                    }
                    ll_curent = ll_curent - 1;
                }
            //Calculez luni
                if (ll_curent >= ll_nastere){ virsta_l = ll_curent - ll_nastere;}
                else {
                    virsta_l = 12 + ll_curent -ll_nastere;
                    aa_curent = aa_curent - 1;
                }
            //Calculez ani
                if(aa_curent >= aa_nastere){ virsta_a = aa_curent - aa_nastere;}
                else{ virsta_a = -1; }
            //Afișare rezultate
                if(virsta_a != -1){afisare_virsta(virsta_a, virsta_l, virsta_z);}
                else{
                    //Mesaj ERROR!!!
                    mesaj_ERROR();
                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePicker_present_day.setEnabled(true);
                datePicker_birth_day.setEnabled(true);
                layout_rezultat.setVisibility(View.INVISIBLE);
                calcul.setEnabled(true);


                zi.setText("Zile");
                luni.setText("Luni");
                ani.setText("Ani");

            }
        });
    }

}
