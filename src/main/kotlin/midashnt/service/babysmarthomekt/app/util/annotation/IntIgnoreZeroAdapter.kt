package midashnt.service.babysmarthomekt.app.util.annotation

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

class IntIgnoreZeroAdapter : TypeAdapter<Int?>() {

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Int? {
        if (`in`.peek() == JsonToken.NULL) {
            `in`.nextNull()
            return 0
        }
        return `in`.nextInt()
    }

    @Throws(IOException::class)
    override fun write(out: JsonWriter, data: Int?) {
        if (data == null || data == INT_ZERO) {
            out.nullValue()
            return
        }
        out.value(data.toInt().toLong())
    }

    companion object {
        private const val INT_ZERO = 0
    }

}