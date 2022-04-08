package cebence.learning.multiplang.kotlin

import cebence.learning.multiplang.api.Greeter
import cebence.learning.multiplang.api.Microphone

class KotlinGreeter : Greeter {
	override fun sayHello(mic: Microphone) {
		mic.say("Hello Kotlin!")
	}
}
