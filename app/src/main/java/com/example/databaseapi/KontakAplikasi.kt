package com.example.databaseapi

import android.app.Application
import com.example.databaseapi.repository.AppContainer
import com.example.databaseapi.repository.KontakContainer

class KontakAplikasi : Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = KontakContainer()
    }
}