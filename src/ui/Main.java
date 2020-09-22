package ui;

import java.io.IOException;

import exceptions.CeroCostException;
import exceptions.NegativeCostException;

public class Main {

	public static void main(String[] args) throws IOException, NegativeCostException, CeroCostException {
		Menu m;
		m = new Menu();
		m.startMenu();
	}
}