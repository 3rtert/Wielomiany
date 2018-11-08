package gra;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ernest on 11.11.2017.
 */

public class Zadanie02 implements Zadanie
{
    final int MAKSYMALNY_WSPOLCZYNNIK=5;
    final int MINIMALNY_WSPOCZYNNIK=-3;
    final int MAKSYMALNY_ARGUMENT=3;
    final int MINIMALNY_ARGUMENT=-3;
    final int LICZBA_PYTAN=3;
    final int PUNKTY=15;
    final String TYTUL="2. Dodawanie wielomianów (1/2)";
    final String PODPOWIEDZ="Dodaj do siebie oba wielomiany i uporządkuj jednomiany.";
    final String WYJASNIENIE="Współczynnik <b>a<small><small>3</small></small></b> to nic innego jak współczynnik stojący przy <b>x<sup><small>3</small></sup></b>. " +
            "Aby wyznaczyć ten współczynnik dla wielomianu wynikowego wystarczy dodać współczynniki stojące przy <b>x<sup><small>3</small></sup></b> z obu wielomianów. " +
            "Analogicznie przy odejmowaniu: należy je odjąć. Po wyznaczeniu wszystkich współczynników wynikowego wielomianu, należy wyznaczyć jego stopień. (patrz zadanie 1. Stopień wielomianu).";
    final String OPIS="Wyznaczanie wielomianu poprzez dodawnie lub odejmowanie innych wielomianów";

    String tresc="Podaj stopień wielomianu <b>w(x)</b> i wartości podanych współczynników wielomianu <b>w(x)</b>, jeżeli: <b>w(x)=";
    String[] pytania=new String[LICZBA_PYTAN];
    String[] rozwiazania=new String[LICZBA_PYTAN];


    int stopienWielomianu1;
    int stopienWielomianu2;
    int[] wspolczynniki1;
    int[] potega1;
    int[] wspolczynniki2;
    int[] potega2;
    String wielomian1="";
    String wielomian2="";
    boolean czyDodajemy;

    Zadanie02()
    {
        noweZadanie();
    }

    void noweZadanie()
    {
        //inicjacja

        Random random=new Random();
        czyDodajemy=random.nextBoolean();
        if(czyDodajemy)
            tresc+=" f(x) + g(x)</b>.";
        else
            tresc+=" f(x) - g(x)</b>.";
        stopienWielomianu1=random.nextInt(3)+2;
        stopienWielomianu2=random.nextInt(3)+2;
        wspolczynniki1=new int[stopienWielomianu1+1];
        potega1=new int[stopienWielomianu1+1];
        wspolczynniki2=new int[stopienWielomianu2+1];
        potega2=new int[stopienWielomianu2+1];
        pytania[0]="Stopień wielomianu w(x): ";
        pytania[1]="Współczynnik a<small><small><small>2</small></small></small>: ";
        pytania[2]="Współczynnik a<small><small><small>0</small></small></small>: ";

        //generowanie wielomianu

        ArrayList<Integer> stopnieWielomianu = new ArrayList<Integer>();
        for(int i=0;i<stopienWielomianu1+1;i++)
            stopnieWielomianu.add(i);
        for(int i=0;i<stopienWielomianu1+1;i++)
            potega1[i]=stopnieWielomianu.remove(random.nextInt(stopnieWielomianu.size()));

        for(int i=0;i<wspolczynniki1.length;i++)
        {
            if(random.nextBoolean()==true)
                wspolczynniki1[i]=random.nextInt(MAKSYMALNY_WSPOLCZYNNIK+ Math.abs(MINIMALNY_WSPOCZYNNIK)+1)- Math.abs(MINIMALNY_WSPOCZYNNIK);
            else
                wspolczynniki1[i]=0;

            if(potega1[i]==stopienWielomianu1)
                wspolczynniki1[i]=1;
        }

        stopnieWielomianu = new ArrayList<Integer>();
        for(int i=0;i<stopienWielomianu2+1;i++)
            stopnieWielomianu.add(i);
        for(int i=0;i<stopienWielomianu2+1;i++)
            potega2[i]=stopnieWielomianu.remove(random.nextInt(stopnieWielomianu.size()));

        for(int i=0;i<wspolczynniki2.length;i++)
        {
            if(random.nextBoolean()==true)
                wspolczynniki2[i]=random.nextInt(MAKSYMALNY_WSPOLCZYNNIK+ Math.abs(MINIMALNY_WSPOCZYNNIK)+1)- Math.abs(MINIMALNY_WSPOCZYNNIK);
            else
                wspolczynniki2[i]=0;

            if(potega2[i]==stopienWielomianu2)
                wspolczynniki2[i]=1;
        }

        boolean czyBylPierwszy=false;
        wielomian1="f(x) = ";
        for(int i=0;i<wspolczynniki1.length;i++)
        {
            String kolejnyElement="";
            if(czyBylPierwszy && wspolczynniki1[i]>0)
                wielomian1+="+";
            if(wspolczynniki1[i]!=0)
            {
                czyBylPierwszy=true;
                if(wspolczynniki1[i]!=1)
                {
                    if(potega1[i]==0 || wspolczynniki1[i] != -1)
                    {
                        kolejnyElement += wspolczynniki1[i];
                    }
                    else
                    {
                        kolejnyElement += "-";
                    }
                }
                if(potega1[i]!=0)
                {
                    if(potega1[i]==1)
                        kolejnyElement+="x";
                    else
                        kolejnyElement+="x<small><sup>"+potega1[i]+"</sup></small>";
                }
                if(wspolczynniki1[i]==1 && potega1[i]==0)
                    kolejnyElement+=1;
            }
            wielomian1+=kolejnyElement;
        }

        czyBylPierwszy=false;
        wielomian2="g(x) = ";
        for(int i=0;i<wspolczynniki2.length;i++)
        {
            String kolejnyElement="";
            if(czyBylPierwszy && wspolczynniki2[i]>0)
                wielomian2+="+";
            if(wspolczynniki2[i]!=0)
            {
                czyBylPierwszy=true;
                if(wspolczynniki2[i]!=1)
                {
                    if(potega2[i]==0 || wspolczynniki2[i] != -1)
                    {
                        kolejnyElement += wspolczynniki2[i];
                    }
                    else
                    {
                        kolejnyElement += "-";
                    }
                }
                if(potega2[i]!=0)
                {
                    if(potega2[i]==1)
                        kolejnyElement+="x";
                    else
                        kolejnyElement+="x<small><sup>"+potega2[i]+"</sup></small>";
                }
                if(wspolczynniki2[i]==1 && potega2[i]==0)
                    kolejnyElement+=1;
            }
            wielomian2+=kolejnyElement;
        }

        //obliczanie rozwiazan



        int wspolczynnikPrzyNajwiekszejPotedze=0;
        int j;
        for(j=Math.max(stopienWielomianu1,stopienWielomianu2);j>0 && wspolczynnikPrzyNajwiekszejPotedze==0;j--)
        {
            for(int i=0;i<wspolczynniki1.length;i++)
            {
                if(potega1[i]==j)
                    wspolczynnikPrzyNajwiekszejPotedze=wspolczynniki1[i];
            }
            for(int i=0;i<wspolczynniki2.length;i++)
            {
                if (potega2[i] == j) {
                    if (czyDodajemy)
                        wspolczynnikPrzyNajwiekszejPotedze += wspolczynniki2[i];
                    else
                        wspolczynnikPrzyNajwiekszejPotedze -= wspolczynniki2[i];
                }
            }
        }
        rozwiazania[0]=Integer.toString(j+1);


        int a0=0;
        for(int i=0;i<wspolczynniki1.length;i++)
        {
            if(potega1[i]==0)
                a0=wspolczynniki1[i];
        }
        for(int i=0;i<wspolczynniki2.length;i++)
        {
            if(potega2[i]==0)
            {
                if(czyDodajemy)
                    a0+=wspolczynniki2[i];
                else
                    a0-=wspolczynniki2[i];
            }
        }
        rozwiazania[2]=Integer.toString(a0);

        int a2=0;
        for(int i=0;i<wspolczynniki1.length;i++)
        {
            if(potega1[i]==2)
                a2=wspolczynniki1[i];
        }
        for(int i=0;i<wspolczynniki2.length;i++)
        {
            if(potega2[i]==2)
            {
                if(czyDodajemy)
                    a2+=wspolczynniki2[i];
                else
                    a2-=wspolczynniki2[i];
            }

        }
        rozwiazania[1]=Integer.toString(a2);
    }

    @Override
    public void generuj()
    {
        Zadanie02 nowe=new Zadanie02();
        this.tresc=nowe.tresc;
        this.pytania=nowe.pytania;
        this.rozwiazania=nowe.rozwiazania;
        this.stopienWielomianu1=nowe.stopienWielomianu1;
        this.stopienWielomianu2=nowe.stopienWielomianu2;
        this.wspolczynniki1=nowe.wspolczynniki1;
        this.potega1=nowe.potega1;
        this.wielomian1=nowe.wielomian1;
        this.wspolczynniki2=nowe.wspolczynniki2;
        this.potega2=nowe.potega2;
        this.wielomian2=nowe.wielomian2;
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
        return wielomian1+"<br>"+wielomian2;
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
