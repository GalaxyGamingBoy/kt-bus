package io.gitlab.mitsiosm.oseth.serializers

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.time.Instant

internal object InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

    @OptIn(FormatStringsInDatetimeFormats::class)
    override fun serialize(encoder: Encoder, value: Instant) {
        val formatter = LocalDateTime.Format { byUnicodePattern("dd/MM/yyyy HH:mm:ss") }
        val format = formatter.format(value.toLocalDateTime(TimeZone.currentSystemDefault()))
        encoder.encodeString(format)
    }

    override fun deserialize(decoder: Decoder): Instant {
        return Instant.parse(decoder.decodeString())
    }

}