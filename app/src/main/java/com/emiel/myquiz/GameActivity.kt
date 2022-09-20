package com.emiel.myquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emiel.myquiz.ui.theme.MyQuizTheme

class GameActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(topBar = {
                        SmallTopAppBar(
                            title = { Text(text = "Quiz") },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                MaterialTheme.colorScheme.surfaceVariant,
                                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }) {
                        Game()

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyQuizTheme {
    }
}

@Preview
@Composable
fun Game() {
    val spacingSize = 15.dp
    val buttonHeight = 50.dp
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            Image(painter = painterResource(id = R.drawable.quiz_logo), contentDescription = null, modifier = Modifier.size(100.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Cyan)
                    .padding(10.dp)
            ) {
                Text(text = "0", fontSize = 30.sp)
            }
        }
        Spacer(modifier = Modifier.height(spacingSize))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(1f)
                .height(300.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Cyan)
                .padding(10.dp)
        ) {
            Text(text = "ssd", fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {/*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)

        ) {
            Text(text = "A", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(spacingSize))
        Button(
            onClick = {/*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)

        ) {
            Text(text = "B", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(spacingSize))


        Button(
            onClick = {/*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)

        ) {
            Text(text = "C", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(spacingSize))


        Button(
            onClick = {/*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)

        ) {
            Text(text = "D", fontSize = 20.sp)
        }

    }
}