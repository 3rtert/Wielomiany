package com.example.ernest.wielomian;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import gra.*;

public class GlownaSciezka extends AppCompatActivity implements View.OnLongClickListener
{
    Prezenter prezenter;

    ImageView[] tloZadania;
    TextView[] ocena;
    Button[] zacznijZadanie;
    TextView[] tytulZadania;
    TextView[] trescZadania;
    int liczbaZadan;
    Button postep;
    Button osiagniecia;
    TextView punkty;
    Gra model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glowna_sciezka);
        model=new Gra(this);
        prezenter=new Prezenter(this,model);
        model.wczytajDane();

        liczbaZadan=model.dajLiczbeZadan();
        tloZadania=new ImageView[liczbaZadan];
        ocena=new TextView[liczbaZadan];
        zacznijZadanie=new Button[liczbaZadan];
        tytulZadania=new TextView[liczbaZadan];
        trescZadania=new TextView[liczbaZadan];

        paintGUI();
    }

    public void paintGUI()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        int tempx=0,tempy=0;
        int step=width*9/20;

        int belkaZPrzyciskami=height/5+50;

        System.out.println(step);

        int liczbaZadan=model.postep.dajLiczbeOdblokowanychZadan()<model.dajLiczbeZadan()?model.postep.dajLiczbeOdblokowanychZadan()+1:model.postep.dajLiczbeOdblokowanychZadan();

        AbsoluteLayout ll=(AbsoluteLayout) findViewById(R.id.ll);
        ll.setMinimumHeight(liczbaZadan*(step+25)+belkaZPrzyciskami);

        punkty=new TextView(this);
        punkty.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        punkty.setX(10);
        punkty.setY(10);
        punkty.setTextSize(TypedValue.COMPLEX_UNIT_PX,height/30);
        punkty.setText(Html.fromHtml("<b>Posiadane punkty: </b>"+model.statystyki.punkty));
        punkty.setTextColor(Color.BLACK);

        ll.addView(punkty);

        postep=new Button(this);
        postep.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        postep.setId(1000+1);
        postep.setX(0);
        postep.setY(height/20+25);
        postep.setText("Statystyki");
        postep.setTextSize(TypedValue.COMPLEX_UNIT_PX,height/35);
        postep.setOnClickListener(prezenter);
        ll.addView(postep);


        osiagniecia=new Button(this);
        osiagniecia.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        osiagniecia.setId(1000+2);
        osiagniecia.setX(0);
        osiagniecia.setY(height/10+75);
        osiagniecia.setText("Osiągnięcia");
        osiagniecia.setTextSize(TypedValue.COMPLEX_UNIT_PX,height/35);
        osiagniecia.setOnClickListener(prezenter);
        ll.addView(osiagniecia);


        for(int i=liczbaZadan-1;i>=0;i--)
        {
            tloZadania[i]=new ImageView(this);
            tloZadania[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            tloZadania[i].setId(i+liczbaZadan);

            if(!model.postep.czyZadanieOdblokowane[i])
                tloZadania[i].setImageDrawable(getResources().getDrawable(R.drawable.zablokowane,null));
            else if(model.postep.postepRozwiazywaniaZadania[i]==100)
                tloZadania[i].setImageDrawable(getResources().getDrawable(R.drawable.zloto,null));
            else if(model.postep.czyZadanieZaliczone[i])
                tloZadania[i].setImageDrawable(getResources().getDrawable(R.drawable.zaliczone,null));
            else
                tloZadania[i].setImageDrawable(getResources().getDrawable(R.drawable.odblokowane,null));


            tloZadania[i].setMaxWidth(width);
            tloZadania[i].setMinimumWidth(width);
            tloZadania[i].setMinimumHeight(step);
            tloZadania[i].setX(tempx);
            tloZadania[i].setY(tempy+belkaZPrzyciskami);
            ll.addView(tloZadania[i]);



            ocena[i] = new TextView(this);
            ocena[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            ocena[i].setId(i+liczbaZadan*2);
            ocena[i].setText(model.postep.postepRozwiazywaniaZadania[i]+"%");
            ocena[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,height/15);
            ocena[i].setX(3*width/100);
            ocena[i].setY(tempy+30*step/100+belkaZPrzyciskami);
            ocena[i].setTextColor(Color.BLACK);
            ll.addView(ocena[i]);



            zacznijZadanie[i]=new Button(this);
            zacznijZadanie[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            zacznijZadanie[i].setId(i);
            zacznijZadanie[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,height/45);
            zacznijZadanie[i].setText("Zaczynajmy");
            zacznijZadanie[i].setOnClickListener(prezenter);
            zacznijZadanie[i].setY(tempy+belkaZPrzyciskami);
            zacznijZadanie[i].setX(0);
            ll.addView(zacznijZadanie[i]);

            tytulZadania[i]=new TextView(this);
            tytulZadania[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            tytulZadania[i].setId(i+liczbaZadan*3);
            tytulZadania[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,height/30);
            tytulZadania[i].setY(tempy+25+belkaZPrzyciskami);
            tytulZadania[i].setX(width/3);
            tytulZadania[i].setWidth(2*width/3-50);
            tytulZadania[i].setText(Html.fromHtml(model.sciezka.dajTytulZadania(i)));
            tytulZadania[i].setTextColor(Color.BLACK);
            ll.addView(tytulZadania[i]);


            trescZadania[i]=new TextView(this);
            trescZadania[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            trescZadania[i].setId(i+liczbaZadan*4);
            trescZadania[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,height/40);
            trescZadania[i].setY(tempy+225+belkaZPrzyciskami);
            trescZadania[i].setX(width/3);
            trescZadania[i].setWidth(2*width/3-50);
            trescZadania[i].setText(Html.fromHtml(model.sciezka.dajOpisZadania(i)));
            ll.addView(trescZadania[i]);

            tempy+=step+25;

            zacznijZadanie[i].setOnLongClickListener(this);
        }
        ocena[0].setOnLongClickListener(prezenter);
    }

    public void rePaintGUI()
    {
        AbsoluteLayout ll=(AbsoluteLayout) findViewById(R.id.ll);
        ll.removeAllViews();
        paintGUI();
    }

    @Override
    protected void onActivityResult(int reqID, int resC, Intent ii)
    {
        prezenter.ocenZadanie(reqID, resC, ii);
    }

    @Override
    public boolean onLongClick(View v)
    {
        Toast.makeText(v.getContext(),"Rozwiązano zadanie: "+v.getId(),Toast.LENGTH_SHORT).show();
        model.statystyki.rozwiazanoZadanie(v.getId(),40,true, 10000);
        model.postep.aktualizuj(model.statystyki,v.getId());
        model.postep.aktualizujOsiagniecia(model, model.statystyki,v.getId(),true);
        model.zapiszDane();
        rePaintGUI();
        return true;
    }
}
