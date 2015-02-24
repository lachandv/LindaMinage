//package main.java;

import java.util.ArrayList;


public class TupleLinda {

	private ArrayList<Champ> monTuple = new ArrayList<Champ>();

	public TupleLinda(String tupleLin) {
		String champs[] = tupleLin.split(",");
		for (String champ : champs){
			String cham[] = champ.split(" ");
			Champ field = new Champ(cham[0],cham[1]);
			monTuple.add(field);
		}
		
		
	}

    public int size(){
        return this.monTuple.size();
    }

    public boolean match(Template t){
        if (this.size() != t.size()){
            return false;
        }
        for(int i = 0; i<this.size(); i++){
            Champ champTuple = this.monTuple.get(i);
            Champ champTemplate = t.get(i);

            if (!champTuple.getType().equals(champTemplate.getType()) ||
                    (!champTuple.getValeur().equals(champTemplate.getValeur()) && champTemplate.getValeur() != null)) {
                return false;
            };
        }
        return true;
    }
	
	
}
