package com.emiel.myquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.emiel.myquiz.RoomDAO.AppDatabase
import com.emiel.myquiz.model.Player
import com.emiel.myquiz.ui.theme.MyQuizTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameResultActivity : ComponentActivity() {
    var scoreGame = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun createDB(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "scores"
            ).fallbackToDestructiveMigration().build()
        }

        val db   = createDB(applicationContext = applicationContext)



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
                    GameResults(db)

                }
            }

        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GameResults(db : AppDatabase) {
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
                    .padding(10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .defaultMinSize(minHeight = 100.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(10.dp),
                    contentAlignment = Alignment.Center


//                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
//                    shape = MaterialTheme.shapes.large
                ) {
                    Column(modifier = Modifier
                        .padding(5.dp)
                        ,
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,) {
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
                                .padding(10.dp)
                                .size(width = 300.dp, height = 80.dp),
                            value = textName,
                            onValueChange = { textName = it },
                            label = { Text("Name") },
                            textStyle = TextStyle(fontSize = 30.sp),
                            singleLine = true,
                            maxLines = 1,

                        )

                        Button(
                            onClick = {
                                GlobalScope.launch(Dispatchers.IO) {
                                    if (textName.trim() != "") {
                                        db.playerDAO()
                                            .insert(Player(name = textName.trim(), score = scoreGame))
                                        mContext.startActivity(
                                            Intent(
                                                mContext,
                                                MainActivity::class.java
                                            )
                                        )
                                    }
                                }
                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .size(width = 150.dp, height = 60.dp)
                        ) {
                            Text(text = "Save")
                        }
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
                        .height(80.dp)
                        .padding(10.dp)
                ) {
                    Text(text = "Menu")
                }
            }
        }
    }
}






