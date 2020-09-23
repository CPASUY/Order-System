package model;

import java.util.Comparator;

public class NitComparator  implements Comparator<Restaurant>{

	@Override
	public int compare(Restaurant r1, Restaurant r2) {
		int comp=0;
		 comp=r1.getNit().compareTo(r2.getNit());
		 return comp;
	}

}
