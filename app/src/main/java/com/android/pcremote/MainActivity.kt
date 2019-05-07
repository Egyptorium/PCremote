package com.android.pcremote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*
import java.net.Socket

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)

            button_raise_volume.setOnClickListener{
                sendCommand("XF86AudioRaiseVolume")
            }

            button_lower_volume.setOnClickListener{
                sendCommand("XF86AudioLowerVolume")
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

            button_raise_master_volume.setOnClickListener{
                sendCommand("5%+")
            }

            button_lower_master_volume.setOnClickListener{
                sendCommand("5%-")
            }

            button_k.setOnClickListener{
                sendCommand("k")
            }

            switch_control.setOnClickListener{
                if (switch_control.isChecked){
                    this.edit_ip_address.isEnabled = true
                    edit_port.isEnabled = true
                } else {
                    edit_ip_address.isEnabled = false
                    edit_port.isEnabled = false
                }
            }
        }


    }
    private fun sendCommand(command: String): Int {
        try {
            val server = edit_ip_address.text.toString()
            val port = edit_port.text.toString().toInt()
            val soc = Socket(server, port)
            val writer = soc.getOutputStream()
            writer.write(command.toByteArray())
            writer.flush()
            soc.close()
            this.text_status.setText(R.string.connected_to_server)
            return 0
        } catch (e: Exception) {
            this.text_status.setText(R.string.disconnected_from_server)
            return -1
        }

    }

}
