package Mine;

import linda.*;
import threads.*;
import javax.swing.*;
/**
 * Created by valentin on 24/02/15.
 */
public class Mine extends JDialog{

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JProgressBar progressBarH2O;
    private JProgressBar progressBarCO;
    private JProgressBar progressBarCH4;
    private JButton buttonLancer;

    private float seuil_CO = 100;
    private float seuil_CH4 = 100;
    private float seuil_H2O = 100;
    private float val_CO = 80;
    private float val_CH4 = 80;
    private float val_H2O = 80;

    private CapteurCH4 captCH4;
    private CapteurCO captCO;
    private CapteurH2O captH2O;
    private Commande_pompe_ventilateur pompe_ventil;
    private H2OHaut H2Ohaut;
    private Pompe pompe;
    private Ventilateur ventilateur;
    private EspaceTuple ts;


    public String getCH4() {
        return String.valueOf(val_CH4);
    }

    public String getCO() {
        return String.valueOf(val_CO);
    }

    public String getH2O() {
        return String.valueOf(val_H2O);
    }

    public void ventile() {
        val_CH4 = val_CH4 / (1+(float)(Math.random()*0.009));
        val_CO = val_CO / (1+(float)(Math.random()*0.009));
    }

    public Mine(){
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public void pompe() {
        val_H2O = val_H2O / (1+(float)(Math.random()*0.009));
    }

    public void initialisation(){
        ts = new EspaceTuple();
        captCH4 = new CapteurCH4(ts, this);
        captCO = new CapteurCO(ts, this);
        captH2O = new CapteurH2O(ts, this);
        H2Ohaut = new H2OHaut(ts, this, seuil_H2O);
        pompe_ventil = new Commande_pompe_ventilateur(ts, this, seuil_CO, seuil_CH4);
        pompe = new Pompe(ts, this);
        ventilateur = new Ventilateur(ts, this);

    }

    public static void main(String[] args) throws InterruptedException {
        Mine mine = new Mine();
        mine.pack();
        mine.setVisible(true);
        mine.initialisation();
        mine.captCH4.start();
        mine.captCO.start();
        mine.captH2O.start();
        mine.H2Ohaut.start();
        mine.pompe_ventil.start();
        mine.pompe.start();
        mine.ventilateur.start();
        while(1==1) {
            Thread.sleep(100);
            EspaceTuple copie = new EspaceTuple();
            copie.espace.addAll(mine.ts.espace);
            mine.val_CH4 = mine.val_CH4 * (1+(float)(Math.random()*0.003));
            mine.val_CO = mine.val_CO * (1+(float)(Math.random()*0.003));
            mine.val_H2O = mine.val_H2O * (1+(float)(Math.random()*0.003));

            System.out.println("===============Espace=============");
            for(int i = 0; i < mine.ts.espace.size(); i++){
                    TupleLinda t = mine.ts.espace.get(i);
                if (t != null) {
                    System.out.println(t.toString());

                }
            }
            System.out.println("=============Fin Espace===========");



        }
    }
}
