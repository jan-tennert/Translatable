package de.jan.translatable

import java.util.Properties

class Translator internal constructor(private val properties: Properties) {

    operator fun get(key: String, vararg args: Any) = if(args.isEmpty()) properties[key].toString() else String.format(properties[key].toString(), *args)
    operator fun get(translatable: TranslatableText) = get(translatable.key, *translatable.args)

}