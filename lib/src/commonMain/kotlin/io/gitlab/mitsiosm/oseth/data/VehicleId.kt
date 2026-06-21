package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Vehicle identifier
 */
@JvmInline
@Serializable
public value class VehicleId(public val id: String)