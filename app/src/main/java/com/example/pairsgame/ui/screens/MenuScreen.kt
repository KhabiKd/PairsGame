package com.example.pairsgame.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pairsgame.Container
import com.example.pairsgame.R
import com.example.pairsgame.ui.theme.PairsGameTheme
import com.example.pairsgame.ui.theme.Purple40

@Composable
fun MenuScreen(
    onPlayButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Coins(modifier = Modifier.align(Alignment.End))

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(180.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            Text(
                text = "Game Logo №1",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                lineHeight = 35.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onPlayButtonClicked() },
            colors = ButtonDefaults.buttonColors(containerColor = Purple40),
            shape = CircleShape,
            modifier = Modifier
                .width(150.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "PLAY",
                fontSize = 21.sp,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .padding(end = 20.dp, bottom = 32.dp)
                .size(80.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
                .align(Alignment.End)
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_privacy_tip_24),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
            )
        }

    }
}

@Composable
fun Coins(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(end = 20.dp, top = 32.dp)
        ,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.coin),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
        )
        Text(
            text = Container.budget.toString(),
            fontSize = 24.sp
        )
    }
}

@Preview(name = "MenuScreen", showSystemUi = true)
@Composable
fun MenuScreenPreview() {
    PairsGameTheme {
        MenuScreen() {}
    }
}