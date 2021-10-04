package com.example.firstapp

import com.google.firebase.database.Exclude

data class userdata(
    var name: String? = "",
    var email: String? = ""

) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "email" to email,
        )
    }
}