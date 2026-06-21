package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Shape identifier
 */
@JvmInline
@Serializable
public value class ShapeId(public val id: String)