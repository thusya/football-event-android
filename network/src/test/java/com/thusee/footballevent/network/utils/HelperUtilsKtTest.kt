package com.thusee.footballevent.network.utils

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HelperUtilsKtTest {

    @Test
    fun `handleRequest returns success on successful request`() = runTest {
        val expectedValue = "Test"
        val requestFunc: suspend () -> String = mockk()
        coEvery { requestFunc.invoke() } returns expectedValue

        val result = handleRequest(requestFunc)

        TestCase.assertTrue(result.isSuccess)
        TestCase.assertEquals(expectedValue, result.getOrNull())
    }

    @Test
    fun `handleRequest returns failure on HttpException`() = runTest {
        val exception = Exception("", Throwable())
        val requestFunc: suspend () -> String = mockk()
        coEvery { requestFunc.invoke() } throws exception

        val result = handleRequest(requestFunc)

        TestCase.assertTrue(result.isFailure)
    }
}