package main.java;

import java.util.ArrayList;


public class TupleLinda {

	ArrayList<Champ> monTuple = new ArrayList<Champ>();

	public TupleLinda(String tupleLin) {
		String champs[] = tupleLin.split(",");
		for (String champ : champs){
			String cham[] = champ.split(" ");
			Champ field = new Champ(cham[0],cham[1]);
			monTuple.add(field);
		}
		
		
	}
	
	
}
