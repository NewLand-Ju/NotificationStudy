package com.algorigo.notificationstudy

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_create.setOnClickListener { MyNotification.createChannel(this, if (edit_group_id.text.isNotEmpty()) edit_group_id.text.toString() else "Default") }
        btn_message.setOnClickListener { MyNotification.sendNotification(this, 1, MyNotification.MESSAGE + edit_group_id.text.toString(), "MESSAGE_TITLE", "MESSAGE_BODY") }
        btn_comment.setOnClickListener { MyNotification.sendNotification(this, 2, MyNotification.COMMENT + edit_group_id.text.toString(), "COMMENT_TITLE", "COMMENT_BODY") }
        btn_notice.setOnClickListener { MyNotification.sendNotification(this, 3, MyNotification.NOTICE + edit_group_id.text.toString(), "NOTICE_TITLE", "NOTICE_BODY") }

        btn_delete_comment.setOnClickListener { MyNotification.deleteChannel(this, MyNotification.COMMENT + edit_group_id.text.toString()) }
        btn_setting_message.setOnClickListener { goToNotificationSettings(MyNotification.MESSAGE + edit_group_id.text.toString()) }
        btn_setting.setOnClickListener { goToNotificationSettings() }
    }

    private fun goToNotificationSettings(): Any =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                    startActivity(this)
                }
            } else {
                MyNotification.showToastMessage(this)
            }


    private fun goToNotificationSettings(channel: String): Any =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                    putExtra(Settings.EXTRA_CHANNEL_ID, channel)
                    startActivity(this)
                }
            } else {
                MyNotification.showToastMessage(this)
            }
}
