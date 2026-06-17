package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * @param value Trip identifier
 */
@JvmInline
@Serializable
public value class TripId(public val value: String)
