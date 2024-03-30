package com.thusee.footballevent.ui.settings

import android.content.Context
import com.thusee.footballevent.ui.utils.MainCoroutineExtension
import com.thusee.footballevent.ui.utils.WebUtil
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class SettingsViewModelTest {

    private val webUtil: WebUtil = mockk(relaxed = true)
    private lateinit var settingsViewModel: SettingsViewModel

    @BeforeEach
    fun setUp() {
        settingsViewModel = SettingsViewModel(webUtil)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `onCheckedChange to false should update notificationEnabled to false`() = runTest {
        settingsViewModel.onCheckedChange(false)

        assertEquals(false, settingsViewModel.notificationEnabled.value)
    }

    @Test
    fun `onCheckedChange to true should update notificationEnabled to true`() = runTest {
        settingsViewModel.onCheckedChange(true)

        assertEquals(true, settingsViewModel.notificationEnabled.value)
    }

    @Test
    fun `openWebView should call WebUtil's launchCustomTab with correct context and URL`() =
        runTest {
            val url = "https://test.com"
            val context: Context = mockk(relaxed = true)

            settingsViewModel.openWebView(url, context)

            verify { webUtil.launchCustomTab(context, url) }
        }
}