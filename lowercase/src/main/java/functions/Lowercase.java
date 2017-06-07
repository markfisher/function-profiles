package functions;

import java.util.function.Function;

public class Lowercase implements Function<String, String> {

	public String apply(String input) {
		return input.toLowerCase();
	}
}
