package cebence.learning.multiplang.api;

/**
 * Someone who has something to say to the world.
 */
public interface Greeter {
	/**
	 * It is your turn, say something to the {@link Microphone}.
	 */
	void sayHello(Microphone mic);
}
