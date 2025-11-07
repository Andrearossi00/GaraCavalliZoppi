import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestoreGaraCavalli {
    static String primo="";
    static PrintWriter pw;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String tmpS;
        int tmp;


        try {
            pw = new PrintWriter(new FileWriter("risultati_gara.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Cavallo> listaCavallo = new ArrayList<Cavallo>();
        for (int i = 1; i <= 4; i++) {
            System.out.println("Inserisci il nome del cavallo " + i);
            tmpS =  input.nextLine();
            System.out.println("Inserisci la lentezza del cavallo " + i);
            tmp = input.nextInt();
            String v = input.nextLine(); //prende il \n
            Cavallo c=new Cavallo(tmpS, tmp);
            listaCavallo.add(c);
        }
        for(Cavallo c: listaCavallo){
            c.start();
        }

        if (!listaCavallo.isEmpty()){
            int randomIndex = (int) (Math.random() * listaCavallo.size());
            listaCavallo.get(randomIndex).interrupt();
        }

        for(Cavallo c: listaCavallo){
            try {
                c.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Il primo cavallo: " + primo);
        scriviNelFile("il vincitore della gara è:" + primo);

        if (pw != null) {
            pw.close();
        }
        input.close();
    }

    public static String getPrimo() {
        return primo;
    }

    public static synchronized void setPrimo(String primo) {
        if(GestoreGaraCavalli.primo.isEmpty()){
            GestoreGaraCavalli.primo = primo;
        }
    }
    public static synchronized void scriviNelFile (String testo) {
        if (pw != null) {
            pw.println(testo);
            pw.flush();
        }
    }
}