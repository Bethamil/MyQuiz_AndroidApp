package com.emiel.myquiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emiel.myquiz.ui.theme.MyQuizTheme

class ScoresActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val extras = intent.extras
            var topTenScores: String
            if (extras != null) {
                topTenScores = extras.getString("key").toString()
                MyQuizTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold(topBar = {
                            TopAppBar(
                                title = { Text(text = "High scores") },
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    MaterialTheme.colorScheme.surfaceVariant,
                                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        })
                        { values ->
                            Column(
                                modifier = Modifier.padding(values).fillMaxSize().padding(10.dp),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {

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
                                        text = topTenScores,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontSize = 30.sp,
                                        lineHeight = 40.sp,
                                        color = MaterialTheme.colorScheme.background
                                    )
                                }

                            }
                            Column(
                                modifier = Modifier
                                    .padding(values)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                val mContext = LocalContext.current

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
                                ) {
                                    Text(text = "Menu")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

