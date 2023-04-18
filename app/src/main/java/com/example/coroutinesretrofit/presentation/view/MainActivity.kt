package com.example.coroutinesretrofit.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.coroutinesretrofit.presentation.viewmodel.UserNameViewModel
import com.example.coroutinesretrofit.ui.theme.*
import com.example.coroutinesretrofit.utils.BaseResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {
    private val viewModel: UserNameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoroutinesRetrofitTheme {
                LaunchedEffect(Unit) {
                    viewModel.fetchUserNames()
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val uiState = viewModel.uiState
                    when (uiState.value) {
                        is BaseResult.Loading -> {
                            ShowLoadingScreen(modifier = Modifier.fillMaxSize())
                        }
                        is BaseResult.Success -> {
                            ShowSuccessScreen(
                                (uiState.value as BaseResult.Success).data,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        is BaseResult.Error -> {
                            ShowErrorScreen(Modifier.fillMaxSize()) {
                                viewModel.fetchUserNames()
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ShowLoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(PROGRESS_BAR_SIZE))
    }
}

@Composable
fun ShowSuccessScreen(userNameList: List<String>, modifier: Modifier = Modifier) {
    userNameList.forEach {
        // create a row
    }
}

@Composable
fun ShowErrorScreen(modifier: Modifier = Modifier, onRetryClick: () -> Unit) {
    Column(
        modifier = modifier.padding(BASE_PADDING),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Something went wrong. Please click on the button to retry.",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = BASE_TEXT_SIZE,
            color = Color(0xFF333333),
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(BASE_PADDING_2X))
        Button(
            onClick = { onRetryClick.invoke() },
            modifier = Modifier
                .fillMaxWidth()
                .height(BASE_BUTTON_SIZE)
                .clip(RoundedCornerShape(BASE_PADDING)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            shape = RoundedCornerShape(BASE_PADDING)
        ) {
            Text(
                text = "RETRY",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoroutinesRetrofitTheme {

    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    CoroutinesRetrofitTheme {
        ShowLoadingScreen(modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    CoroutinesRetrofitTheme {
        ShowErrorScreen(modifier = Modifier.fillMaxSize()) {}
    }
}