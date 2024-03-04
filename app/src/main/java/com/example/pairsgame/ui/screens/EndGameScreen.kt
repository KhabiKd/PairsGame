package com.example.pairsgame.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pairsgame.Container
import com.example.pairsgame.R
import com.example.pairsgame.ui.GameState
import com.example.pairsgame.ui.GameViewModel
import com.example.pairsgame.ui.theme.PairsGameTheme
import com.example.pairsgame.ui.theme.Purple40

@Composable
fun EndGameScreen(
    onHomeButtonClicked: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.trophy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
        )

        Text(
            text = stringResource(id = R.string.congratulation),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(id = R.string.you_won),
            fontSize = 24.sp
        )

        Box(
            modifier = Modifier
                .width(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
                .padding(8.dp)
            ,
            contentAlignment = Alignment.Center
        ) {
            Prize()
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(containerColor = Purple40),
                shape = CircleShape,
                modifier = Modifier.height(80.dp).padding(end = 12.dp)
            ) {
                Text(
                    text = "Double Reward",
                    fontSize = 21.sp,
                    textAlign = TextAlign.Center,
                )
            }

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
                    .padding(8.dp)
                    .clickable { onHomeButtonClicked() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun Prize(modifier: Modifier = Modifier) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.coin),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
        )
        Text(
            text = Container.prize.toString(),
            fontSize = 24.sp
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun EndGameScreenPreview() {
    PairsGameTheme {
        EndGameScreen() {}
    }
}