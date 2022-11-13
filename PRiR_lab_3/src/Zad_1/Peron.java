package Zad_1;

public class Peron {
    static int PERON =1;
    static int START=2;
    static int PRZEJAZD=3;
    static int KONIEC_PRZEJAZDU =4;
    static int KATASTROFA=5;
    int ilosc_torow;
    int ilosc_zajetych;
    int ilosc_pociagow;
    Peron(int ilosc_torow, int ilosc_pociagow){
        this.ilosc_torow =ilosc_torow;
        this.ilosc_pociagow =ilosc_pociagow;
        this.ilosc_zajetych=0;
    }
    synchronized int start(int numer){
        ilosc_zajetych--;
        System.out.println("Pozwolenie na odjazd pociąg: "+numer);
        return START;
    }
    synchronized int wjezdzaj(){
        try{
            Thread.sleep(1000);//sleep for 1000 ms
        }
        catch(Exception ie){
        }
        if(ilosc_zajetych< ilosc_torow){
            ilosc_zajetych++;
            System.out.println("Pozwolenie na zatrzymanie na torze "+ilosc_zajetych);
            return PERON;
        }
        else
        {return KONIEC_PRZEJAZDU;}
    }
    synchronized void zmniejsz(){
        ilosc_pociagow--;
        if(ilosc_pociagow == ilosc_torow) System.out.println("ILOSC POCIĄGÓW TAKA SAMA JAK ILOSC TORÓW ______________");
    }
}


