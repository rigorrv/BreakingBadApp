package net.android.breakingbadapp.utility

import java.io.IOException

object Connection {


    @Throws(IllegalAccessException::class, IOException::class)
    fun isConnected(): Boolean {
        val command = "ping -c 1 breakingbadapi.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }
}