package andrewkonst.invoka.initializers

import andrewkonst.invoka.core.api.config.Config
import andrewkonst.invoka.core.api.initializer.Initializer
import andrewkonst.invoka.core.ui.imageurl.ImageUrlBuilder
import android.content.Context
import android.util.Log
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.intercept.Interceptor
import coil.request.ImageResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject


internal class ImageInitializer @Inject constructor(
    private val context: Context,
    private val config: Config,
) : Initializer {

    override fun initialize() {
        val logger = HttpLoggingInterceptor { message -> Log.d("image", message) }
        logger.level =
            if (config.isDebug) HttpLoggingInterceptor.Level.BASIC
            else HttpLoggingInterceptor.Level.NONE
        val coilOkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .build()
        Coil.setImageLoader {
            ImageLoader.Builder(context)
                .okHttpClient(coilOkHttpClient)
                .components {
                    add(ImageUrlBuilderCoilInterceptor(config))
                    add(SvgDecoder.Factory())
                }
                .crossfade(300)
                .build()
        }

    }

    private class ImageUrlBuilderCoilInterceptor constructor(
        private val config: Config
    ) : Interceptor {

        override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
            val request = when (val data = chain.request.data) {
                is ImageUrlBuilder -> {
                    chain.request.newBuilder()
                        .data(transform(data))
                        .build()
                }
                else -> chain.request
            }
            return chain.proceed(request)
        }

        private fun transform(builder: ImageUrlBuilder): String = builder.buildUrl(config)

    }

}
