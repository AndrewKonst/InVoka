package andrewkonst.invoka.impl

import andrewkonst.invoka.ui.AppActivity
import andrewkonst.invoka.R
import andrewkonst.invoka.core.api.notification.NotificationNotifier
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import javax.inject.Inject


internal class NotificationNotifierImpl @Inject constructor(
    private val context: Context,
) : NotificationNotifier {


    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }


    override fun createNotificationChannel(info: NotificationNotifier.ChannelInfo) {
        val channel = NotificationChannel(info.id, info.name, info.importance)
        notificationManager.createNotificationChannel(channel)
    }

    override fun showNotification(title: String?, body: String?, extras: Bundle) {
        if (!areNotificationsEnabled()) return

        val channel = defaultChannelInfo()
        createNotificationChannel(channel)
        val color = ContextCompat.getColor(
            context, R.color.push_notifications_notification_icon_color
        )
        val notification = NotificationCompat.Builder(context, channel.id)
            .setSmallIcon(notificationLogo())
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setOnlyAlertOnce(true)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setColor(color)
            .setAutoCancel(true)
            .setExtras(extras)
            .setContentIntent(createPendingIntent(extras))
            .build()

        notificationManager.notify(createUniqueId(), notification)

    }

    override fun clearAllNotifications() {
        notificationManager.cancelAll()
    }


    override fun createUniqueId(): Int = (System.currentTimeMillis() and 0xfffffff).toInt()

    override fun notificationLogo(): Int = andrewkonst.invoka.core.ui.R.drawable.circle


    override fun defaultChannelInfo(): NotificationNotifier.ChannelInfo {
        return NotificationNotifier.ChannelInfo(
            context.getString(R.string.push_notifications_notification_default_channel_id),
            context.getString(R.string.push_notifications_notification_default_channel_name),
            NotificationManager.IMPORTANCE_HIGH,
        )
    }

    private fun createPendingIntent(extra: Bundle?): PendingIntent {
        val appIntent = Intent(context, AppActivity::class.java)
        extra?.let { appIntent.putExtras(it) }
        appIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        return PendingIntent.getActivity(
            context,
            APP_LAUNCH_REQUEST_CODE,
            appIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun areNotificationsEnabled(): Boolean {
        return NotificationManagerCompat.from(context).areNotificationsEnabled()
    }

    private companion object {
        private const val APP_LAUNCH_REQUEST_CODE = 4321
    }

}
