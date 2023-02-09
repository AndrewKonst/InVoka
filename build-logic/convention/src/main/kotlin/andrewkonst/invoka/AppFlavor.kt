package andrewkonst.invoka

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project

const val environmentKey = "environment"

@Suppress("EnumEntryName")
enum class AppFlavor(val dimension: String, val applicationIdSuffix: String? = null) {
    dev(environmentKey),
    prod(environmentKey)
}

fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: AppFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += environmentKey
        productFlavors {
            AppFlavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension
                    flavorConfigurationBlock(this, it)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            this.applicationIdSuffix = it.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}
