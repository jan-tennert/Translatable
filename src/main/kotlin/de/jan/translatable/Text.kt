package de.jan.translatable

data class Text(val text: String) : Component {

    override fun asString(translator: Translator) = text

}

fun text(text: String) : Component = Text(text)

val String.asComponent: Component
    get() = text(this)