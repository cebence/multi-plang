package cebence.learning.multiplang.scala

import cebence.learning.multiplang.api.Greeter
import cebence.learning.multiplang.api.Microphone

class ScalaGreeter extends Greeter {
	def sayHello(mic: Microphone) = mic.say("Hello Scala!")
}
