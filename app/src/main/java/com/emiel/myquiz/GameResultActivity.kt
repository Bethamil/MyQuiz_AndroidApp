package com.emiel.myquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.nfc.cardemulation.CardEmulation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emiel.myquiz.RoomDAO.PlayerDAO
import com.emiel.myquiz.ui.theme.MyQuizTheme

class GameResultActivity : ComponentActivity() {
    var scoreGame = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
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
            TopAppBar(
                title = { Text(text = "Score: ") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }) { values ->
            Column(
                modifier = Modifier
                    .padding(values)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Card(
                    modifier = Modifier
                        .padding(10.dp).fillMaxWidth()
                        ,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = scoreGame.toString(),
                        fontSize = 110.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(10.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    var textName by remember { mutableStateOf("") }

                    TextField(
                        modifier = Modifier
                            .padding(10.dp).size(width = 300.dp, height = 80.dp)
                        ,
                        value = textName,
                        onValueChange = { textName = it },
                        label = { Text("Name", ) },
                        textStyle = TextStyle(fontSize = 30.sp)
                    )

                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier.padding(10.dp).size(width = 150.dp, height = 60.dp)) {
                        Text(text = "Save", )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(values)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = {
                    mContext.startActivity(
                        Intent(
                            mContext,
                            MainActivity::class.java
                        )
                    )
                },
                    modifier = Modifier
                        .width(300.dp)
                        .height(80.dp)) {
                    Text(text = "Menu")
                }


//                Box(
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
//                        .fillMaxWidth(1f)
//                        .clip(RoundedCornerShape(10.dp))
//                        .background(MaterialTheme.colorScheme.tertiary)
//                        .padding(10.dp)
//                ) {
//                    Text(
//                        modifier = Modifier.align(Alignment.Center),
//                        text = scoreGame.toString(),
//                        fontSize = 110.sp,
//                        color = MaterialTheme.colorScheme.background
//                    )
//                }
            }
        }
    }
}






