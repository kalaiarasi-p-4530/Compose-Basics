package com.example.compose_webview

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_webview.ui.theme.ComposeWebviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWebviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToolBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Mymail")
                    },
            Modifier.background(colorResource(id = R.color.purple_200)),
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.ArrowBack, contentDescription ="back" )
                }
            }
        )
    }, content = {
        ListWidget()
    })
}

@Composable
fun ListWidget(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription ="",
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .clip(CircleShape)
                .border(
                    width = 3.dp,
                    color = colorResource(id = R.color.black),
                    shape = CircleShape
                )
                 )
        Column(Modifier.fillMaxWidth().padding(start =5.dp)) {
            Row() {
                Text(text = "Sam",
                    modifier = Modifier.weight(1f, true),
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic)
                Spacer(modifier = Modifier.weight(1f, true))
                Row( Modifier.weight(1f, true), horizontalArrangement = Arrangement.End) {
                    Text(text = "10:42 AM")
                }
            }

            Text(text = "Weekend Adventure",
                modifier = Modifier.weight(1f, false).padding(top =5.dp),
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )
            Text(text = "Lets go fishing with john and others. We will enjoy this weekend", modifier = Modifier
                .fillMaxWidth()
                .weight(1f, false)
                .padding(top =5.dp) , maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeWebviewTheme {
        ToolBar()
    }
}