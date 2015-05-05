package threads;
import linda.*;
import Mine.*;
/**
 * Created by valentin on 03/03/15.
 */
public class Commande_pompe_ventilateur extends Thread {

    private EspaceTuple ts;
    private Mine mine;
    private float seuil_CO;
    private float seuil_CH4;

    public Commande_pompe_ventilateur(EspaceTuple ts, Mine m, float seuil_CO, float seuil_CH4) {
        this.ts = ts;
        this.mine = m;
        this.seuil_CH4 = seuil_CH4;
        this.seuil_CO = seuil_CO;
    }

    public void run() {
        while (1 == 1) {
            TupleLinda t1 = ts.rd(ts, new Template("niveau_CH4 ?x"));
            float x = Float.valueOf(t1.getValue(0));
            TupleLinda t2 = ts.rd(ts, new Template("niveau_CO ?y"));
            float y = Float.valueOf(t2.getValue(0));
            System.out.println("CH4 : " +x);
            System.out.println("CO : "+y);
            if (x >= seuil_CH4 || y >= seuil_CO) {
                ts.add(ts, new Template("activer_ventilateur"));
                ts.add(ts, new Template("detection_gaz_bas"));
            }
            if (x < seuil_CH4 && y < seuil_CO) {
                ts.add(ts, new Template("activer_pompe"));
                ts.add(ts, new Template("detection_H2O_haut"));
                ts.add(ts, new Template("detection_gaz_haut"));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}