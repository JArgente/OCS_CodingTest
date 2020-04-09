package com.olympic.channel.rover.domain

sealed class Action(val battery: Int)
object Forward : Action(3)
object Backwards : Action(3)
object TurnLeft : Action(2)
object TurnRight : Action(2)
object TakeSample : Action(8)
object SolarPanels: Action(1)