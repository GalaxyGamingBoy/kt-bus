package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * @param value Route identifier
 */
@JvmInline
@Serializable
public value class RouteId(public val value: String)
