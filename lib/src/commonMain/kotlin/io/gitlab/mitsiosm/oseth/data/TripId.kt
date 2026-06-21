package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * @param value Trip identifier
 */
@JvmInline
@Serializable
public value class TripId(public val value: String)
