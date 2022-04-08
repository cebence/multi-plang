package cebence.learning.multiplang.app;

import java.util.ServiceLoader;
import cebence.learning.multiplang.api.Greeter;
import cebence.learning.multiplang.api.Microphone;

/**
 * A stage for different JVM programming languages to say "Hello" to the world.
 * It is using {@link ServiceLoader} to locate the {@link Greeter} implementations
 * and let them say hello.
 */
public class Main {
	public static void main(String[] args) {
		final Microphone microphone = new Microphone() {
			public void say(String greeting) {
				System.out.println(greeting);
			}
		};

		ServiceLoader<Greeter> greeters = ServiceLoader.load(Greeter.class);
		for (Greeter greeter : greeters) {
			System.out.println(String.format("Welcome, %s. Please say something:",
					greeter.getClass().getSimpleName()));
			System.out.print("> ");
			greeter.sayHello(microphone);
			System.out.println();
		}
	}
}
