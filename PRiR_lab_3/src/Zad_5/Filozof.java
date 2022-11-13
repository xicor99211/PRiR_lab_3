package Zad_5;

import java.util.concurrent.Semaphore ;
public class Filozof extends Thread {
    static int MAX=101;
    static String danie;
    static Semaphore [] widelec = new Semaphore [MAX] ;
    int mojNum;
    public Filozof ( int nr ) {
        mojNum=nr ;
    }
    public void run ( ) {
        while ( true ) {
// myslenie
            System.out.println ( "Myśli ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            widelec [mojNum].acquireUninterruptibly ( ) ; //przechwycenie L widelca
            widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ; //przechwycenie P widelca
// jedzenie
            System.out.println ( "Zaczyna jesc " +danie+" "+mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc "+danie+" " +mojNum) ;
            widelec [mojNum].release ( ) ; //zwolnienie L widelca
            widelec [ (mojNum+1)%MAX].release ( ) ; //zwolnienie P widelca
        }
    }
    public void uruchom()
    {
        for ( int i =0; i<MAX; i++) {
            Filozof.widelec [ i ]=new Semaphore ( 1 ) ;
        }
        for ( int i =0; i<MAX; i++) {
            new Filozof(i).start();
        }
    }
    public static void main ( String [] args ) {

    }
}