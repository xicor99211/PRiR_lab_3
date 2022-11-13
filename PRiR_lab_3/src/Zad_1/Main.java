package Zad_1;

public class Main {
    static int ilosc_pociagow = 10;
    static int ilosc_torow = 5;
    static Peron peron;

    public Main() {
    }

    public static void main(String[] args) {
        peron = new Peron(ilosc_torow, ilosc_pociagow);
        for (int i = 0; i < ilosc_pociagow; i++)
            new Pociag(i, 2000, peron).start();
    }
}