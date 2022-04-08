package cebence.learning.multiplang.secretlab;

import cebence.learning.multiplang.api.Greeter;
import cebence.learning.multiplang.api.Microphone;

public class Frankenstein implements Greeter {
	public void sayHello(Microphone mic) {
		mic.say("It's alive!");
	}
}
