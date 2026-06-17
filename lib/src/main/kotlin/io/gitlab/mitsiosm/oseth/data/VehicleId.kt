package io.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * Vehicle identifier
 */
@JvmInline
@Serializable
public value class VehicleId(public val id: String)