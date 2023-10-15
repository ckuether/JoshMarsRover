package com.example.joshmarsrover.common

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalFileReader @Inject constructor(@ApplicationContext val app: Context) {

    fun getRawResource(resourceId: Int): JsonElement {
        val inputStream: InputStream = app.resources.openRawResource(resourceId)
        val isr = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(isr)
        val sb = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null){
            sb.append(line)
        }
        return Gson().fromJson(sb.toString(), JsonObject::class.java)
    }
}