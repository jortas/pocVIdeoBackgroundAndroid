package com.example.pocforvideos

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (findViewById<View>(R.id.video) as VideoView).apply {
            setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.sample))

            setOnCompletionListener {
                start()
            }

            setOnPreparedListener { mediaPlayer ->
                val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight.toFloat()
                val screenRatio = width / height.toFloat()
                val newScaleX = videoRatio / screenRatio
                if (scaleX >= 1f) {
                    scaleX = newScaleX
                } else {
                    scaleY = 1f / newScaleX
                }
                start();
            }
        }
    }
}