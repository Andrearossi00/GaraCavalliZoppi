import com.sun.tools.javac.Main;

public class Cavallo extends Thread {
    private final String name;
    private int lentezza;
    public Cavallo (String name, int lentezza){
        super();
        this.name = name;
        this.lentezza = lentezza;
    }

    @Override
    public void run(){
        System.out.println("Cavallo " + name + "comincia il suo galoppo");
        for (int i = 1; i <= 10; i++ ){
            try {
                sleep(lentezza);
            } catch (InterruptedException a) {
                throw new RuntimeException(a);
            }
            System.out.println(name + " cavalca - passo: " + 1);
        }
    }
    protected int getLentezza(){return lentezza;}
    protected void setLentezza(){ this.lentezza = lentezza;}
}