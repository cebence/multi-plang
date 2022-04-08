package cebence.learning.multiplang.groovy

import cebence.learning.multiplang.api.Greeter
import cebence.learning.multiplang.api.Microphone

class GroovyGreeter implements Greeter {
	void sayHello(Microphone mic) {
		mic.say "Hello Groovy!"
	}
}
