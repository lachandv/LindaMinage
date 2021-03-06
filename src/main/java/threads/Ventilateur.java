package threads;
import linda.*;
import Mine.*;
/**
 * Created by valentin on 03/03/15.
 */
public class Ventilateur extends Thread {

    private EspaceTuple ts;
    private MineTMP mine;
    private boolean etat = false;

    public Ventilateur(EspaceTuple ts, MineTMP m) {
        this.ts = ts;
        this.mine = m;
    }

    public void run() {

        while (1 == 1) {
            if (ts.inp(ts, new Template("activer_ventilateur")) != null) {
                this.etat = true;
            }
            if (ts.inp(ts, new Template("desactiver_ventilateur")) != null) {
                this.etat = false;
            }
            if (this.etat == true) {
                mine.ventile();
                mine.ventile();
                mine.ventile();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}