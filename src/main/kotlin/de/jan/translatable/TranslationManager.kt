package de.jan.translatable

import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Properties
import kotlin.io.path.Path
import kotlin.io.path.extension
import kotlin.io.path.inputStream
import kotlin.io.path.name
import kotlin.io.path.nameWithoutExtension

class TranslatorManager(id: String) {

    private val translationsPath = Paths.get(ClassLoader.getSystemResource("translations/$id")?.toURI() ?: throw FileNotFoundException("Translations with id $id not found"))
    private val languages = hashMapOf<String, Translator>()

    init {
        loadTranslations()
    }

    private fun loadTranslations() {
        Files.walk(translationsPath)
            .filter { it.extension == "properties" }
            .forEach { path ->
                val properties = Properties()
                path.inputStream().use {
                    properties.load(it)
                }
                languages[path.nameWithoutExtension] = Translator(properties)
            }
    }

    operator fun get(name: String) = languages[name]

    operator fun iterator() = languages.toMap().iterator()

}

fun translator(id: String) = TranslatorManager(id)