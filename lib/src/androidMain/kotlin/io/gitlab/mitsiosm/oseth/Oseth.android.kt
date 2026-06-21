package io.gitlab.mitsiosm.oseth

import io.ktor.client.engine.android.Android

internal actual val engine: io.ktor.client.engine.HttpClientEngineFactory<io.ktor.client.engine.HttpClientEngineConfig>
    get() = Android