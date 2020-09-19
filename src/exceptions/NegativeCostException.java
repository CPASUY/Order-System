package exceptions;

public class NegativeCostException extends Exception {
	//Constant
	private static final long serialVersionUID = 1L;
	//Method
	public NegativeCostException() {
		super("The cost of the product can not be negative");
	}

}
