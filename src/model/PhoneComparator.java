package model;

import java.util.Comparator;

public class PhoneComparator  implements Comparator<Client>{

	@Override
	public int compare(Client c1, Client c2) {
		int comp;
		if(c1.getPhone()<c2.getPhone()) {
			comp = 1; 
		}else if(c1.getPhone()>c2.getPhone()) {
			comp = -1;
		}else {
			comp=0;
		}
		return comp;
	}

}
