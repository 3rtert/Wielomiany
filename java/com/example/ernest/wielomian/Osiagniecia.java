package com.example.ernest.wielomian;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Osiagniecia extends AppCompatActivity
{
    ProgressBar[] postepOsiagniecia;
    TextView[] trescOsiagniecia;

    Intent intencja;
    int liczbaOsiagniec;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osiagniecia);

        intencja=getIntent();

        liczbaOsiagniec=intencja.getIntExtra("liczbaOsiagniec",0);
        postepOsiagniecia=new ProgressBar[liczbaOsiagniec];
        trescOsiagniecia=new TextView[liczbaOsiagniec];

        paintGUI();
    }

    public void paintGUI()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int step=height/15;

        AbsoluteLayout ll=(AbsoluteLayout) findViewById(R.id.ll3);
        ll.setMinimumHeight(liczbaOsiagniec*(step+30));
        int tempy=0;

        for(int i=0;i<liczbaOsiagniec;i++)
        {
            trescOsiagniecia[i]=new TextView(this);
            trescOsiagniecia[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            trescOsiagniecia[i].setText(Html.fromHtml(intencja.getStringExtra("trescOsiagniecia"+i)+(intencja.getBooleanExtra("czyOdblokowane"+i,false)?" <b>✔</b>":" ("+intencja.getIntExtra("postepOsiagniecia"+i,0)+"/"+intencja.getIntExtra("celOsiagniecia"+i,0)+")")));
            trescOsiagniecia[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,height/35);
            trescOsiagniecia[i].setX(15);
            trescOsiagniecia[i].setY(tempy+25);
            trescOsiagniecia[i].setTextColor(Color.BLACK);

            ll.addView(trescOsiagniecia[i]);

            postepOsiagniecia[i]= new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);
            postepOsiagniecia[i].setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            postepOsiagniecia[i].setMax(intencja.getIntExtra("celOsiagniecia"+i,0));
            postepOsiagniecia[i].setProgress(intencja.getIntExtra("postepOsiagniecia"+i,0));
            postepOsiagniecia[i].setX(0);
            postepOsiagniecia[i].setY(tempy+height/20);

            ll.addView(postepOsiagniecia[i]);
            tempy+=step+30;
        }


    }
}
