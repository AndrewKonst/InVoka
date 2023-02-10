package andrewkonst.invoka.core.api.notification

import android.os.Bundle


interface NotificationNotifier {

    fun createNotificationChannel(info: ChannelInfo)

    fun showNotification(title: String?, body: String?, extras: Bundle)

    fun clearAllNotifications()


    fun createUniqueId(): Int

    fun notificationLogo(): Int


    fun defaultChannelInfo(): ChannelInfo


    class ChannelInfo(val id: String, val name: String, val importance: Int)

}