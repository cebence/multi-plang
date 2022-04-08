package cebence.learning.multiplang.api;

/**
 * A way for a {@link Greeter} to say something to the world.
 */
public interface Microphone {
	/**
	 * Say your greeting, and it will be broadcasted to the world.
	 */
	void say(String greeting);
}
