package com.thusee.footballevent.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thusee.footballevent.R
import retrofit2.HttpException
import java.net.UnknownHostException

@Composable
fun ErrorScreen(
    exception: Throwable,
    modifier: Modifier = Modifier,
) {
    when (exception) {
        is UnknownHostException -> {
            ErrorComponent(
                message = stringResource(id = R.string.error_no_network),
                modifier = modifier,
            )
        }

        is HttpException -> {
            val errorMessage = exception.message ?: ""

            if (errorMessage.contains("404")) {
                ErrorComponent(
                    message = stringResource(id = R.string.error_page_not_found),
                    modifier = modifier,
                )
            } else if (errorMessage.contains("500")) {
                ErrorComponent(
                    message = stringResource(id = R.string.error_server_down),
                    modifier = modifier,
                )
            } else {
                ErrorComponent(
                    message = stringResource(id = R.string.error_common),
                    modifier = modifier,
                )
            }
        }

        else -> {
            ErrorComponent(
                message = stringResource(id = R.string.error_common),
                modifier = modifier,
            )
        }
    }
}