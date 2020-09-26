package model;

import java.util.Comparator;

public class NameComparator implements Comparator<Client>{
	public int compare(Client r1, Client r2) {
		int comp=0;
		 comp=r1.getName().compareTo(r2.getName());
		 return comp;
	}
}
