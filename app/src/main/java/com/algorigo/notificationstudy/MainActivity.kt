package com.algorigo.notificationstudy

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_create.setOnClickListener {
            NotificationManager.createChannel(this, if (edit_group_id.text.isNotEmpty())edit_group_id.text.toString() else "default")
        }

        btn_message.setOnClickListener { NotificationManager.sendNotification(this, 1, NotificationManager.MESSAGE+edit_group_id.text.toString(), "MESSAGE_TITLE", "MESSAGE_BODY", edit_group_id.text.toString()) }
        btn_comment.setOnClickListener { NotificationManager.sendNotification(this, 2, NotificationManager.COMMENT+edit_group_id.text.toString(), "COMMENT_TITLE", "COMMENT_BODY", edit_group_id.text.toString()) }
        btn_notice.setOnClickListener { NotificationManager.sendNotification(this, 3, NotificationManager.NOTICE+edit_group_id.text.toString(), "NOTICE_TITLE", "NOTICE_BODY", edit_group_id.text.toString()) }

        btn_delete_comment.setOnClickListener { NotificationManager.deleteChannel(this, NotificationManager.COMMENT+edit_group_id.text.toString()) }
        btn_setting_message.setOnClickListener { goToNotificationSettings(NotificationManager.MESSAGE+edit_group_id.text.toString()) }
        btn_setting.setOnClickListener { goToNotificationSettings() }
    }

    /**
     * Send Intent to load system Notification Settings for this app.
     */
    private fun goToNotificationSettings() {
        val i = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
        i.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        startActivity(i)
    }

    /**
     * Send intent to load system Notification Settings UI for a particular channel.
     *
     * @param channel Name of channel to configure
     */
    private fun goToNotificationSettings(channel: String) {
        val i = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
        i.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        i.putExtra(Settings.EXTRA_CHANNEL_ID, channel)
        startActivity(i)
    }
}
