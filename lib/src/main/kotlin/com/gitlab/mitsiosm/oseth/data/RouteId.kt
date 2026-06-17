package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * @param value Route identifier
 */
@JvmInline
@Serializable
public value class RouteId(public val value: String)
