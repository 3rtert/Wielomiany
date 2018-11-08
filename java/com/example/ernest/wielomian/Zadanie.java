package com.example.ernest.wielomian;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Zadanie extends AppCompatActivity
{
    int punkty;
    Intent intencja;
    String trescPodpowiedzi="";
    String trescRozwiazania="";
    TextView tytul, tresc, funkcja;
    TextView pytanie1,pytanie2,pytanie3;
    EditText odpowiedz1, odpowiedz2, odpowiedz3;
    Button zakonczZadanie, jakRozwiazac, podpowiedz;
    int numerZadania;
    boolean czyPodpowiedzOdblokowana;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadanie);

        tytul=(TextView) findViewById(R.id.textView8);
        tresc=(TextView) findViewById(R.id.textView12);
        funkcja=(TextView) findViewById(R.id.textView13);
        pytanie1=(TextView) findViewById(R.id.textView);
        pytanie2=(TextView) findViewById(R.id.textView2);
        pytanie3=(TextView) findViewById(R.id.textView3);
        odpowiedz1=(EditText) findViewById(R.id.editText);
        odpowiedz2=(EditText) findViewById(R.id.editText2);
        odpowiedz3=(EditText) findViewById(R.id.editText3);
        zakonczZadanie=(Button) findViewById(R.id.button4);
        jakRozwiazac=(Button) findViewById(R.id.button);
        podpowiedz=(Button) findViewById(R.id.button3);

        intencja=getIntent();

        tytul.setText(Html.fromHtml(intencja.getStringExtra("Tytul")));
        tresc.setText(Html.fromHtml(intencja.getStringExtra("Tresc")));
        funkcja.setText(Html.fromHtml(intencja.getStringExtra("Funkcja")));
        pytanie1.setText(Html.fromHtml(intencja.getStringExtra("Pytanie1")));
        pytanie2.setText(Html.fromHtml(intencja.getStringExtra("Pytanie2")));
        pytanie3.setText(Html.fromHtml(intencja.getStringExtra("Pytanie3")));
        trescPodpowiedzi=intencja.getStringExtra("Podpowiedz");
        trescRozwiazania=intencja.getStringExtra("Rozwiazanie");
        punkty=intencja.getIntExtra("punkty",0);
        numerZadania=intencja.getIntExtra("numerZadania",0);
        czyPodpowiedzOdblokowana=intencja.getBooleanExtra("czyPodpowiedzOdblokowana",false);
    }

    synchronized public void zakonczZadanie(View view)
    {
        AlertDialog.Builder komunikat=new AlertDialog.Builder(this);
        komunikat.setTitle("Zakonczenie zadania");
        komunikat.setMessage("Czy na pewno chcesz zakończyć zadanie?");
        komunikat.setCancelable(true);
        komunikat.setPositiveButton("Tak",new Dialog.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int whichButton)
            {
                Bundle rozwiazaniaUcznia = new Bundle();
                rozwiazaniaUcznia.putString("1",odpowiedz1.getText().toString());
                rozwiazaniaUcznia.putString("2",odpowiedz2.getText().toString());
                rozwiazaniaUcznia.putString("3",odpowiedz3.getText().toString());
                rozwiazaniaUcznia.putInt("punkty",punkty);
                rozwiazaniaUcznia.putBoolean("czyPodpowiedzOdblokowana",czyPodpowiedzOdblokowana);
                intencja.putExtras(rozwiazaniaUcznia);
                setResult(Activity.RESULT_OK, intencja);
                finish();
            }
        });
        komunikat.setNegativeButton("Nie",new Dialog.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {}
        });
        komunikat.create().show();
    }

    public void jakRozwiazac(View view)
    {
        if(numerZadania!=0 && !czyPodpowiedzOdblokowana)
        {
            if(punkty>=50*numerZadania+50)
            {
                AlertDialog.Builder komunikat=new AlertDialog.Builder(this);
                komunikat.setTitle("Sposób rozwiązania");
                komunikat.setMessage(Html.fromHtml("Sposób rozwiązania kosztuje <b>"+(50*numerZadania+50)+"</b> punktów.<br>" +
                        "Posiadasz obecnie <b>"+punkty+"</b> punktów.<br><br>" +
                        "Czy chcesz uzyskać sposób rozwiązania w zamian za punkty?"));
                komunikat.setCancelable(true);
                komunikat.setPositiveButton("Tak",new Dialog.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        punkty=punkty-(50*numerZadania+50);
                        czyPodpowiedzOdblokowana=true;
                        pokazRozwiazanie();
                    }
                });
                komunikat.setNegativeButton("Nie",new Dialog.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {}
                });
                komunikat.create().show();
            }
            else
            {
                AlertDialog.Builder komunikat=new AlertDialog.Builder(this);
                komunikat.setTitle("Sposób rozwiązania");
                komunikat.setMessage(Html.fromHtml("Sposób rozwiązania kosztuje <b>"+(50*numerZadania+50)+"</b> punktów.<br>" +
                        "Posiadasz obecnie tylko <b>"+punkty+"</b> punktów."));
                komunikat.setCancelable(true);
                komunikat.setPositiveButton("Ok",new Dialog.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {}
                });
                komunikat.create().show();
            }
        }
        else
        {
            pokazRozwiazanie();
        }
    }

    public void podpowiedz(View view)
    {
        pokazPodpowiedz();
    }

    private void pokazRozwiazanie()
    {
        AlertDialog.Builder komunikat=new AlertDialog.Builder(this);
        komunikat.setTitle("Sposób rozwiązania");
        komunikat.setMessage(Html.fromHtml(trescRozwiazania));
        komunikat.setCancelable(true);
        komunikat.setPositiveButton("Ok",new Dialog.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {}
        });
        komunikat.create().show();
    }

    private void pokazPodpowiedz()
    {
        AlertDialog.Builder komunikat=new AlertDialog.Builder(this);
        komunikat.setTitle("Podpowiedż");
        komunikat.setMessage(Html.fromHtml(trescPodpowiedzi));
        komunikat.setCancelable(true);
        komunikat.setPositiveButton("Ok",new Dialog.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {}
        });
        komunikat.create().show();
    }

    @Override
    public void onBackPressed()
    {
        Bundle rozwiazaniaUcznia = new Bundle();
        rozwiazaniaUcznia.putString("1",odpowiedz1.getText().toString());
        rozwiazaniaUcznia.putString("2",odpowiedz2.getText().toString());
        rozwiazaniaUcznia.putString("3",odpowiedz3.getText().toString());
        rozwiazaniaUcznia.putInt("punkty",punkty);
        rozwiazaniaUcznia.putBoolean("czyPodpowiedzOdblokowana",czyPodpowiedzOdblokowana);
        intencja.putExtras(rozwiazaniaUcznia);
        setResult(Activity.RESULT_OK, intencja);
        super.onBackPressed();
    }
}
