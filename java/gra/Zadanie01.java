package gra;

import java.util.ArrayList;
import java.util.Random;

public class Zadanie01 implements Zadanie
{
    final int MAKSYMALNY_WSPOLCZYNNIK=5;
    final int MINIMALNY_WSPOCZYNNIK=-3;
    final int MAKSYMALNY_ARGUMENT=3;
    final int MINIMALNY_ARGUMENT=-3;
    final int LICZBA_PYTAN=3;
    final int PUNKTY=10;
    final String TYTUL="1. Stopień wielomianu";
    final String PODPOWIEDZ="Zwróć uwagę na wartości potęg przy <b>x</b>. Podstaw za <b>x</b> jakąś wartość.";
    final String WYJASNIENIE="Stopniem wielomianu nazywamy najwyższą potęgę <b>x</b>, dla której współczynnik jest niezerowy. " +
            "Dla przykładu funkcja kwadratowa <b>f(x) = 7x<small><sup>2</sup></small>-3x+2</b> jest stopnia drugiego. Aby obliczyć <b>wartość wielomianu</b> dla zadanego argumentu, " +
            "należy podstawić tą liczbę zamiast <b>x</b> we wzorze wielomianu. Otrzymana wartość jest wartością wielomianu. <b>Wyrazem wolnym</b> nazywamy wartośc współczynnika dla wyrazu, " +
            "który stoi bez <b>x</b> w wielomianie uporządkowanym. To znaczy takim, w których nie występują 2 razy takie same potęgi <b>x</b>.";
    final String OPIS="Wyznaczanie stopnia wielomianu, oraz jego wartości.";

    String tresc="Podaj stopień wielomianu i wartość wyrazu wolnego, oraz oblicz wartość wielomianu dla x=";
    String[] pytania=new String[LICZBA_PYTAN];
    String[] rozwiazania=new String[LICZBA_PYTAN];


    int stopienWielomianu;
    int[] wspolczynniki;
    int[] potega;
    String wielomian="";
    int argument;

    Zadanie01()
    {
        noweZadanie();
    }

    void noweZadanie()
    {
        //inicjacja

        Random random=new Random();
        stopienWielomianu=random.nextInt(4)+2;
        wspolczynniki=new int[stopienWielomianu+1];
        potega=new int[stopienWielomianu+1];
        pytania[0]="Stopień wielomianu: ";
        pytania[1]="Wyraz wolny: ";
        pytania[2]="Wartość wielomianu: ";

        //generowanie wielomianu

        ArrayList<Integer> stopnieWielomianu = new ArrayList<Integer>();
        for(int i=0;i<stopienWielomianu+1;i++)
            stopnieWielomianu.add(i);
        for(int i=0;i<stopienWielomianu+1;i++)
            potega[i]=stopnieWielomianu.remove(random.nextInt(stopnieWielomianu.size()));

        for(int i=0;i<wspolczynniki.length;i++)
        {
            if(random.nextBoolean()==true)
                wspolczynniki[i]=random.nextInt(MAKSYMALNY_WSPOLCZYNNIK+ Math.abs(MINIMALNY_WSPOCZYNNIK)+1)- Math.abs(MINIMALNY_WSPOCZYNNIK);
            else
                wspolczynniki[i]=0;

            if(potega[i]==stopienWielomianu)
                wspolczynniki[i]=1;
        }


        boolean czyBylPierwszy=false;
        wielomian="f(x) = ";
        for(int i=0;i<wspolczynniki.length;i++)
        {
            String kolejnyElement="";
            if(czyBylPierwszy && wspolczynniki[i]>0)
                wielomian+="+";
            if(wspolczynniki[i]!=0)
            {
                czyBylPierwszy=true;
                if(wspolczynniki[i]!=1)
                {
                    if(potega[i]==0 || wspolczynniki[i] != -1)
                    {
                        kolejnyElement += wspolczynniki[i];
                    }
                    else
                    {
                        kolejnyElement += "-";
                    }
                }
                if(potega[i]!=0)
                {
                    if(potega[i]==1)
                        kolejnyElement+="x";
                    else
                        kolejnyElement+="x<small><sup>"+potega[i]+"</sup></small>";
                }
                if(wspolczynniki[i]==1 && potega[i]==0)
                    kolejnyElement+=1;
            }
            wielomian+=kolejnyElement;
        }

        argument=random.nextInt(MAKSYMALNY_ARGUMENT+1+ Math.abs(MINIMALNY_ARGUMENT))- Math.abs(MINIMALNY_ARGUMENT);
        tresc+=argument+".";

        //obliczanie rozwiazan

        rozwiazania[0]= Integer.toString(stopienWielomianu);
        for(int i=0;i<wspolczynniki.length;i++)
        {
            if(potega[i]==0)
                rozwiazania[1]= Integer.toString(wspolczynniki[i]);
        }

        int wartoscWielomianu=0;
        for(int i=0;i<wspolczynniki.length;i++)
        {
            wartoscWielomianu+=wspolczynniki[i]*(Math.pow(argument,potega[i]));
        }
        rozwiazania[2]= Integer.toString(wartoscWielomianu);
    }

    @Override
    public void generuj()
    {
        Zadanie01 nowe=new Zadanie01();
        this.tresc=nowe.tresc;
        this.pytania=nowe.pytania;
        this.rozwiazania=nowe.rozwiazania;
        this.stopienWielomianu=nowe.stopienWielomianu;
        this.wspolczynniki=nowe.wspolczynniki;
        this.potega=nowe.potega;
        this.wielomian=nowe.wielomian;
        this.argument=nowe.argument;
    }
    @Override
    public String dajTytul()
    {
        return TYTUL;
    }
    @Override
    public String dajTresc()
    {
        return tresc;
    }
    @Override
    public String dajFunkcje()
    {
        return wielomian;
    }
    @Override
    public String[] dajRozwiazania()
    {
        return rozwiazania;
    }
    @Override
    public int dajPunkty()
    {
        return PUNKTY;
    }
    @Override
    public String[] dajPytania()
    {
        return pytania;
    }
    @Override
    public int dajLiczbePytan()
    {
        return LICZBA_PYTAN;
    }
    @Override
    public String dajPodpowiedz()
    {
        return PODPOWIEDZ;
    }
    @Override
    public String dajWyjasnienie()
    {
        return WYJASNIENIE;
    }
    @Override
    public String dajOpis()
    {
        return OPIS;
    }
}

