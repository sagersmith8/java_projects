package gaussianFilter;

public class DimmensionException extends Exception {
	private static final long serialVersionUID = -2260342912989311327L;

	public DimmensionException() {
		super("A single dimmension does not exist for this filter");
	}
}
