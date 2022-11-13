package Zad_5;

import java.util.concurrent.Semaphore;

public class Filozof2 extends Thread {
    static int MAX=101;
    static Semaphore [] widelec = new Semaphore [MAX] ;

    static String danie;
    int mojNum;
    public Filozof2(int nr ) {
        mojNum=nr ;
    }
    public void run ( ) {
        while ( true ) {
// myslenie
            System.out.println ( "Myśli ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            if (mojNum == 0) {
                widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
                widelec [mojNum].acquireUninterruptibly ( ) ;
            } else {
                widelec [mojNum].acquireUninterruptibly ( ) ;
                widelec [ (mojNum+1)%MAX].acquireUninterruptibly ( ) ;
            }
// jedzenie
            System.out.println ( "Zaczyna jesc "+danie+" "+mojNum) ;
            try {
                Thread.sleep ( ( long ) (3000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc "+danie+" "+mojNum) ;
            widelec [mojNum].release ( ) ;
            widelec [ (mojNum+1)%MAX].release ( ) ;
        }
    }

    public void uruchom()
    {
        for ( int i =0; i<MAX; i++) {
            Filozof2.widelec [ i ]=new Semaphore ( 1 ) ;
        }
        for ( int i =0; i<MAX; i++) {
            new Filozof2(i).start();
        }
    }

    public static void main ( String [] args ) {

    }
}