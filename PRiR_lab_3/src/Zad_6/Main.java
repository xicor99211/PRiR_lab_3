package Zad_6;

import java.net.*;
import java.io.*;
import java.util.*;
class Run extends Thread {
    private Socket socket = null;
    String getAnswer() {
        InetAddress adres;
        String name = "";
        String ip = "";
        try {
            adres = InetAddress.getLocalHost();
            name = adres.getHostName();
            ip = adres.getHostAddress();
        }
        catch (UnknownHostException ex) { System.err.println(ex); }
        String document = "<html>\r\n" +
                "<center>"+
                "<body style=\"padding-top: 20px;\" bgcolor=\"SlateBlue\" ><br>\r\n" +
                "<h2 style=\"margin-bottom: 60px;\"><font color=MidnightBlue><span style=\"font-size: xx-large;\">MENU </span>\r\n" +
                "</font></h2>\r\n" +
                "<h3><font color=MidnightBlue><span style=\"font-size: xx-large\"></span></h3><hr>\r\n" +
                "<i><font color=MidnightBlue><span style=\"font-size: large\">Data: <b><i>" + new Date() + "</b><br>\r\n" +
                "<font color=MidnightBlue><em>Nazwa hosta: <b><em>" + name + "</b><br>\r\n" +
                "IP hosta: <b><font color=MidnightBlue>" + ip + "</b><br>\r\n</span>" +
                "<div style=\"background: url(\"https://images.unsplash.com/photo-1542831371-29b0f74f9713?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8Y29kaW5nfGVufDB8fDB8fA%3D%3D&w=1000&q=80\") \">" + "</div>" +
                "<hr>\r\n" +
                "<h2 style=\"padding-top: 100px;\"><font color=MediumSpringGreen><span style=\"font-size: xx-large\"><a style=\"color: Maroon; text-decoration: none;\" href=\"https://pogoda.interia.pl/prognoza-dlugoterminowa-bialystok,cId,1197\">SPRAWDZ POGODE  </a></span>\r\n" +
                "<h2 style=\"padding-top: 20px;\"><font color=MediumSpringGreen><span style=\"font-size: xx-large\"><a style=\"color: Maroon; text-decoration: none;\" href =\"https://www.uwb.edu.pl/\">STRONA UWB  </a></span>\r\n" +
                "</body>\r\n" +
                "</center>"+
                "</html>\r\n";

        String header = "HTTP/1.1 200 OK\r\n" +
                "Server: jHTTPServer ver 1.1\r\n" +
                "Last-Modified: Fri, 28 Jul 2000 07:58:55 GMT\r\n" +
                "Content-Length: " + document.length() + "\r\n" +
                "Connection: close\r\n" +
                "Content-Type: text/html; charset=iso-8859-2";
        return header + "\r\n\r\n" + document;
    }

    public Run(Socket socket){
        System.out.println("Nowy obiekt jHTTPSMulti...");
        this.socket = socket;
        start();
    }
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            System.out.println("---------------- Pierwsza linia zapytania ----------------");
            System.out.println(in.readLine());
            System.out.println("---------------- Wysylam odpowiedz -----------------------");
            System.out.println(getAnswer());
            System.out.println("---------------- Koniec odpowiedzi -----------------------");
            out.println(getAnswer());
            out.flush();
        } catch (IOException e) {
            System.out.println("Blad IO danych!");
        }
        finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Blad zamkniecia gniazda!");
            }
        } // finally
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(80);
        try {
            while (true) {
                Socket socket = server.accept();
                new Run(socket);
            } // while
        } // try
        finally { server.close();}
    } // main
}