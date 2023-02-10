package andrewkonst.invoka.utils

import andrewkonst.invoka.common.uri.mimeType
import andrewkonst.invoka.core.api.config.Config
import andrewkonst.invoka.impl.ExternalRouterImpl
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.FileProvider
import java.io.File


internal class ExternalRouterCommandHandler(
    private val activity: Activity, private val config: Config
) {

    fun handle(command: ExternalRouterImpl.Command) = when (command) {
        is ExternalRouterImpl.Command.OpenFile -> openFile(command.file)
        is ExternalRouterImpl.Command.OpenLink -> openBrowser(command.link)
    }

    private fun openBrowser(link: String) {
        val uri = Uri.parse(link)
        try {
            val builder = CustomTabsIntent.Builder()
            builder.setShareState(CustomTabsIntent.SHARE_STATE_ON)
            val schemeParams = CustomTabColorSchemeParams.Builder()
                .build()
            builder.setDefaultColorSchemeParams(schemeParams)
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(activity, uri)
        } catch (e: Exception) {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = uri
            if (browserIntent.resolveActivity(activity.packageManager) != null) {
                activity.startActivity(browserIntent)
            } else {
                Log.e("", "Not resolve openBrowser action")
            }
        }
    }

    private fun openFile(file: File) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            val fileUri =
                FileProvider.getUriForFile(activity, config.applicationId, file)
            intent.setDataAndType(fileUri, fileUri.mimeType(activity))
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            val chooserIntent = Intent.createChooser(intent, null)
            chooserIntent.flags =
                Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_TASK
            chooserIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            activity.startActivity(chooserIntent)
        } catch (e: Exception) {
            Log.e("", "", e)
        }
    }

}
