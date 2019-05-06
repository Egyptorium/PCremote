package com.android.pcremote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import kotlinx.android.synthetic.main.activity_main.*
import java.net.Socket

class MainActivity : AppCompatActivity() {
    val port = 53210
    val server = "192.168.43.201"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)

            button_raise_volume.setOnClickListener{
                sendCommand("XF86AudioLowerVolume")
            }

            button_lower_volume.setOnClickListener{
                sendCommand("XF86AudioRiseVolume")
            }

            button_next.setOnClickListener{
                sendCommand("XF86AudioNext")
            }

            button_prev.setOnClickListener{
                sendCommand("XF86AudioPrev")
            }

            button_play.setOnClickListener{
                sendCommand("XF86AudioPlay")
            }

            button_ok.setOnClickListener{
                sendCommand("Return")
            }

            button_up.setOnClickListener{
                sendCommand("Up")
            }

            button_down.setOnClickListener{
                sendCommand("Down")
            }

            button_left.setOnClickListener{
                sendCommand("Left")
            }

            button_right.setOnClickListener{
                sendCommand("Right")
            }

        }


    }
    private fun sendCommand(command: String): Int {
        try {
            val soc = Socket(server, port)
            val writer = soc.getOutputStream()
            writer.write(command.toByteArray())
            writer.flush()
            soc.close()
            return 0
        } finally {
            return -1
        }

    }

}
