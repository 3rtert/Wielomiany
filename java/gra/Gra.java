package gra;


import android.app.Activity;

public class Gra
{
    Activity widok;

    int liczbaZadan=6;
    public Sciezka sciezka=new Sciezka(liczbaZadan);
    public Statystyki statystyki=new Statystyki(liczbaZadan);
    public Postep postep=new Postep(liczbaZadan);

    public Gra(Activity widok)
    {
        this.widok=widok;
    }

    public int dajLiczbeZadan()
    {
        return liczbaZadan;
    }

    public void generujZadanie(int numerZadania)
    {
        sciezka.zadanie[numerZadania].generuj();
    }

    public String sprawdzZadanie(int numerZadania, String[] odpowiedzi, long poswieconyCzas) // zwraca informacje dla ucznia o wynikach
    {
        String[] rozwiazania = sciezka.dajRozwiazaniaZadania(numerZadania);
        String[] pytania = sciezka.dajPytaniaZadania(numerZadania);
        int punkty=sciezka.dajPunktyZadania(numerZadania);

        String informacjaZwrotna=sciezka.dajTrescZadania(numerZadania)+"<br><br><b>"+sciezka.dajFunkcjeZadania(numerZadania)+"</b><br><br>";
        int otrzymanePunkty=0;
        for(int i=0;i<rozwiazania.length;i++)
        {
            if(rozwiazania[i].equals(odpowiedzi[i]))
            {
                otrzymanePunkty+=punkty;
                informacjaZwrotna+="✔ "+pytania[i]+odpowiedzi[i]+" <br>";
            }
            else
            {
                informacjaZwrotna+="✖ "+pytania[i]+rozwiazania[i]+", a nie "+odpowiedzi[i]+"<br>";
            }
        }
        if(otrzymanePunkty==3*punkty)
            otrzymanePunkty+=punkty;
        informacjaZwrotna+="<br><br><b> Nagroda</b>: "+otrzymanePunkty+" punktów.";

        statystyki.rozwiazanoZadanie(numerZadania, otrzymanePunkty, otrzymanePunkty==punkty*rozwiazania.length, poswieconyCzas);
        int rezultat=postep.aktualizuj(statystyki, numerZadania);
        String zdobyteOsiagniecia=postep.aktualizujOsiagniecia(this,statystyki,numerZadania,rezultat>0);
        if(rezultat==1 || rezultat==3)
            informacjaZwrotna+="<br><br><b> Zadanie zaliczone!</b>";
        if(rezultat==2 || rezultat==3)
            informacjaZwrotna+="<br><br><b> Kolejne zadanie jest odblokowane!</b>";
        informacjaZwrotna+="<br>"+zdobyteOsiagniecia;
        zapiszDane();
        return informacjaZwrotna;
    }

    public void wczytajDane()
    {
        statystyki.wczytaj(widok);
        postep.wczytaj(widok);
    }
    public void zapiszDane()
    {
        statystyki.zapisz(widok);
        postep.zapisz(widok);
    }
    public void reset(Activity aktywność)
    {
        statystyki.reset(aktywność);
        postep.reset(aktywność);
    }


}
