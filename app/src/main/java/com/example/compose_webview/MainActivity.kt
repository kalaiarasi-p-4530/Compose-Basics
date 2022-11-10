package com.example.compose_webview


import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_webview.model.MailItemDataModel
import com.example.compose_webview.ui.theme.ComposeWebviewTheme
import kotlinx.coroutines.launch

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
        SmallTopAppBar(
            title = {
                Text(text = "Mymail")
            },
            Modifier.background(colorResource(id = R.color.purple_200)),
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                }
            }
        )
    }, content = {
        //UIWidget()
        ListWidget()
    }, floatingActionButton = {
        Fab()
    })
}

@Composable
fun Fab(showButton: Boolean = false, listState: LazyListState ?= null){
    val ctx = LocalContext.current
    val density = LocalDensity.current
    val composableScope = rememberCoroutineScope()


    AnimatedVisibility(visible = showButton,enter = slideInVertically {
        with(density) { 40.dp.roundToPx() }
    } + fadeIn(),
        exit = fadeOut(
            animationSpec = keyframes {
                this.durationMillis = 120
            }
        )
    ) {

        // show fab
        Column(
            Modifier
                .fillMaxSize()
                .fillMaxWidth(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
            FloatingActionButton(
                onClick = {
                    composableScope.launch { listState?.animateScrollToItem(0)  }
                    },
                shape = CircleShape,
                containerColor = Color.Magenta,
            ) {
                Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "Move up")

            }
        }
    }

}
@Composable
fun ListWidget() {
   // UIWidget()
   columnList()
   // rowList()
   // GridList()

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun columnList(){
    var webView by remember {
        mutableStateOf<WebView?>(null)
    }

    Box {

        val listState = rememberLazyListState()
        val list = loadData()
        val grouped = list.groupBy { it.section }
        LazyColumn(state = listState) {

            grouped.forEach { (section, name) ->
                stickyHeader {
                    Text(
                        text = "$section",
                        color = androidx.compose.ui.graphics.Color.White,
                        modifier = Modifier
                            .background(color = androidx.compose.ui.graphics.Color.Black)
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
                items(items = name, key = { listItem ->
                    // Return a stable + unique key for the item
                    listItem.id
                }
                ) { listItem ->

                    UIWidget(listItem)
                }
            }


        }
        val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
        }
        Fab(showButton, listState)


    }

}

@Composable
fun rowList(){
    val list = loadData()
    LazyRow(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),) {
        items(list) { item ->
            UIWidget(item)
            // Text(text = item.name)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridList(){
    val list = loadData()
   /* LazyVerticalGrid(cells = GridCells.Fixed(3)) {
        items(list) { item ->
            UIWidget(item)
        }
    }*/
    LazyVerticalGrid(cells = GridCells.Adaptive(128.dp)) {
       items(list) { item ->
            UIWidget(item)
        }


    }
}
fun loadData(): List<MailItemDataModel> {
    val list = arrayListOf<MailItemDataModel>()
    var section = 0
    for (i in 0..200) {

        if(i % 10 == 0){
            section++
        }
        list.add(
            MailItemDataModel(i,
                "Kalai $i",
                "Subject $i",
                "Hi can you send more info about the issue. Hi can you send more info about the issue.",
                "$i:45 AM", section
            )
        )
    }
    return list
}


/*fun loadData(): List<MailItemDataModel> {
    val list = arrayListOf<MailItemDataModel>()
    var i =1
        list.add(
            MailItemDataModel(1,
                "AAAA i",
                "Subject $i",
                "Hi can you send more info about the issue. Hi can you send more info about the issue.",
                "$i:45 AM"
            )
        )
    list.add(
        MailItemDataModel(2,
            "ABBB i",
            "Subject $i",
            "Hi can you send more info about the issue. Hi can you send more info about the issue.",
            "$i:45 AM"
        )
    )
    list.add(
        MailItemDataModel(3,
            "ACCC i",
            "Subject $i",
            "Hi can you send more info about the issue. Hi can you send more info about the issue.",
            "$i:45 AM"
        )
    )
    list.add(
        MailItemDataModel(4,
            "ADDDD i",
            "Subject $i",
            "Hi can you send more info about the issue. Hi can you send more info about the issue.",
            "$i:45 AM"
        )
    )
    list.add(
        MailItemDataModel(5,
            "AEEEE i",
            "Subject $i",
            "Hi can you send more info about the issue. Hi can you send more info about the issue.",
            "$i:45 AM"
        )
    )
    list.add(
        MailItemDataModel(6,
            "BAAA i",
            "Subject $i",
            "Hi can you send more info about the issue. Hi can you send more info about the issue.",
            "$i:45 AM"
        )
    )
    list.add(
        MailItemDataModel(7,
            "BNNA i",
            "Subject $i",
            "Hi can you send more info about the issue. Hi can you send more info about the issue.",
            "$i:45 AM"
        )
    )
    list.add(
            MailItemDataModel(8,
                "BJJS i",
                "Subject $i",
                "Hi can you send more info about the issue. Hi can you send more info about the issue.",
                "$i:45 AM"
            )
            )
    list.add(
            MailItemDataModel(9,
                "BKNKNK i",
                "Subject $i",
                "Hi can you send more info about the issue. Hi can you send more info about the issue.",
                "$i:45 AM"
            )
            )
    list.add(
            MailItemDataModel(10,
                "CKHK i",
                "Subject $i",
                "Hi can you send more info about the issue. Hi can you send more info about the issue.",
                "$i:45 AM"
            )
            )
    list.add(
            MailItemDataModel(11,
                "CNKNK i",
                "Subject $i",
                "Hi can you send more info about the issue. Hi can you send more info about the issue.",
                "$i:45 AM"
            )
            )
    list.add(
            MailItemDataModel(12,
                "CNKN i",
                "Subject $i",
                "Hi can you send more info about the issue. Hi can you send more info about the issue.",
                "$i:45 AM"
            )
            )
    list.add(
            MailItemDataModel(13,
                "CNKN i",
                "Subject $i",
                "Hi can you send more info about the issue. Hi can you send more info about the issue.",
                "$i:45 AM"
            )
            )
    list.add(
            MailItemDataModel(14,
                "DNKNK i",
                "Subject $i",
                "Hi can you send more info about the issue. Hi can you send more info about the issue.",
                "$i:45 AM"
            )
            )
    list.add(
            MailItemDataModel(15,
                "DKNK i",
                "Subject $i",
                "Hi can you send more info about the issue. Hi can you send more info about the issue.",
                "$i:45 AM"
            )
            )
    list.add(
        MailItemDataModel(16,
            "DNKK i",
            "Subject $i",
            "Hi can you send more info about the issue. Hi can you send more info about the issue.",
            "$i:45 AM"
        )
    )
    list.add(
        MailItemDataModel(17,
            "DNKNK i",
            "Subject $i",
            "Hi can you send more info about the issue. Hi can you send more info about the issue.",
            "$i:45 AM"
        )
    )

    return list
}*/


@Composable
fun UIWidget(item : MailItemDataModel) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
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
            Text(
                text = item.name,
                modifier = Modifier.weight(1f, true),
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

        }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeWebviewTheme {
        // ToolBar()
       // ListWidget()
    }
}