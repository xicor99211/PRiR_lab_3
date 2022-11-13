package Zad_1;

import java.util.Random;
public class Pociag extends Thread {
    static int POCIAG = 1;
    static int START = 2;
    static int PRZEJAZD = 3;
    static int KONIEC_PRZEJAZDU = 4;
    static int KATASTROFA = 5;
    static int TANKUJ = 1000;
    static int REZERWA = 500;
    //zmienne pomocnicze
    int numer;
    int paliwo;
    int stan;
    Peron p;
    Random rand;

    public Pociag(int numer, int paliwo, Peron l) {
        this.numer = numer;
        this.paliwo = paliwo;
        this.stan = PRZEJAZD;
        this.p = l;
        rand = new Random();
    }

    public void run() {
        while (true) {
            if (stan == POCIAG) {
                if (rand.nextInt(2) == 1) {
                    stan = START;
                    paliwo = TANKUJ;
                    System.out.println("prosze o pozwolenie na odjazd, pociąg " + numer);
                    stan = p.start(numer);
                } else {
                    System.out.println("Czekam na odjazd pociąg " + numer);
                }
            } else if (stan == START) {
                System.out.println("Ruszyłem, pociąg " + numer);
                stan = PRZEJAZD;
            } else if (stan == PRZEJAZD) {
                paliwo -= rand.nextInt(500);
                if (paliwo <= REZERWA) {
                    stan = KONIEC_PRZEJAZDU;
                } else try {
                    sleep(rand.nextInt(1000));
                } catch (Exception e) {
                }
            } else if (stan == KONIEC_PRZEJAZDU) {
                System.out.println("Prosze o pozowolenie na wjazd " + numer + " ilosc paliwa " + paliwo);
                stan = p.wjezdzaj();
                if (stan == KONIEC_PRZEJAZDU) {
                    paliwo -= rand.nextInt(500);
                    System.out.println("REZERWA " + paliwo);
                    if (paliwo <= 0) stan = KATASTROFA;
                }
            } else if (stan == KATASTROFA) {
                System.out.println("Wykolejenie pociąg " + numer);
                p.zmniejsz();
            }
        }
    }
}