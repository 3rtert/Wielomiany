package gra;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ernest on 11.11.2017.
 */

public class Zadanie06 implements Zadanie
{
    final int MAKSYMALNY_WSPOLCZYNNIK=5;
    final int MINIMALNY_WSPOCZYNNIK=-5;
    final int MAKSYMALNY_ARGUMENT=3;
    final int LICZBA_PYTAN=3;
    final int PUNKTY=20;
    final String TYTUL="6. Trójmian kwadratowy";
    final String PODPOWIEDZ="Oblicz deltę w pierwszej kolejności ze wzoru, a następnie za jej pomocą wyznacz miejsca zerowe.";
    final String WYJASNIENIE="Dla wielomianu <b>ax<small><sup>2</sup></small> + bx + c</b> wartość delty wynosi: <br>"
            +" <b>b<small><sup>2</sup></small>-4ac</b><br>"
            +"Należy obliczyć pierwiastek kwadratowy (jeżeli taki istnieje) delty. Otrzymamy 2 wartości i obie (delta*) należy podstawić do kolejnego wzoru: <br>"
            +" <b>x = (-b + delta*)/2a </b><br>"
            +"Otrzymamy wtedy 2 wyniki. Będą to miejsca zerowe tego trójmianu kwadratowego.";
    final String OPIS="Wyznaczanie miejsc zerowych wielomianu drugiego stopnia.";

    String tresc="Podaj miejsca zerowe w kolejności rosnącej i wyznacz deltę podanego trómianu kwadratowego.";
    String[] pytania=new String[LICZBA_PYTAN];
    String[] rozwiazania=new String[LICZBA_PYTAN];


    int stopienWielomianu1;
    int[] wspolczynniki1;
    int[] potega1;
    String wielomian1="";

    Zadanie06()
    {
        noweZadanie();
    }

    void noweZadanie()
    {
        //inicjacja

        Random random=new Random();

        stopienWielomianu1=2;
        wspolczynniki1=new int[stopienWielomianu1+1];
        potega1=new int[stopienWielomianu1+1];
        potega1[0]=2;
        potega1[1]=1;
        potega1[2]=0;

        pytania[0]="Mniejsze miejsce zerowe: ";
        pytania[1]="Większe miejsce zerowe: ";
        pytania[2]="Wartość delty: ";



        int a=random.nextInt(3)+1;
        int mniejszeMiejsceZerowe=random.nextInt(5)-5;
        int wiekszeMiejsceZerowe=random.nextInt(5);

        wspolczynniki1[0]=a;
        wspolczynniki1[1]=0-a*(mniejszeMiejsceZerowe+wiekszeMiejsceZerowe);
        wspolczynniki1[2]=a*mniejszeMiejsceZerowe*wiekszeMiejsceZerowe;


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


        //obliczanie rozwiazan

        rozwiazania[0]=""+mniejszeMiejsceZerowe;
        rozwiazania[1]=""+wiekszeMiejsceZerowe;

        int delta=wspolczynniki1[1]*wspolczynniki1[1]-4*a*wspolczynniki1[2];
        rozwiazania[2]=""+delta;
    }

    @Override
    public void generuj()
    {
        Zadanie06 nowe=new Zadanie06();
        this.tresc=nowe.tresc;
        this.pytania=nowe.pytania;
        this.rozwiazania=nowe.rozwiazania;
        this.stopienWielomianu1=nowe.stopienWielomianu1;
        this.wspolczynniki1=nowe.wspolczynniki1;
        this.potega1=nowe.potega1;
        this.wielomian1=nowe.wielomian1;
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
        return wielomian1;
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
