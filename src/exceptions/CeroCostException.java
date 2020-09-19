package exceptions;

public class CeroCostException extends Exception {
	//Constant
	private static final long serialVersionUID = 1L;
	//Method
	public CeroCostException() {
		super("The cost of the product can not be 0");
	}

}
