package com.test.optus.models

import java.io.Serializable

data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
) : Serializable