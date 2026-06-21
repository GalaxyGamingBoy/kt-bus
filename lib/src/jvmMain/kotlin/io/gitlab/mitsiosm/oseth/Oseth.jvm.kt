package io.gitlab.mitsiosm.oseth

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

internal actual val engine: HttpClientEngineFactory<HttpClientEngineConfig>
    get() = CIO