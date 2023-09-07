package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var id by remember { mutableStateOf(1) }

    when (id) {
        1 -> ArtSpace(
            imageId = R.drawable.cat_inside_helmet,
            imageDescription = R.string.cat_inside_helmet_description,
            title = R.string.cat_inside_helmet_title,
            artist = "No Name",
            year = "2023",
            onPreviousClick = { id = 5 },
            onNextClick = { id = 2 }
        )
        2 -> ArtSpace(
            imageId = R.drawable.cute_cat,
            imageDescription = R.string.cute_cat_description,
            title = R.string.cute_cat_title,
            artist = "No Name",
            year = "2023",
            onPreviousClick = { id = 1 },
            onNextClick = { id = 3 }
        )
        3 -> ArtSpace(
            imageId = R.drawable.hiding_cat,
            imageDescription = R.string.hiding_cat_description,
            title = R.string.hiding_cat_title,
            artist = "No Name",
            year = "2023",
            onPreviousClick = { id = 2 },
            onNextClick = { id = 4 }
        )
        4 -> ArtSpace(
            imageId = R.drawable.laying_cat,
            imageDescription = R.string.laying_cat_description,
            title = R.string.laying_cat_title,
            artist = "No Name",
            year = "2023",
            onPreviousClick = { id = 3 },
            onNextClick = { id = 5 }
        )
        else -> ArtSpace(
            imageId = R.drawable.sleepy_cat,
            imageDescription = R.string.sleepy_cat_description,
            title = R.string.sleepy_cat_title,
            artist = "No Name",
            year = "2023",
            onPreviousClick = { id = 4 },
            onNextClick = { id = 1 }
        )
    }

}

@Composable
fun ArtSpace(
    @DrawableRes imageId: Int,
    @StringRes imageDescription: Int,
    @StringRes title: Int,
    artist: String,
    year: String,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        Spacer(modifier = Modifier.weight(1.0f))
        ArtworkWall(
            imageId = imageId,
            imageDescription = imageDescription
        )
        Spacer(modifier = Modifier.weight(1.0f))
        ArtworkDescriptor(
            title = title,
            artist = artist,
            year = year
        )
        Spacer(modifier = Modifier.weight(0.5f))
        DisplayController(
            onPreviousClick = onPreviousClick,
            onNextClick = onNextClick
        )
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes imageId: Int,
    @StringRes imageDescription: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shadowElevation = 8.dp,
    ) {
        androidx.compose.foundation.Image(
            painter = painterResource(imageId),
            contentDescription = stringResource(imageDescription),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(3f / 4f)
                .padding(32.dp)
        )
    }
}

@Composable
fun ArtworkDescriptor(
    @StringRes title: Int,
    artist: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.headlineLarge
        )
        Row(
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = artist,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = " ($year)",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1.0f)

        ) {
            Text("Previous")
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1.0f)
        ) {
            Text("Next")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}