package com.example.joshmarsrover.api

import com.example.joshmarsrover.data.model.Photo
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PhotosDeserializer: JsonDeserializer<List<Photo>> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Photo> {
        val photosJsonObj = json.asJsonObject
        val photosJsonArr = photosJsonObj["photos"].asJsonArray
        return Gson().fromJson(photosJsonArr, object: TypeToken<List<Photo>>(){}.type)
    }
}