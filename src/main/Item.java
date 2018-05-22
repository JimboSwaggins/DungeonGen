package main;

import java.io.IOException;
import java.util.ArrayList;

public class Item {
	public String name;
	public String type;
	public int value;
	
	public Material material;
	public double volume;
	
	
	public Item(ArrayList<String> toInject) {
		while(toInject.size() > 0) {
			int toRemove = -1;
			for(int i = 0; i < toInject.size(); i++) {
				if(toInject.get(i).contains("TYPE:")) {
					this.type = toInject.get(i).replaceAll("TYPE:", "");
					toRemove = i;
					break;
				}if(toInject.get(i).contains("VALUE:")) {
					this.value = Integer.parseInt(toInject.get(i).replaceAll("VALUE:", ""));
					toRemove = i;
					break;
				}if(toInject.get(i).contains("ITEM:")) {
					this.name = toInject.get(i).replaceAll("ITEM", "");
					toRemove = i;
					break;
				}
			}
			if(toRemove == -1) {
				break;
			}else {
				toInject.remove(toInject.get(toRemove));		
			}
		}
		try {
			this.material = LoadAllObjects.getMaterial("IRON");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Loaded " + this.toString());
	}
	
}
