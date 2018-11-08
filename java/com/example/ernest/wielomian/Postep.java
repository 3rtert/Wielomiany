package com.example.ernest.wielomian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Postep extends AppCompatActivity implements View.OnClickListener
{

    Intent intencja;
    TextView zadaniaTymRazem, czasTymRazem, zadania, czas;
    TextView odblokowaneTekst, zaliczoneTekst, opanowaneTekst;
    ProgressBar odblokowane, zaliczone, calkowite;
    Button wiecejInformacji;

    int liczbaZadan;
    int rozwiazanoZadan[];
    long czasSpedzoynNaZadaniu[];
    int liczbaPodejsc[];
    int postepRozwiazywaniaZadania[];
    boolean czyZadanieZaliczone[];
    boolean czyZadanieOdblokowane[];
    String tytulZadania[];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postep);

        zadaniaTymRazem=(TextView) findViewById(R.id.textView4);
        czasTymRazem=(TextView) findViewById(R.id.textView5);
        zadania=(TextView) findViewById(R.id.textView3);
        czas=(TextView) findViewById(R.id.textView9);
        odblokowaneTekst=(TextView) findViewById(R.id.textView);
        zaliczoneTekst=(TextView) findViewById(R.id.textView6);
        opanowaneTekst=(TextView) findViewById(R.id.textView16);
        odblokowane=(ProgressBar) findViewById(R.id.progressBar4);
        zaliczone=(ProgressBar) findViewById(R.id.progressBar);
        calkowite=(ProgressBar) findViewById(R.id.progressBar5);

        wiecejInformacji=(Button) findViewById(R.id.button2);
        wiecejInformacji.setOnClickListener(this);

        intencja=getIntent();

        zadaniaTymRazem.setText(intencja.getStringExtra("RozwiazanoZadanTymRazem"));
        czasTymRazem.setText(zamienNaCzas(Long.parseLong(intencja.getStringExtra("CzasTymRazem")))+"");
        liczbaZadan=Integer.parseInt(intencja.getStringExtra("liczbaZadan"));

        int rozwiazano=0;
        long spedzono=0;
        int podejsc=0;

        int zaliczone=0;
        int odblokowane=0;
        int opanowane=0;

        rozwiazanoZadan=new int[liczbaZadan];
        czasSpedzoynNaZadaniu=new long[liczbaZadan];
        liczbaPodejsc=new int[liczbaZadan];
        postepRozwiazywaniaZadania=new int[liczbaZadan];
        czyZadanieZaliczone=new boolean[liczbaZadan];
        czyZadanieOdblokowane=new boolean[liczbaZadan];
        tytulZadania=new String[liczbaZadan];

        for(int i=0;i<liczbaZadan;i++)
        {
            tytulZadania[i]=intencja.getStringExtra("tytulZadania"+i);

            rozwiazanoZadan[i]=Integer.parseInt(intencja.getStringExtra("RozwiazanoZadan"+i));
            rozwiazano+=rozwiazanoZadan[i];
            czasSpedzoynNaZadaniu[i]=Long.parseLong(intencja.getStringExtra("CzasSpedzonyNaZadaniu"+i));
            spedzono+=czasSpedzoynNaZadaniu[i];
            liczbaPodejsc[i]=Integer.parseInt(intencja.getStringExtra("LiczbaPodejsc"+i));
            podejsc+=liczbaPodejsc[i];

            postepRozwiazywaniaZadania[i]=Integer.parseInt(intencja.getStringExtra("postepRozwiazywaniaZadania"+i));
            if(postepRozwiazywaniaZadania[i]==100)
                opanowane++;
            czyZadanieZaliczone[i]=Boolean.parseBoolean(intencja.getStringExtra("czyZadanieZaliczone"+i));
            if(czyZadanieZaliczone[i])
                zaliczone++;
            czyZadanieOdblokowane[i]=Boolean.parseBoolean(intencja.getStringExtra("czyZadanieOdblokowane"+i));
            if(czyZadanieOdblokowane[i])
                odblokowane++;
        }


        zadania.setText(rozwiazano+"");
        czas.setText(zamienNaCzas(spedzono));
        odblokowaneTekst.setText("Odblokowanych zadań ("+odblokowane+"/"+liczbaZadan+")");
        zaliczoneTekst.setText("Zaliczonych zadań ("+zaliczone+"/"+liczbaZadan+")");
        opanowaneTekst.setText("Opanowanych całkowicie Zadań ("+opanowane+"/"+liczbaZadan+")");

        this.odblokowane.setMax(liczbaZadan);
        this.zaliczone.setMax(liczbaZadan);
        this.calkowite.setMax(liczbaZadan);
        this.odblokowane.setProgress(odblokowane);
        this.zaliczone.setProgress(zaliczone);
        this.calkowite.setProgress(opanowane);
    }
    private String zamienNaCzas(long czas)
    {
        czas=czas/1000;
        int godziny=(int)czas/3600;
        int minuty=((int)czas-godziny*3600)/60;
        int sekundy=((int)czas)-godziny*3600-minuty*60;
        return godziny+" godz "+minuty+" min "+sekundy+" s";
    }

    @Override
    public void onClick(View v)
    {
        Intent intencja=new Intent(this,SzczegolowyPostep.class);
        intencja.putExtra("liczbaZadan",liczbaZadan);

        for(int i=0;i<liczbaZadan;i++)
        {
            intencja.putExtra("RozwiazanoZadan"+i, rozwiazanoZadan[i]);
            intencja.putExtra("CzasSpedzonyNaZadaniu"+i, czasSpedzoynNaZadaniu[i]);
            intencja.putExtra("LiczbaPodejsc"+i,liczbaPodejsc[i]);

            intencja.putExtra("postepRozwiazywaniaZadania"+i,postepRozwiazywaniaZadania[i]);
            intencja.putExtra("tytulZadania"+i,tytulZadania[i]);
        }

        this.startActivity(intencja);
    }
}
