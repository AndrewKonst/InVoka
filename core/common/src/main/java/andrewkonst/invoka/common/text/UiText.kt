package andrewkonst.invoka.common.text

import android.content.Context
import androidx.annotation.StringRes


sealed interface UiText {


    fun get(context: Context): CharSequence


    data class Plain(val text: CharSequence) : UiText {

        override fun get(context: Context): CharSequence = text

    }


    /** String resource, requires [Context] to get [CharSequence]. */
    data class Resource(@StringRes val resourceId: Int) : UiText {

        override fun get(context: Context): CharSequence = context.getText(resourceId)

    }


    data class ResourceFormatted(
        @StringRes val resourceId: Int, private val args: List<Any>
    ) : UiText {

        constructor(@StringRes resourceId: Int, vararg args: Any) : this(resourceId, args.asList())

        override fun get(context: Context): CharSequence {
            return context.getString(resourceId, *args.toTypedArray())
        }

    }

}
