package com.example.compose_webview

import android.icu.text.CaseMap.Title
import android.webkit.WebView
import androidx.annotation.ColorRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewComposable(title : String, url : String, onBackPressed: () -> Unit) {
    var webView by remember {
        mutableStateOf<WebView?>(null)
    }



    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (webView != null && webView!!.canGoBack()) {
                            webView!!.goBack()
                        } else {
                            onBackPressed.invoke()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, "", tint = colorResource(
                                id = R.color.white
                            )
                        )

                    }
                })
        }, content = {

        })



}





