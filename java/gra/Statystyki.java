package gra;


import android.app.Activity;
import android.content.SharedPreferences;

public class Statystyki
{
    public final int LICZONYCH_PODEJSC=20;
    SharedPreferences zapis;
    int liczbaZadan;

    // zapisywane


    public long[] spedzonyCzasNaZadaniu;
    public int[] liczbaPodejscDoZadania;
    public int[] liczbaPoprawnychRozwiazanZadania;
    public int[][] wynikPodejsciaDoZadania;
    public long[][] czasPodejsciaDoZadania;
    public int[] indeksPodejscia;
    public int punkty;
    public int doswiadczenie;



    // niezapisywane


    public long[] czasSpedzonyNaZadaniuPrzyObecnejSesji;
    public int liczbaRozwiazanychZadanPrzyObecnejSesji=0;


    Statystyki(int liczbaZadan)
    {
        this.liczbaZadan=liczbaZadan;
        spedzonyCzasNaZadaniu=new long[liczbaZadan];
        wynikPodejsciaDoZadania=new int[liczbaZadan][LICZONYCH_PODEJSC];
        czasPodejsciaDoZadania=new long[liczbaZadan][LICZONYCH_PODEJSC];
        indeksPodejscia=new int[liczbaZadan];
        liczbaPodejscDoZadania=new int[liczbaZadan];
        liczbaPoprawnychRozwiazanZadania=new int[liczbaZadan];

        czasSpedzonyNaZadaniuPrzyObecnejSesji=new long[liczbaZadan];
        for(int i=0;i<liczbaZadan;i++)
        {
            for(int j=0;j<LICZONYCH_PODEJSC;j++)
            {
                wynikPodejsciaDoZadania[i][j]=0;
                czasPodejsciaDoZadania[i][j]=0;
            }
            spedzonyCzasNaZadaniu[i]=0;
            liczbaPodejscDoZadania[i]=0;
            liczbaPoprawnychRozwiazanZadania[i]=0;
            czasSpedzonyNaZadaniuPrzyObecnejSesji[i]=0;
            indeksPodejscia[i]=0;
        }
    }
    public long dajCzasSpedzonyNaZadaniach()
    {
        long suma=0;
        for(int i=0;i<liczbaZadan;i++)
        {
            suma+=spedzonyCzasNaZadaniu[i];
        }
        return suma;
    }
    public long dajCzasSpedzonyNaZadaniachWObecnejSesji()
    {
        long suma=0;
        for(int i=0;i<liczbaZadan;i++)
        {
            suma+=czasSpedzonyNaZadaniuPrzyObecnejSesji[i];
        }
        return suma;
    }
    public void rozwiazanoZadanie(int numerZadania, int wynik, boolean czyZaliczone, long poswieconyCzas)
    {
        punkty+=wynik;
        doswiadczenie+=wynik;

        wynikPodejsciaDoZadania[numerZadania][indeksPodejscia[numerZadania]]=wynik;
        liczbaPodejscDoZadania[numerZadania]++;
        if(czyZaliczone)
        {
            liczbaRozwiazanychZadanPrzyObecnejSesji++;
            liczbaPoprawnychRozwiazanZadania[numerZadania]++;
        }

        czasSpedzonyNaZadaniuPrzyObecnejSesji[numerZadania]+=poswieconyCzas;
        czasPodejsciaDoZadania[numerZadania][indeksPodejscia[numerZadania]]+=poswieconyCzas;
        spedzonyCzasNaZadaniu[numerZadania]+=poswieconyCzas;

        indeksPodejscia[numerZadania]++;
        if(indeksPodejscia[numerZadania]>=LICZONYCH_PODEJSC)
            indeksPodejscia[numerZadania]=0;
    }
    void wczytaj(Activity aktywność)
    {
        zapis=aktywność.getSharedPreferences("zapis", aktywność.MODE_MULTI_PROCESS);
        punkty=zapis.getInt("punkty",0);
        doswiadczenie=zapis.getInt("doswiadczenie",0);
        for(int i=0;i<liczbaZadan;i++)
        {
            spedzonyCzasNaZadaniu[i]=zapis.getLong("spedzonyCzasNaZadaniu"+i,0);
            liczbaPodejscDoZadania[i]=zapis.getInt("liczbaPodejscDoZadania"+i,0);
            liczbaPoprawnychRozwiazanZadania[i]=zapis.getInt("liczbaPoprawnychRozwiazanZadania"+i,0);
            indeksPodejscia[i]=zapis.getInt("indeksPodejscia"+i,0);

            for(int j=0;j<LICZONYCH_PODEJSC;j++)
            {
                wynikPodejsciaDoZadania[i][j]=zapis.getInt("wynikPodejsciaDoZadania"+i+"-"+j,0);
                czasPodejsciaDoZadania[i][j]=zapis.getLong("czasPodejsciaDoZadania"+i+"-"+j,0);
            }
        }

    }
    void zapisz(Activity aktywność)
    {
        zapis=aktywność.getSharedPreferences("zapis", aktywność.MODE_MULTI_PROCESS);
        SharedPreferences.Editor edytor=zapis.edit();

        edytor.putInt("punkty",punkty);
        edytor.putInt("doswiadczenie",doswiadczenie);
        for(int i=0;i<liczbaZadan;i++)
        {
            edytor.putLong("spedzonyCzasNaZadaniu"+i,spedzonyCzasNaZadaniu[i]);
            edytor.putInt("liczbaPodejscDoZadania"+i,liczbaPodejscDoZadania[i]);
            edytor.putInt("liczbaPoprawnychRozwiazanZadania"+i,liczbaPoprawnychRozwiazanZadania[i]);
            edytor.putInt("indeksPodejscia"+i,indeksPodejscia[i]);
            for(int j=0;j<LICZONYCH_PODEJSC;j++)
            {
                edytor.putInt("wynikPodejsciaDoZadania"+i+"-"+j,wynikPodejsciaDoZadania[i][j]);
                edytor.putLong("czasPodejsciaDoZadania"+i+"-"+j,czasPodejsciaDoZadania[i][j]);
            }
        }

        edytor.commit();

    }

    public void reset(Activity aktywność)
    {
        punkty=0;
        for(int i=0;i<liczbaZadan;i++)
        {
            spedzonyCzasNaZadaniu[i]=0;
            liczbaPodejscDoZadania[i]=0;
            liczbaPoprawnychRozwiazanZadania[i]=0;
            indeksPodejscia[i]=0;

            for(int j=0;j<LICZONYCH_PODEJSC;j++)
            {
                wynikPodejsciaDoZadania[i][j]=0;
                czasPodejsciaDoZadania[i][j]=0;
            }
        }

        zapisz(aktywność);
    }
}
