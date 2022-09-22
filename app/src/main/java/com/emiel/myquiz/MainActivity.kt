package com.emiel.myquiz

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.emiel.myquiz.RoomDAO.AppDatabase
import com.emiel.myquiz.model.Player
import com.emiel.myquiz.ui.theme.MyQuizTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun createDB(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "scores"
            ).fallbackToDestructiveMigration().build()
        }

        val db = createDB(applicationContext = applicationContext)
        var listPlayer: List<Player>
        var topTen = StringBuilder()


        GlobalScope.launch(Dispatchers.IO) {
            listPlayer = db.playerDAO().getTopTen()
            Collections.sort(listPlayer)
            var count = 0
            for (player: Player in listPlayer) {
                count++
                topTen.append("$count. ${player.name} - ${player.score} \n")
            }
        }

        setContent {
            MyQuizTheme {
//                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(topBar = {
                        TopAppBar(
                            title = { Text(text = "Menu") },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                MaterialTheme.colorScheme.surfaceVariant,
                                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    })
                    {

                        Scaffold(topBar = {
                            TopAppBar(
                                title = { Text(text = "Menu") },
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    MaterialTheme.colorScheme.surfaceVariant,
                                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        })
                        { values ->
                            val mContext = LocalContext.current
                            Column(
                                modifier = Modifier
                                    .padding(values)
                                    .fillMaxSize()
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.quiz_logo),
                                    contentDescription = null,
                                    modifier = Modifier.height(250.dp)
                                )
                                Spacer(modifier = Modifier.height(80.dp))

                                Button(
                                    onClick = {
                                        mContext.startActivity(
                                            Intent(
                                                mContext,
                                                GameActivity::class.java
                                            )
                                        )
                                    },
                                    modifier = Modifier
                                        .width(300.dp)
                                        .height(80.dp)

                                ) {
                                    Text(text = "Start game", fontSize = 30.sp)
                                }
                                Spacer(modifier = Modifier.height(30.dp))
                                Button(
                                    onClick = {
                                        val value: String = topTen.toString()
                                        println("LALALALALALAL" + value)
                                        val i = Intent(mContext, ScoresActivity::class.java)
                                        i.putExtra("key", value)
                                        mContext.startActivity(i)
                                    },
                                    modifier = Modifier
                                        .width(300.dp)
                                        .height(80.dp)

                                ) {
                                    Text(text = "High Scores", fontSize = 30.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




