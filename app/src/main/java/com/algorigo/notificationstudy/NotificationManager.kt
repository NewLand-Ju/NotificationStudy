package com.algorigo.notificationstudy

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi

/**
 * NotificationManager
 * Created by judh on 2018. 2. 14..
 */
@RequiresApi(Build.VERSION_CODES.O)
class NotificationManager {

    companion object {
        private const val GROUP_ID_JUDH:String = "JuDH"
        private const val GROUP_NAME_JUDH:String = "JuDH_BLABLA"
        private const val GROUP_ID_JUDH2:String = "JuDH2"
        private const val GROUP_NAME_JUDH2:String = "JuDH2_BLABLA"
        const val MESSAGE:String = "Message"
        const val COMMENT:String = "Comment"
        const val NOTICE:String = "Notice"
        const val MESSAGE2:String = "Message2"
        const val COMMENT2:String = "Comment2"
        const val NOTICE2:String = "Notice2"

        fun createChannel(context:Context, id:String) {
            val notificationChannelGroup = NotificationChannelGroup(id, GROUP_NAME_JUDH+id)
            getManager(context).createNotificationChannelGroup(notificationChannelGroup)
//            val notificationChannelGroup2 = NotificationChannelGroup(GROUP_ID_JUDH2, GROUP_NAME_JUDH2)
//            getManager(context).createNotificationChannelGroup(notificationChannelGroup2)
//            getManager(context).createNotificationChannelGroups(mutableListOf(notificationChannelGroup, notificationChannelGroup2))

            val channelMessage = NotificationChannel(MESSAGE+id, MESSAGE+id, NotificationManager.IMPORTANCE_HIGH)
            channelMessage.description = "${GROUP_ID_JUDH}의 $MESSAGE"
            channelMessage.group = id
            channelMessage.lightColor = Color.GREEN
            channelMessage.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            getManager(context).createNotificationChannel(channelMessage)

            val channelComment = NotificationChannel(COMMENT+id, COMMENT+ id, NotificationManager.IMPORTANCE_LOW)
            channelComment.description = GROUP_ID_JUDH +"의 "+ COMMENT
            channelComment.group = id
            channelComment.lightColor = Color.YELLOW
            channelComment.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            getManager(context).createNotificationChannel(channelComment)

            val channelNotice = NotificationChannel(NOTICE+id, NOTICE+ id, NotificationManager.IMPORTANCE_DEFAULT)
            channelNotice.description = GROUP_ID_JUDH +"의 "+ NOTICE
            channelNotice.group = id
            channelNotice.lightColor = Color.RED
            channelNotice.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            getManager(context).createNotificationChannel(channelNotice)

//            val channelMessage2 = NotificationChannel(MESSAGE2, MESSAGE2+ GROUP_NAME_JUDH2, NotificationManager.IMPORTANCE_HIGH)
//            channelMessage2.description = GROUP_NAME_JUDH2 +"의 "+ MESSAGE2
//            channelMessage2.group = GROUP_ID_JUDH2
//            channelMessage2.lightColor = Color.GREEN
//            channelMessage2.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
//            getManager(context).createNotificationChannel(channelMessage2)
//
//            val channelComment2 = NotificationChannel(COMMENT2, COMMENT2+ GROUP_NAME_JUDH2, NotificationManager.IMPORTANCE_LOW)
//            channelComment2.description = GROUP_NAME_JUDH2 +"의 "+ COMMENT2
//            channelComment2.group = GROUP_ID_JUDH2
//            channelComment2.lightColor = Color.YELLOW
//            channelComment2.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
//            getManager(context).createNotificationChannel(channelComment2)
//
//            val channelNotice2 = NotificationChannel(NOTICE2, NOTICE2+ GROUP_NAME_JUDH2, NotificationManager.IMPORTANCE_DEFAULT)
//            channelNotice2.description = GROUP_NAME_JUDH2 +"의 "+ NOTICE2
//            channelNotice2.group = GROUP_ID_JUDH2
//            channelNotice2.lightColor = Color.RED
//            channelNotice2.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
//            getManager(context).createNotificationChannel(channelNotice2)

        }

        private fun getManager(context: Context):NotificationManager {
            return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        fun deleteChannel(context: Context, channel: String) {
            getManager(context).deleteNotificationChannel(channel)
        }

        fun sendNotification(context: Context, id: Int, channel: String, title: String, body: String, gourp:String) {
            val builder = Notification.Builder(context, channel)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true)

            getManager(context).notify(id, builder.build())
        }

        private fun getSmallIcon(): Int {
            return android.R.drawable.stat_notify_chat
        }
    }
}