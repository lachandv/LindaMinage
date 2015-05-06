package Mine;

import linda.*;
import threads.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by valentin on 24/02/15.
 */
public class MineTMP extends JFrame{

    private JProgressBar progressBarH2O;
    private JProgressBar progressBarCO;
    private JProgressBar progressBarCH4;
    private JLabel labelH2O;
    private JLabel labelCO;
    private JLabel labelCH4;
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

    public MineTMP(){
        progressBarH2O = new JProgressBar(0, (int)(seuil_H2O*1.2));
        progressBarCO = new JProgressBar(0, (int)(seuil_CO*1.2));
        progressBarCH4 = new JProgressBar(0, (int)(seuil_CH4*1.2));
        labelH2O = new JLabel("Niveau H2O");
        labelCO = new JLabel("Niveau CO");
        labelCH4 = new JLabel("Niveau CH4");
        buttonLancer = new JButton("Lancer la mine");
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(labelH2O, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        this.add(labelCO, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        this.add(labelCH4, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        this.add(progressBarH2O, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        this.add(progressBarCO, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        this.add(progressBarCH4, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        this.add(buttonLancer, c);

        this.setVisible(true);
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
        MineTMP mine = new MineTMP();
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

            mine.progressBarCH4.setValue((int) mine.val_CH4);
            if( mine.val_CH4 < mine.seuil_CH4*0.9){
                mine.progressBarCH4.setForeground(Color.GREEN);
            }
            else if (mine.val_CH4 >= mine.seuil_CH4*0.9 && mine.val_CH4 < mine.seuil_CH4){
                mine.progressBarCH4.setForeground(Color.ORANGE);
            }
            else{
                mine.progressBarCH4.setForeground(Color.RED);
            }

            mine.progressBarCO.setValue((int)mine.val_CO);
            if( mine.val_CO < mine.seuil_CO*0.9){
                mine.progressBarCO.setForeground(Color.GREEN);
            }
            else if (mine.val_CO >= mine.seuil_CO*0.9 && mine.val_CH4 < mine.seuil_CO){
                mine.progressBarCO.setForeground(Color.ORANGE);
            }
            else{
                mine.progressBarCO.setForeground(Color.RED);
            }

            mine.progressBarH2O.setValue((int)mine.val_H2O);
            if( mine.val_H2O < mine.seuil_H2O*0.9){
                mine.progressBarH2O.setForeground(Color.GREEN);
            }
            else if (mine.val_H2O >= mine.seuil_H2O*0.9 && mine.val_H2O < mine.seuil_H2O){
                mine.progressBarH2O.setForeground(Color.ORANGE);
            }
            else{
                mine.progressBarH2O.setForeground(Color.RED);
            }

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
