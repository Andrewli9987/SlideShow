package com.example.slideshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slideshow.ui.theme.SlideShowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideShowTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SlideshowScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SlideshowScreen(modifier: Modifier = Modifier) {

    val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5
    )

    val captions = listOf(
        "This is the first picture.",
        "This is the second picture.",
        "This is the third picture.",
        "This is the fourth picture.",
        "This is the fifth picture."
    )

    var currentIndex by remember { mutableStateOf(0) }
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = captions[currentIndex],
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Text(
            text = captions[currentIndex],
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Picture ${currentIndex + 1} of ${images.size}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                currentIndex = if (currentIndex == 0) {
                    images.size - 1
                } else {
                    currentIndex - 1
                }
            }) {
                Text("Back")
            }

            Button(onClick = {
                currentIndex = if (currentIndex == images.size - 1) {
                    0
                } else {
                    currentIndex + 1
                }
            }) {
                Text("Next")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Enter picture number") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    val number = inputText.toIntOrNull()
                    if (number != null && number in 1..images.size) {
                        currentIndex = number - 1
                    }
                }
            ) {
                Text("Go")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SlideshowPreview() {
    SlideShowTheme {
        SlideshowScreen()
    }
}
