package io.ashdavies.playground.yaml

import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

internal data class YamlConfig(
    val serializersModule: SerializersModule = EmptySerializersModule
)