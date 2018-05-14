package com.algorigo.notificationstudy

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.widget.Toast

/**
 * MyNotification
 * Created by judh on 2018. 2. 14..
 */
object MyNotification {

    const val MESSAGE = "Message"
    const val COMMENT = "Comment"
    const val NOTICE = "Notice"

    private fun getManager(context: Context) = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun createChannel(context: Context, id: String) =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                /**
                 * NotificationChannelGroup 생성
                 * 생성시 입력한 id를 이용하여 각각의 채널에 그룹 설정 가능
                **/
                val notificationChannelGroup = NotificationChannelGroup(id, "Group : $id")
                getManager(context).createNotificationChannelGroup(notificationChannelGroup)

                val channelMessage = NotificationChannel(MESSAGE + id, MESSAGE, NotificationManager.IMPORTANCE_HIGH)
                channelMessage.description = "${id}의 $MESSAGE"
                channelMessage.group = id // channel에 그룹 설정 (ChannelGroup 사용을 안할거면 생략가능)
                channelMessage.lightColor = Color.GREEN
                channelMessage.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                getManager(context).createNotificationChannel(channelMessage)

                val channelComment = NotificationChannel(COMMENT + id, COMMENT, NotificationManager.IMPORTANCE_LOW)
                channelComment.description = "${id}의 $COMMENT"
                channelComment.group = id
                channelComment.lightColor = Color.YELLOW
                channelComment.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                getManager(context).createNotificationChannel(channelComment)

                val channelNotice = NotificationChannel(NOTICE + id, NOTICE, NotificationManager.IMPORTANCE_DEFAULT)
                channelNotice.description = "${id}의 $NOTICE"
                channelNotice.group = id
                channelNotice.lightColor = Color.RED
                channelNotice.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                getManager(context).createNotificationChannel(channelNotice)
            } else {
                showToastMessage(context)
            }

    fun deleteChannel(context: Context, channel: String) =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                getManager(context).deleteNotificationChannel(channel)
            } else {
                showToastMessage(context)
            }

    fun sendNotification(context: Context, id: Int, channel: String, title: String, body: String) {
        val builder = NotificationCompat.Builder(context, channel)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setAutoCancel(true)

        getManager(context).notify(id, builder.build())
    }

    fun showToastMessage(context: Context) = Toast.makeText(context, "This device's OS is less than Oreo", Toast.LENGTH_SHORT).show()
}