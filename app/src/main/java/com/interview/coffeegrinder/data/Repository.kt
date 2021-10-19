package com.interview.coffeegrinder.data

import com.interview.coffeegrinder.api.ApiHelper
import javax.inject.Inject

class Repository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getCoffees() = apiHelper.getCoffees().body() ?: listOf()
    suspend fun getCoffee(id: String) = apiHelper.getCoffee(id).body()
}
