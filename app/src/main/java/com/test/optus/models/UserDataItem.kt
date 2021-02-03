package com.test.optus.models

import com.test.optus.models.Address
import com.test.optus.models.Company
import java.io.Serializable

data class UserDataItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) : Serializable