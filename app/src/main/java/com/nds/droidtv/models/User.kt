package com.nds.droidtv.models

/**
 * User login information
 */
data class User(
    val userId: Int,  //User ID, from server
    val emailAddress: String,  //Registered email address
    val passwordHash: String  //MD5 hash of user's password
)