package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * Stop identifier
 */
@JvmInline
@Serializable
public value class StopId(public val id: String)