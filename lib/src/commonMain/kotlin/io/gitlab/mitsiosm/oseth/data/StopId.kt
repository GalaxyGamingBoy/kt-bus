package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Stop identifier
 */
@JvmInline
@Serializable
public value class StopId(public val id: String)