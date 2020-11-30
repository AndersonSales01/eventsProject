package com.example.anderson.eventsproject.app.util

import kotlinx.coroutines.*
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class ConnectionTest: CoroutineScope {

    // Um job representa uma ou conjunto de tarefas em backgroud.
    private val job = Job()

    override val coroutineContext
    get() = Dispatchers.IO + job
    companion object {

        suspend fun verifyConectation(): Boolean {
            var isConnect = false
            coroutineScope {
                async {
                    try {
                        val sock = Socket()
                        sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                        sock.close()
                        isConnect = true
                    } catch (e: IOException) {
                        isConnect = false
                    }
                }.await()
            }
            return isConnect
        }

    }

}