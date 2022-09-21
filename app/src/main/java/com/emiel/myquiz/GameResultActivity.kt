package com.emiel.myquiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emiel.myquiz.ui.theme.MyQuizTheme

class GameResultActivity : ComponentActivity() {
    var scoreGame = 0

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val extras = intent.extras
            if (extras != null) {
                scoreGame = extras.getInt("key")
            }
            MyQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameResults()

                }
            }

        }
    }


    @Preview
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GameResults() {
        val mContext = LocalContext.current
        Scaffold(topBar = {
            SmallTopAppBar(
                title = { Text(text = "Score: ") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }) {
            Column(
                modifier = Modifier.padding(10.dp).fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(10.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = scoreGame.toString(),
                        fontSize = 110.sp
                    )
                }
                Spacer(modifier = Modifier.height(300.dp))
                Button(
                    onClick = {
                        mContext.startActivity(
                            Intent(
                                mContext,
                                MainActivity::class.java
                            )
                        )
                    },
                    modifier = Modifier
                        .width(300.dp)
                        .weight(1f, true)


                ) {
                    Text(text = "Menu", fontSize = 30.sp)
                }
            }


        }
    }


    @Composable
    fun Modifier.weight(
        @FloatRange(from = 0.0, to = 3.4e38, fromInclusive = false)
        weight: Float,
        fill: Boolean = true
    ) {
    }
}

