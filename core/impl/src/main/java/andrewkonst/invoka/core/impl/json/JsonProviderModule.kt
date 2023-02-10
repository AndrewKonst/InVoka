package andrewkonst.invoka.core.impl.json

import andrewkonst.invoka.core.api.json.JsonProvider
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json

@Module
internal class JsonProviderModule {

    @Provides
    fun provideJsonProvider(): JsonProvider = object : JsonProvider {
        override val json: Json
            get() = Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = true
            }

    }
}