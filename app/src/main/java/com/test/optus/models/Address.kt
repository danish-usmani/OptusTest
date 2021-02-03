package com.test.optus.models

import com.test.optus.models.Geo
import java.io.Serializable

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
) : Serializable