package com.example.ernest.wielomian;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Html;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SzczegolowyPostep extends AppCompatActivity
{
    TextView[] numerZadania;
    ProgressBar[] postepZadania;
    TextView[] poswieconoCzasuTekst;
    TextView[] wykonanoRazyTekst;


    Intent intencja;
    int liczbaZadan;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szczegolowy_postep);

        intencja=getIntent();

        liczbaZadan=intencja.getIntExtra("liczbaZadan",0);

        numerZadania=new TextView[liczbaZadan];
        postepZadania=new ProgressBar[liczbaZadan];
        poswieconoCzasuTekst=new TextView[liczbaZadan];
        wykonanoRazyTekst=new TextView[liczbaZadan];

        paintGUI();
    }
    public void paintGUI()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int step=height/7;

        AbsoluteLayout ll=(AbsoluteLayout) findViewById(R.id.ll2);
        ll.setMinimumHeight(liczbaZadan*(step+50));
        int tempy=0;

        for(int i=0;i<liczbaZadan;i++)
        {
            numerZadania[i]=new TextView(this);
            numerZadania[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            numerZadania[i].setText(Html.fromHtml("<b>"+intencja.getStringExtra("tytulZadania"+i)+"<b>"));
            numerZadania[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,height/35);
            numerZadania[i].setX(15);
            numerZadania[i].setY(tempy+25);
            numerZadania[i].setTextColor(Color.BLACK);

            ll.addView(numerZadania[i]);

            postepZadania[i]= new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);
            postepZadania[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            postepZadania[i].setMax(100);
            postepZadania[i].setProgress(intencja.getIntExtra("postepRozwiazywaniaZadania"+i,0));
            postepZadania[i].setX(0);
            postepZadania[i].setY(tempy+height/21);

            ll.addView(postepZadania[i]);

            poswieconoCzasuTekst[i]=new TextView(this);
            poswieconoCzasuTekst[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            poswieconoCzasuTekst[i].setText("Spędzono na zadaniu: "+zamienNaCzas(intencja.getLongExtra("CzasSpedzonyNaZadaniu"+i,0)));
            poswieconoCzasuTekst[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,height/40);
            poswieconoCzasuTekst[i].setTextColor(Color.BLACK);
            poswieconoCzasuTekst[i].setX(15);
            poswieconoCzasuTekst[i].setY(tempy+height/17);
            ll.addView(poswieconoCzasuTekst[i]);

            wykonanoRazyTekst[i]=new TextView(this);
            wykonanoRazyTekst[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

            int liczbaWykonanZadania=intencja.getIntExtra("RozwiazanoZadan"+i,0);
            wykonanoRazyTekst[i].setText("Zadanie wykonano: "+liczbaWykonanZadania+(liczbaWykonanZadania==1?" raz":" razy"));
            wykonanoRazyTekst[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,height/40);
            wykonanoRazyTekst[i].setTextColor(Color.BLACK);
            wykonanoRazyTekst[i].setX(15);
            wykonanoRazyTekst[i].setY(tempy+height/10);
            ll.addView(wykonanoRazyTekst[i]);


            tempy+=step+50;
        }


    }

    private String zamienNaCzas(long czas)
    {
        czas=czas/1000;
        int godziny=(int)czas/3600;
        int minuty=((int)czas-godziny*3600)/60;
        int sekundy=((int)czas)-godziny*3600-minuty*60;
        return godziny+" godz "+minuty+" min "+sekundy+" s";
    }
}
