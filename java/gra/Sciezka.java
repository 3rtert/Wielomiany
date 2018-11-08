package gra;


public class Sciezka
{
    Zadanie[] zadanie;

    Sciezka(int liczbaZadan)
    {
        zadanie=new Zadanie[liczbaZadan];
        zadanie[0]=new Zadanie01();
        zadanie[1]=new Zadanie02();
        zadanie[2]=new Zadanie03();
        zadanie[3]=new Zadanie04();
        zadanie[4]=new Zadanie05();
        zadanie[5]=new Zadanie06();
    }
    public void generujZadanie(int numerZadania)
    {
        zadanie[numerZadania].generuj();
    }
    public String dajTytulZadania(int numerZadania)
    {
        return zadanie[numerZadania].dajTytul();
    }
    public String dajTrescZadania(int numerZadania)
    {
        return zadanie[numerZadania].dajTresc();
    }
    public String dajFunkcjeZadania(int numerZadania)
    {
        return zadanie[numerZadania].dajFunkcje();
    }
    public String[] dajRozwiazaniaZadania(int numerZadania)
    {
        return zadanie[numerZadania].dajRozwiazania();
    }
    public int dajPunktyZadania(int numerZadania)
    {
        return zadanie[numerZadania].dajPunkty();
    }
    public String[] dajPytaniaZadania(int numerZadania)
    {
        return zadanie[numerZadania].dajPytania();
    }
    public int dajLiczbePytanZadania(int numerZadania)
    {
        return zadanie[numerZadania].dajLiczbePytan();
    }
    public String dajPodpowiedzZadania(int numerZadania)
    {
        return zadanie[numerZadania].dajPodpowiedz();
    }
    public String dajWyjasnienieZadania(int numerZadania)
    {
        return zadanie[numerZadania].dajWyjasnienie();
    }
    public String dajOpisZadania(int numerZadania)
    {
        return zadanie[numerZadania].dajOpis();
    }
}

