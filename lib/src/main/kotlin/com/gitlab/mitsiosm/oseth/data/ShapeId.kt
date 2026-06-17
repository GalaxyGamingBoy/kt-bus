package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.Serializable

/**
 * Shape identifier
 */
@JvmInline
@Serializable
public value class ShapeId(public val id: String)