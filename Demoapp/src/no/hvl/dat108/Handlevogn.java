package no.hvl.dat108;

import java.util.ArrayList;

public class Handlevogn {
	private static ArrayList<Vare> varer = new ArrayList<Vare>();
	
	public Handlevogn() {
	}
	
	public void LeggTilVare(String navn) {
		varer.add(new Vare(navn));
	}
	
	public void fjernVare(int index) {
		varer.remove(index);
	}
	
	public ArrayList<Vare> getVarer() {
		return varer;
	}
	
	public int index (Vare vare) {
		int denne = varer.indexOf(vare);
		return denne;
	}
}
