package com.arepasinqueso.miprimerlogin.data

data class LoginResponse(
    val id : Int,
    val username : String,
    val email : String,
    val firstName : String,
    val lastName : String,
    val accessToken : String,
    val refreshToken : String,
)
