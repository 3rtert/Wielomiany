package gra;


import android.app.Activity;
import android.content.SharedPreferences;

public class Postep
{
    int ROZWIAZAN_POTRZEBNYCH_DO_ZALICZENIA=6; //10
    int ROZWIAZAN_WSZYSTKICH=20;
    int PROCENT_POTRZEBNY_NA_ODBLOKOWANIE_KOLEJNEGO_ZADANIA=15; //30

    SharedPreferences zapis;

    int liczbaZadan;
    public boolean[] czyZadanieZaliczone;
    public boolean[] czyZadanieOdblokowane;
    public int[] postepRozwiazywaniaZadania; //w procentach
    public boolean[] czyPodpowiedzOdblokowana;


    // osiagniecia

    public final int LICZBA_OSIAGNIEC=11;

    public boolean[] czyOdblokowane = new boolean[LICZBA_OSIAGNIEC];
    public int[] postepOsiagniecia = new int[LICZBA_OSIAGNIEC];
    public String[] tekstOsiagniecia = new String[LICZBA_OSIAGNIEC];
    public int[] celOsiagniecia = new int[LICZBA_OSIAGNIEC];


    Postep(int liczbaZadan)
    {
        this.liczbaZadan=liczbaZadan;
        czyZadanieZaliczone=new boolean[liczbaZadan];
        czyZadanieOdblokowane=new boolean[liczbaZadan];
        postepRozwiazywaniaZadania=new int[liczbaZadan];
        czyPodpowiedzOdblokowana=new boolean[liczbaZadan];
        for(int i=0;i<liczbaZadan;i++)
            postepRozwiazywaniaZadania[i]=0;

        tekstOsiagniecia[0]="Wykonaj jedno zadanie.";
        celOsiagniecia[0]=1;
        tekstOsiagniecia[1]="Zalicz pierwsze zadanie.";
        celOsiagniecia[1]=30;
        tekstOsiagniecia[2]="Zalicz trzy pierwsze zadania.";
        celOsiagniecia[2]=3;
        tekstOsiagniecia[3]="Wykonaj zadanie w mniej niż 30 s.";
        celOsiagniecia[3]=1;
        tekstOsiagniecia[4]="Wykonaj zadanie w mniej niż 15 s.";
        celOsiagniecia[4]=1;
        tekstOsiagniecia[5]="Ucz się 20 minut.";
        celOsiagniecia[5]=20;
        tekstOsiagniecia[6]="Ucz się godzinę.";
        celOsiagniecia[6]=60;
        tekstOsiagniecia[7]="Rozwiąż zadanie 20 razy na 20 podejść.";
        celOsiagniecia[7]=20;
        tekstOsiagniecia[8]="Zdobądź 500 punktów.";
        celOsiagniecia[8]=500;
        tekstOsiagniecia[9]="Zdobądż 2000 punktów.";
        celOsiagniecia[9]=2000;
        tekstOsiagniecia[10]="Zdobądż 5000 punktów.";
        celOsiagniecia[10]=5000;
    }

    public int ileZadanJednegoTypuRozwiazanoDobrze(int[][] podejsciaZadan, int liczbaZadan, Gra gra)
    {
        int najwiecej=0;
        for(int i=0;i<liczbaZadan;i++)
        {
            int liczbaPoprawnychRozwiazanTegoZadania=0;
            for(int j=0;j<ROZWIAZAN_WSZYSTKICH;j++)
            {
                if(podejsciaZadan[i][j]>=gra.sciezka.dajPunktyZadania(i)*4)
                    liczbaPoprawnychRozwiazanTegoZadania++;
            }
            if(liczbaPoprawnychRozwiazanTegoZadania>najwiecej)
                najwiecej=liczbaPoprawnychRozwiazanTegoZadania;
        }
        return najwiecej;
    }

    public int dajLiczbeOdblokowanychZadan()
    {
        int zaliczonych=0;
        for(int i=0;i<liczbaZadan;i++)
        {
            if(czyZadanieOdblokowane[i])
                zaliczonych++;
        }
        return zaliczonych;
    }

    public String aktualizujOsiagniecia(Gra gra, Statystyki statystyki,int numerZadania,boolean czyRozwiazane)
    {
        String odpowiedz="";
        if(czyRozwiazane && !czyOdblokowane[0])
        {
            czyOdblokowane[0]=true;
            postepOsiagniecia[0]=1;
            odpowiedz+="<br> Odblokowano osiągnięcie: <b>"+tekstOsiagniecia[0]+"</b><br>";
        }

        postepOsiagniecia[1]=(postepRozwiazywaniaZadania[0]<31?postepRozwiazywaniaZadania[0]:30);
        if(postepOsiagniecia[1]>=celOsiagniecia[1] && !czyOdblokowane[1])
        {
            czyOdblokowane[1]=true;
            odpowiedz+="<br> Odblokowano osiągnięcie: <b>"+tekstOsiagniecia[1]+"</b><br>";
        }

        int zaliczone=0;
        for(int i=0;i<3;i++)
        {
            if(czyZadanieZaliczone[i])
                zaliczone++;
        }
        postepOsiagniecia[2]=zaliczone;
        if(zaliczone==3 && !czyOdblokowane[2])
        {
            czyOdblokowane[2]=true;
            odpowiedz+="<br> Odblokowano osiągnięcie: <b>"+tekstOsiagniecia[2]+"</b><br>";
        }
        if(statystyki.czasPodejsciaDoZadania[numerZadania][statystyki.indeksPodejscia[numerZadania]]/1000<30 && czyRozwiazane && !czyOdblokowane[3])
        {
            czyOdblokowane[3]=true;
            postepOsiagniecia[3]=1;
            odpowiedz+="<br> Odblokowano osiągnięcie: <b>"+tekstOsiagniecia[3]+"</b><br>";
        }
        if(statystyki.czasPodejsciaDoZadania[numerZadania][statystyki.indeksPodejscia[numerZadania]]/1000<15 && czyRozwiazane && !czyOdblokowane[4])
        {
            czyOdblokowane[4]=true;
            postepOsiagniecia[4]=1;
            odpowiedz+="<br> Odblokowano osiągnięcie: <b>"+tekstOsiagniecia[4]+"</b><br>";
        }

        if(!czyOdblokowane[5])
            postepOsiagniecia[5]=(int)statystyki.dajCzasSpedzonyNaZadaniach()/60000>celOsiagniecia[5]?celOsiagniecia[5]:(int)statystyki.dajCzasSpedzonyNaZadaniach()/60000;
        if(!czyOdblokowane[6])
            postepOsiagniecia[6]=(int)statystyki.dajCzasSpedzonyNaZadaniach()/60000>celOsiagniecia[6]?celOsiagniecia[6]:(int)statystyki.dajCzasSpedzonyNaZadaniach()/60000;
        if(!czyOdblokowane[7])
            postepOsiagniecia[7]=ileZadanJednegoTypuRozwiazanoDobrze(statystyki.wynikPodejsciaDoZadania, gra.liczbaZadan, gra);
        if(!czyOdblokowane[8])
            postepOsiagniecia[8]=statystyki.punkty<=celOsiagniecia[8]?statystyki.punkty:celOsiagniecia[8];
        if(!czyOdblokowane[9])
            postepOsiagniecia[9]=statystyki.punkty<=celOsiagniecia[9]?statystyki.punkty:celOsiagniecia[9];
        if(!czyOdblokowane[10])
            postepOsiagniecia[10]=statystyki.punkty<=celOsiagniecia[10]?statystyki.punkty:celOsiagniecia[10];


        for(int i=5;i<LICZBA_OSIAGNIEC;i++)
        {
            if(postepOsiagniecia[i]>=celOsiagniecia[i] && czyRozwiazane && !czyOdblokowane[i])
            {
                czyOdblokowane[i]=true;
                odpowiedz+="<br> Odblokowano osiągnięcie: <b>"+tekstOsiagniecia[i]+"</b><br>";
            }
        }

        return odpowiedz;
    }

    public int aktualizuj(Statystyki statystyki, int numerZadania) //0 = brak zmian, 1 = zadanie zaliczone, 2 = zadanie kolejne odblokowane, 3 = 1 + 2
    {
        int informacjaZwrotna=0;
        postepRozwiazywaniaZadania[numerZadania]=Math.min(statystyki.liczbaPoprawnychRozwiazanZadania[numerZadania]*100/ROZWIAZAN_WSZYSTKICH,100);
        if(postepRozwiazywaniaZadania[numerZadania]>=ROZWIAZAN_POTRZEBNYCH_DO_ZALICZENIA*100/ROZWIAZAN_WSZYSTKICH && !czyZadanieZaliczone[numerZadania])
        {
            czyZadanieZaliczone[numerZadania]=true;
            informacjaZwrotna+=1;
        }
        if(postepRozwiazywaniaZadania[numerZadania]>=PROCENT_POTRZEBNY_NA_ODBLOKOWANIE_KOLEJNEGO_ZADANIA && numerZadania!=czyZadanieOdblokowane.length-1 && !czyZadanieOdblokowane[numerZadania+1])
        {
            czyZadanieOdblokowane[numerZadania+1]=true;
            informacjaZwrotna+=2;
        }
        return informacjaZwrotna;
    }

    void ustawPostepZadania(int numerZadania, int postep)
    {
        postepRozwiazywaniaZadania[numerZadania]=postep;
    }
    boolean czyZadanieZaliczone(int numerZadania)
    {
        return czyZadanieZaliczone[numerZadania];
    }
    boolean czyZadanieOdblokowane(int numerZadania)
    {
        return czyZadanieOdblokowane[numerZadania];
    }

    void wczytaj(Activity aktywność)
    {
        zapis=aktywność.getSharedPreferences("zapis", aktywność.MODE_MULTI_PROCESS);
        for(int i=0;i<LICZBA_OSIAGNIEC;i++)
        {
            czyOdblokowane[i]=zapis.getBoolean("czyOdblokowane"+i,false);
            postepOsiagniecia[i]=zapis.getInt("postepOsiagniecia"+i,0);
        }
        for(int i=0;i<liczbaZadan;i++)
        {
            czyZadanieZaliczone[i]=zapis.getBoolean("czyZadanieZaliczone"+i,false);
            czyZadanieOdblokowane[i]=zapis.getBoolean("czyZadanieOdblokowane"+i,false);
            postepRozwiazywaniaZadania[i]=zapis.getInt("postepRozwiazywaniaZadania"+i,0);
            czyPodpowiedzOdblokowana[i]=zapis.getBoolean("czyPodpowiedzOdblokowana"+i,false);
        }

        czyZadanieOdblokowane[0]=true;
    }
    void zapisz(Activity aktywność)
    {
        zapis=aktywność.getSharedPreferences("zapis", aktywność.MODE_MULTI_PROCESS);
        SharedPreferences.Editor edytor=zapis.edit();
        for(int i=0;i<LICZBA_OSIAGNIEC;i++)
        {
            edytor.putBoolean("czyOdblokowane"+i,czyOdblokowane[i]);
            edytor.putInt("postepOsiagniecia"+i,postepOsiagniecia[i]);
        }
        for(int i=0;i<liczbaZadan;i++)
        {
            edytor.putBoolean("czyZadanieZaliczone"+i,czyZadanieZaliczone[i]);
            edytor.putBoolean("czyZadanieOdblokowane"+i,czyZadanieOdblokowane[i]);
            edytor.putInt("postepRozwiazywaniaZadania"+i,postepRozwiazywaniaZadania[i]);
            edytor.putBoolean("czyPodpowiedzOdblokowana"+i,czyPodpowiedzOdblokowana[i]);
        }

        edytor.commit();

    }
    public void reset(Activity aktywność)
    {
        for(int i=0;i<LICZBA_OSIAGNIEC;i++)
        {
            czyOdblokowane[i]=false;
            postepOsiagniecia[i]=0;
        }
        for(int i=0;i<liczbaZadan;i++)
        {
            czyZadanieZaliczone[i]=false;
            czyZadanieOdblokowane[i]=false;
            postepRozwiazywaniaZadania[i]=0;
            czyPodpowiedzOdblokowana[i]=false;
        }
        czyZadanieOdblokowane[0]=true;

        zapisz(aktywność);
    }
}
