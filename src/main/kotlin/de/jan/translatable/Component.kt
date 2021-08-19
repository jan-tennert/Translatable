package de.jan.translatable

sealed interface Component {

    fun asString(translator: Translator): String

}