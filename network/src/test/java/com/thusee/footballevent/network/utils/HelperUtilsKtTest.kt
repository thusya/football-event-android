package com.thusee.footballevent.network.utils

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HelperUtilsKtTest {

    @Test
    fun `handleRequest returns success on successful request`() = runTest {
        val expectedValue = "Test"
        val requestFunc: suspend () -> String = mockk()
        coEvery { requestFunc.invoke() } returns expectedValue

        val result = handleRequest(requestFunc)

        Assertions.assertTrue(result.isSuccess)
        Assertions.assertEquals(expectedValue, result.getOrNull())
    }

    @Test
    fun `handleRequest returns failure on HttpException`() = runTest {
        val exception = Exception("", Throwable())
        val requestFunc: suspend () -> String = mockk()
        coEvery { requestFunc.invoke() } throws exception

        val result = handleRequest(requestFunc)

        Assertions.assertTrue(result.isFailure)
    }
}