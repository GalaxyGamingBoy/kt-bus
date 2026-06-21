package io.gitlab.mitsiosm.oseth

import io.ktor.client.engine.darwin.Darwin

internal actual val engine: io.ktor.client.engine.HttpClientEngineFactory<io.ktor.client.engine.HttpClientEngineConfig>
    get() = Darwin