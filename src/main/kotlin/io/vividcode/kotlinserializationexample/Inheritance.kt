package io.vividcode.kotlinserializationexample

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable
abstract class Base {
    abstract val name: String
}

@Serializable
class Child1(override val name: String, val v1: String) : Base()

@Serializable
class Child2(override val name: String, val v2: String) : Base()

@Serializable
data class InheritanceData(
    @Polymorphic val base: Base,
    val extra: Int
)

val customModule = SerializersModule {
    polymorphic(Base::class) {
        subclass(Child1::class)
        subclass(Child2::class)
    }
}