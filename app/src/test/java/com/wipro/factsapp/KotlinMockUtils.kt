package com.wipro.factsapp

import org.mockito.Mockito

object KotlinMockUtils {

    fun <T> any() = Mockito.any<T>() as T
}