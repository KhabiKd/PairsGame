package com.example.pairsgame.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import androidx.navigation.NavController
import com.example.pairsgame.Container
import com.example.pairsgame.R
import com.example.pairsgame.data.DataSource
import com.example.pairsgame.data.Item
import com.example.pairsgame.ui.GameState
import com.example.pairsgame.ui.GameViewModel
import com.example.pairsgame.ui.theme.PairsGameTheme
import kotlinx.coroutines.delay
import java.lang.Integer.max

@Composable
fun GameSceneScreen(gameViewModel: GameViewModel = viewModel(), navController: NavController) {

    val gameState by gameViewModel.gameState
    gameViewModel.setNavController(navController)


    LaunchedEffect(key1 = gameState.score) {
        while (true) {
            delay(1000)
            gameViewModel.updateTimer()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, end = 16.dp)
        ) {
            Timer(gameState.elapsedTime)
            CurrPrize(gameState)
        }

        Text(
            text = "! less time, more reward",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 32.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        GameGrid(
            gameState.cells, gameViewModel::onCellClicked
        )

        Text(
            text = stringResource(id = R.string.description),
            textAlign = TextAlign.Center,
            lineHeight = 16.sp,
            modifier = Modifier.align(CenterHorizontally).padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

    }
}

@Composable
fun Timer(elapsedTime: Int) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(IntrinsicSize.Max)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(Color.LightGray)
                .padding(start = 74.dp, end = 32.dp)
                .align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = formatTime(elapsedTime),
                fontSize = 24.sp,
                modifier = Modifier
            )
        }

        Image(
            painter = painterResource(id = R.drawable.timer_icon_153935),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterStart)
        )
    }
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return "%02d:%02d".format(minutes, remainingSeconds)
}

@Composable
fun CurrPrize(gameState: GameState, modifier: Modifier = Modifier) {
    val maxReward = 100
    val minReward = 10
    val elapsedSeconds = gameState.elapsedTime
    val timeOverLimit = max(0, elapsedSeconds - 20)
    val currentReward = max(minReward, maxReward - timeOverLimit * 5)
    Container.prize = currentReward

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.coin),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
        )
        Text(
            text = currentReward.toString(),
            fontSize = 24.sp
        )
    }
}

@Composable
fun GameGrid(
    gameData: List<Item>,
    onItemClick: (Item) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        items(gameData) { item ->
            Cell(
                item = item,
                onItemClick = { onItemClick(item) },
            )
        }
    }
}

@Composable
fun Cell(item: Item, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(if (item.checked) Color.LightGray else Color.Gray)
            .clickable { onItemClick() }
    ) {
        if (item.checked) {
            Image(
                painter = painterResource(id = item.imgSrc),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun TimerPreview() {
    PairsGameTheme {
        Timer(10)
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun GameSceneScreenPreview() {
//    PairsGameTheme {
//        GameSceneScreen()
//    }
//}