package de.jan.translatable

class TranslatableText(val key: String, vararg val args: Any) : Component {

    override fun asString(translator: Translator) = translator[this]

}

fun translatable(key: String, vararg args: Any) = TranslatableText(key, *args)