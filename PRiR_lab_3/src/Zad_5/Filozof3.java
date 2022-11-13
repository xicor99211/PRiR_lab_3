package Zad_5;

import java.util.Random;
import java.util.concurrent.Semaphore ;
public class Filozof3 extends Thread {
    static int MAX=101;
    static Semaphore [] widelec = new Semaphore [MAX] ;
    int mojNum;

    static String danie;
    Random losuj ;
    public Filozof3 ( int nr ) {
        mojNum=nr ;
        losuj = new Random(mojNum) ;
    }
    public void run ( ) {
        while ( true ) {
// myslenie
            System.out.println ( "Myśli ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            int strona = losuj.nextInt ( 2 ) ;
            boolean podnioslDwaWidelce = false ;
            do {
                if ( strona == 0) {
                    widelec [mojNum].acquireUninterruptibly ( ) ;
                    if( ! ( widelec [ (mojNum+1)%MAX].tryAcquire ( ) ) ) {
                        widelec[mojNum].release ( ) ;
                    } else {
                        podnioslDwaWidelce = true ;
                    }
                } else {
                    widelec[(mojNum+1)%MAX].acquireUninterruptibly ( ) ;
                    if ( ! (widelec[mojNum].tryAcquire ( ) ) ) {
                        widelec[(mojNum+1)%MAX].release ( ) ;
                    } else {
                        podnioslDwaWidelce = true ;
                    }
                }
            } while ( podnioslDwaWidelce == false ) ;
            System.out.println ( "Zaczyna jesc " +danie+" "+mojNum) ;
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
            Filozof3.widelec [ i ]=new Semaphore ( 1 ) ;
        }
        for ( int i =0; i<MAX; i++) {
            new Filozof3(i).start();
        }
    }

    public static void main ( String [] args ) {
    }
}