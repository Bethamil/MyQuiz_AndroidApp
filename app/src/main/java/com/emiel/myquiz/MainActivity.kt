package com.emiel.myquiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emiel.myquiz.ui.theme.MyQuizTheme

class MainActivity : ComponentActivity() {
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
                            title = { Text(text = "Menu") },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                MaterialTheme.colorScheme.surfaceVariant,
                                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    })
                    {
                        Menu()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Menu() {
    val mContext = LocalContext.current
    Column(
        modifier = Modifier.padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Image(painter = painterResource(id = R.drawable.quiz_logo), contentDescription = null)
        Spacer(modifier = Modifier.height(80.dp))

        Button(
            onClick = {mContext.startActivity(Intent(mContext, GameActivity::class.java))},
            modifier = Modifier
                .width(300.dp)
                .height(80.dp)

        ) {
            Text(text = "Start game", fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(300.dp)
                .height(80.dp)

        ) {
            Text(text = "High Scores", fontSize = 30.sp)
        }
    }
}