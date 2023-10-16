package com.example.dineshassignment.model

import android.content.Context
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException


class LocalRepo {


    @ExperimentalSerializationApi
    fun retrieveDataFromJsonFile(context: Context): MyData? {
        return try {
            val inputStream = context.assets.open("my_data.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            Json.decodeFromString<MyData>(jsonString)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}