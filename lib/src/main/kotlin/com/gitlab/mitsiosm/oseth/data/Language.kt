package com.gitlab.mitsiosm.oseth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The language to fetch the data in
 */
@Serializable
public enum class Language {
    @SerialName("el") GREEK,
    @SerialName("en") ENGLISH
}