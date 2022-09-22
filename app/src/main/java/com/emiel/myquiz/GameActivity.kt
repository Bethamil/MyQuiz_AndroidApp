package com.emiel.myquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.emiel.myquiz.model.Game
import com.emiel.myquiz.ui.theme.MyQuizTheme
import org.jsoup.Jsoup


class GameActivity : ComponentActivity() {
    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gfgPolicy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(gfgPolicy)
            game = Game()
            MyQuizTheme {
//                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizGame()
                }
            }
        }
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun QuizGame() {
        val qNumber = remember {
            mutableStateOf(1)
        }
        val mContext = LocalContext.current

        fun buttonFunction(answerNumber: Int) {
            if (qNumber.value < 10) {
                qNumber.value++; game.nextQuestion(game.allAnswers[answerNumber])
            } else {
                game.checkAnswer(game.allAnswers[answerNumber])

                val value = game.score
                val i = Intent(mContext, GameResultActivity::class.java)
                i.putExtra("key", value)
                startActivity(i)
            }
        }

        val spacingSize = 15.dp
        val buttonHeight = 60.dp

        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = "Score: " + game.score) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
//            HACK UPDATE
            qNumber.value
        }) { values ->
            Column(
                modifier = Modifier
                    .padding(values).padding(10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(spacingSize))


                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .defaultMinSize(minHeight = 100.dp)
                        .background(MaterialTheme.colorScheme.tertiary)
                        .padding(10.dp)
                ) {
                    Text(
                        text = qNumber.value.toString() + ". " + Jsoup.parse(
                            game.currentQuestion?.question ?: String()
                        ).text(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 30.sp,
                        lineHeight = 40.sp,
                        color = MaterialTheme.colorScheme.background
                    )
                }



                Column(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally) {


                    Button(
                        onClick = { buttonFunction(0) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = buttonHeight)

                    ) {
                        Text(text = game.allAnswers[0], fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(spacingSize))
                    Button(
                        onClick = { buttonFunction(1) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = buttonHeight)


                    ) {
                        Text(text = game.allAnswers[1], fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(spacingSize))


                    Button(
                        onClick = { buttonFunction(2) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = buttonHeight)


                    ) {
                        Text(text = game.allAnswers[2], fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(spacingSize))


                    Button(
                        onClick = { buttonFunction(3) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = buttonHeight)


                    ) {
                        Text(text = game.allAnswers[3], fontSize = 20.sp)
                    }
                }
            }
        }
    }
}