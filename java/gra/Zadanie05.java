package gra;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ernest on 11.11.2017.
 */

public class Zadanie05 implements Zadanie
{
    final int MAKSYMALNY_WSPOLCZYNNIK=3;
    final int MINIMALNY_WSPOCZYNNIK=-3;
    final int MAKSYMALNY_ARGUMENT=3;
    final int LICZBA_PYTAN=3;
    final int PUNKTY=20;
    final String TYTUL="5. Mnożenie wielomianów (2/2)";
    final String PODPOWIEDZ="Wymnóż przez siebie oba wielomiany tak samo jak się wymnaża nawiasy. Pamiętaj o współczynnikach stojących przy wielomianach.";
    final String WYJASNIENIE="<b>Stopniem wielomianu</b> <b>w(x)</b>, który jest iloczynem wielomianów <b>f(x)</b> i <b>g(x)</b> jest suma stopni tych wielomianów. "
            +"Wystarczy wymnożyć wielomiany i sprawdzić jego stopień żeby to potwierdzić. Żeby wyznaczyć wielomian <b>w(x)</b>, należy wymnożyć oba wielomiany mnożąc przez siebie kolejne jednomiany. "
            +"Każdy jednomian z pierwszego wielomianu należy wymnożyć przez jakżdy jednomian z drugiego wielomianu. Należy przy tym pamiętać o wynmożeniu wielomianu przez wsspółczynnik stojący przed nim. Dla przykładu: <br>"
            +"<b> 2*(2x + 3)*(-x<small><sup>2</sup></small> + x) = -4x<small><sup>3</sup></small> + 4x<small><sup>2</sup></small> - 6x<small><sup>2</sup></small> + 6x <br></b>"
            +"Następnie wystarczy uporządkować wielomian i otrzymamy: <br>"
            +"<b> -4x<small><sup>3</sup></small> - 2x<small><sup>2</sup></small> + 6x</b>";
    final String OPIS="Wyznaczanie wielomianu poprzez wymnażanie innym wielomianów";

    String tresc="Podaj stopień wielomianu <b>w(x)</b> i wartości podanych współczynników wielomianu <b>w(x)</b>, jeżeli: <b>w(x) = ";
    String[] pytania=new String[LICZBA_PYTAN];
    String[] rozwiazania=new String[LICZBA_PYTAN];


    int stopienWielomianu1;
    int stopienWielomianu2=2;
    int[] wspolczynniki1;
    int[] potega1;
    int[] wspolczynniki2;
    int[] potega2;
    int waga1;
    int waga2;
    String wielomian1="";
    String wielomian2="";

    Zadanie05()
    {
        noweZadanie();
    }

    void noweZadanie()
    {
        //inicjacja

        Random random=new Random();

        stopienWielomianu1=random.nextInt(2)+2;
        wspolczynniki1=new int[stopienWielomianu1+1];
        potega1=new int[stopienWielomianu1+1];
        wspolczynniki2=new int[stopienWielomianu2+1];
        potega2=new int[stopienWielomianu2+1];
        pytania[0]="Stopień wielomianu w(x): ";
        pytania[1]="Współczynnik a<small><small><small>3</small></small></small>: ";
        pytania[2]="Współczynnik a<small><small><small>1</small></small></small>: ";

        waga1=random.nextInt(5)-2;
        if(waga1==0)
            waga1=1;
        waga2=random.nextInt(5)-2;
        if(waga2==0)
            waga2=1;

        tresc+=waga1+"f(x) * "+(waga2<0?"("+waga2:waga2)+"g(x)"+(waga2<0?")":"")+"</b>.";
        //generowanie wielomianu

        potega2[0]=2;
        potega2[1]=1;
        potega2[2]=0;

        wspolczynniki2[0]=random.nextInt(2)+1;
        wspolczynniki2[1]=0;
        wspolczynniki2[2]=random.nextInt(5)-2;



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

        rozwiazania[0]=""+(stopienWielomianu1+stopienWielomianu2);

        int a1=0;
        for(int i=0;i<wspolczynniki1.length;i++)
        {
            if(potega1[i]==1)
                a1=wspolczynniki1[i]*wspolczynniki2[2];
        }
        rozwiazania[2]=Integer.toString(a1);


        int a3=0;
        for(int i=0;i<wspolczynniki1.length;i++)
        {
            if(potega1[i]==3)
                a3=wspolczynniki1[i]*wspolczynniki2[2]*waga1*waga2;
        }
        for(int i=0;i<wspolczynniki1.length;i++)
        {
            if(potega1[i]==1)
                a3+=wspolczynniki1[i]*wspolczynniki2[0]*waga1*waga2;
        }
        rozwiazania[1]=Integer.toString(a3);
    }

    @Override
    public void generuj()
    {
        Zadanie05 nowe=new Zadanie05();
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
        this.waga1=nowe.waga1;
        this.waga2=nowe.waga2;
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
