package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Weapon {
	public String name;
	public String type;
	
	public int value;
	public Material material;
	public HashMap matsAndWeights = new HashMap<Material, int>();
	
	
	public Weapon(ArrayList<String> toInject) {
		matsAndWeights  = new HashMap<Material, int>();
		while(toInject.size() > 0) {
			int toRemove = 0;
			for(int i = 0; i < toInject.size(); i++) {
				if(toInject.get(i).contains("TYPE:")) {
					this.type = toInject.get(i).replaceAll("TYPE:", "");
					toRemove = i;
					break;
				}if(toInject.get(i).contains("VALUE:")) {
					this.value = Integer.parseInt(toInject.get(i).replaceAll("VALUE:", ""));
				}if()
			}
		}
	}
	
	
}
