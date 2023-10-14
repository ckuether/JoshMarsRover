package com.example.joshmarsrover.api

import com.example.joshmarsrover.data.model.Rover
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class RoversDeserializer: JsonDeserializer<List<Rover>> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Rover> {
        val roverListObj = json.asJsonObject
        val roverListArr = roverListObj["rovers"].asJsonArray
        return Gson().fromJson(roverListArr, object: TypeToken<List<Rover>>(){}.type)
    }
}