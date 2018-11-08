package com.example.ernest.wielomian;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import gra.*;

/**
 * Created by Ernest on 09.11.2017.
 */

public class Prezenter implements View.OnClickListener, View.OnLongClickListener
{
    AppCompatActivity widok;
    Gra model;
    int numerZadania;
    Date czas;


    public Prezenter(AppCompatActivity widok, Gra model)
    {
        this.widok=widok;
        this.model=model;
    }

    public void ocenZadanie(int reqID, int resC, Intent ii)
    {
        if(resC == Activity.RESULT_OK && reqID==1)
        {
            long poswieconyCzas=(new Date()).getTime()-czas.getTime();
            Bundle rezultat=ii.getExtras();
            String[] odpowiedzi=new String[3];
            model.statystyki.punkty=rezultat.getInt("punkty");
            model.postep.czyPodpowiedzOdblokowana[numerZadania]=rezultat.getBoolean("czyPodpowiedzOdblokowana");
            odpowiedzi[0]=rezultat.getString("1");
            odpowiedzi[1]=rezultat.getString("2");
            odpowiedzi[2]=rezultat.getString("3");
            String informacjaZwrotna=model.sprawdzZadanie(numerZadania,odpowiedzi,poswieconyCzas);

            AlertDialog.Builder komunikat=new AlertDialog.Builder(widok);
            komunikat.setTitle("Ocena");
            komunikat.setMessage(Html.fromHtml(informacjaZwrotna));
            komunikat.setCancelable(true);
            komunikat.setPositiveButton("Ok",new Dialog.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {}
            });
            komunikat.create().show();
            ((GlownaSciezka)widok).rePaintGUI();
        }
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==1000+1)
        {
            Intent intencja =new Intent(widok,Postep.class);
            intencja.putExtra("RozwiazanoZadanTymRazem", Integer.toString(model.statystyki.liczbaRozwiazanychZadanPrzyObecnejSesji));
            intencja.putExtra("CzasTymRazem", Long.toString(model.statystyki.dajCzasSpedzonyNaZadaniachWObecnejSesji()));
            intencja.putExtra("punkty",Integer.toString(model.statystyki.punkty));
            intencja.putExtra("doswiadczenie",Integer.toString(model.statystyki.doswiadczenie));
            intencja.putExtra("liczbaZadan",Integer.toString(model.dajLiczbeZadan()));
            for(int i=0;i<model.dajLiczbeZadan();i++)
            {
                intencja.putExtra("RozwiazanoZadan"+i, Integer.toString(model.statystyki.liczbaPoprawnychRozwiazanZadania[i]));
                intencja.putExtra("CzasSpedzonyNaZadaniu"+i, Long.toString(model.statystyki.spedzonyCzasNaZadaniu[i]));
                intencja.putExtra("LiczbaPodejsc"+i,Integer.toString(model.statystyki.liczbaPodejscDoZadania[i]));

                intencja.putExtra("postepRozwiazywaniaZadania"+i,Integer.toString(model.postep.postepRozwiazywaniaZadania[i]));
                intencja.putExtra("czyZadanieZaliczone"+i,Boolean.toString(model.postep.czyZadanieZaliczone[i]));
                intencja.putExtra("czyZadanieOdblokowane"+i,Boolean.toString(model.postep.czyZadanieOdblokowane[i]));

                intencja.putExtra("tytulZadania"+i,model.sciezka.dajTytulZadania(i));
            }
            widok.startActivity(intencja);
        }
        else if(v.getId()==1000+2)
        {
            Intent intencja =new Intent(widok,Osiagniecia.class);
            intencja.putExtra("liczbaOsiagniec",model.postep.LICZBA_OSIAGNIEC);
            for(int i=0;i<model.postep.LICZBA_OSIAGNIEC;i++)
            {
                intencja.putExtra("trescOsiagniecia"+i,model.postep.tekstOsiagniecia[i]);
                intencja.putExtra("czyOdblokowane"+i,model.postep.czyOdblokowane[i]);
                intencja.putExtra("celOsiagniecia"+i,model.postep.celOsiagniecia[i]);
                System.out.println(model.postep.celOsiagniecia[i]);
                intencja.putExtra("postepOsiagniecia"+i,model.postep.postepOsiagniecia[i]);
            }



            widok.startActivity(intencja);
        }
        else
        {
            //Toast.makeText(v.getContext(),"Test"+v.getId(),Toast.LENGTH_SHORT).show();
            numerZadania=v.getId();
            if(model.postep.czyZadanieOdblokowane[numerZadania])
            {
                czas=new Date();
                model.generujZadanie(numerZadania);
                int liczbaPytan=model.sciezka.dajLiczbePytanZadania(numerZadania);
                Intent intencja =new Intent(widok,Zadanie.class);
                intencja.putExtra("Tytul",model.sciezka.dajTytulZadania(numerZadania));
                intencja.putExtra("Tresc",model.sciezka.dajTrescZadania(numerZadania));
                intencja.putExtra("Funkcja",model.sciezka.dajFunkcjeZadania(numerZadania));
                String[] pytania=model.sciezka.dajPytaniaZadania(numerZadania);
                for(int i=0;i<liczbaPytan;i++)
                {
                    intencja.putExtra("Pytanie"+(i+1),pytania[i]);
                }
                intencja.putExtra("Podpowiedz",model.sciezka.dajPodpowiedzZadania(numerZadania));
                intencja.putExtra("Rozwiazanie",model.sciezka.dajWyjasnienieZadania(numerZadania));
                intencja.putExtra("punkty",model.statystyki.punkty);
                intencja.putExtra("numerZadania",numerZadania);
                intencja.putExtra("czyPodpowiedzOdblokowana",model.postep.czyPodpowiedzOdblokowana[numerZadania]);
                widok.startActivityForResult(intencja,1);
            }
            else
            {
                AlertDialog.Builder komunikat=new AlertDialog.Builder(widok);
                komunikat.setTitle("Za wcześnie");
                komunikat.setMessage(Html.fromHtml("Nie odblokowano jeszcze tego zadania. Rozwiąż najpierw poprzednie."));
                komunikat.setCancelable(true);
                komunikat.setPositiveButton("Ok",new Dialog.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {}
                });
                komunikat.create().show();
            }
        }
    }

    @Override
    public boolean onLongClick(View v)
    {
        Toast.makeText(v.getContext(),"Reset",Toast.LENGTH_SHORT).show();
        model.reset(widok);
        ((GlownaSciezka)widok).rePaintGUI();
        return true;
    }
}