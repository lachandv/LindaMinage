package threads;
import linda.*;
import Mine.*;
/**
 * Created by valentin on 03/03/15.
 */
public class Commande_pompe_ventilateur extends Thread {

    private EspaceTuple ts;
    private MineTMP mine;
    private float seuil_CO;
    private float seuil_CH4;
    private float seuil_bas_CO;
    private float seuil_bas_CH4;

    public Commande_pompe_ventilateur(EspaceTuple ts, MineTMP m, float seuil_CO, float seuil_CH4) {
        this.ts = ts;
        this.mine = m;
        this.seuil_CH4 = seuil_CH4;
        this.seuil_CO = seuil_CO;
        this.seuil_bas_CH4 = seuil_CH4*(float)0.8;
        this.seuil_bas_CO = seuil_CO*(float)0.8;
    }

    public void run() {
        while (1 == 1) {
            TupleLinda t1 = ts.rd(ts, new Template("niveau_CH4 ?x"));
            float x = Float.valueOf(t1.getValue(0));
            TupleLinda t2 = ts.rd(ts, new Template("niveau_CO ?y"));
            float y = Float.valueOf(t2.getValue(0));
            if (x >= seuil_CH4 || y >= seuil_CO) {
                ts.add(ts, new Template("activer_ventilateur"));
                ts.add(ts, new Template("detection_gaz_bas"));
            }
            if (x < seuil_CH4 && y < seuil_CO) {
                if( ts.inp(ts, new Template("H2O_haut_detecte")) != null) {
                    ts.add(ts, new Template("activer_pompe"));
                    ts.add(ts, new Template("detection_H2O_haut"));
                    ts.add(ts, new Template("detection_gaz_haut"));
                }
                else if(ts.inp(ts, new Template("H2O_bas_detecte")) != null){
                    ts.add(ts, new Template("desactiver_pompe"));
                    ts.add(ts, new Template("detection_H2O_bas"));
                    ts.add(ts, new Template("detection_gaz_haut"));
                }
                else{
                    ts.add(ts, new Template("detection_gaz_haut"));
                }
            }
            if (x <= seuil_bas_CH4 && y <= seuil_bas_CO){
                ts.add(ts, new Template("desactiver_ventilateur"));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}