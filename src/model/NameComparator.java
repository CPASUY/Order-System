package model;

import java.util.Comparator;

public class NameComparator implements Comparator<Client>{

	@Override
	public int compare(Client c1, Client c2) {
		int comp;
		String full1=" ";
		String full2=" ";
		String nom1 = c1.getName(); 
		String parts[]=nom1.split(" ");
		for(int s=0;s<parts.length;s++) {
			full1+=parts[s];
		}
		String nom2 = c2.getName(); 
		String parts2[]=nom2.split(" ");
		for(int w=0;w<parts2.length;w++) {
			full2+=parts[w];
		}
		comp = full1.compareTo(full2);
		
		return comp;
	}

}
