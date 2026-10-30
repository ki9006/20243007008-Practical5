package com.example.practical5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practical5.ui.theme.Practical5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practical5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavApp(modifier: Modifier = Modifier) {
    var isStartPage by remember { mutableStateOf(true) }
    if (isStartPage) {
        StartPage { isStartPage = false }
    } else {
        CitySwitchPage()
    }
}

@Composable
fun StartPage(onClickEnter: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .clickable { onClickEnter() },
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = "logo",
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(R.drawable.chinese_municipalities),
            contentDescription = "map",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Text(text = "Click anywhere to enter the municipality display", modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun CitySwitchPage() {
    var selectedIndex by remember { mutableStateOf(0) }
    val imgList = listOf(
        R.drawable.beijing,
        R.drawable.shanghai,
        R.drawable.chongqing,
        R.drawable.tianjin
    )
    val nameList = listOf("beijing", "shanghai", "chongqing", "tianjin")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(imgList[selectedIndex]),
            contentDescription = nameList[selectedIndex],
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Text(text = "Current city：${nameList[selectedIndex]}")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { selectedIndex = 0 }) { Text("beijing") }
            Button(onClick = { selectedIndex = 1 }) { Text("shanghai") }
            Button(onClick = { selectedIndex = 2 }) { Text("chongqing") }
            Button(onClick = { selectedIndex = 3 }) { Text("tianjin") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    Practical5Theme {
        NavApp()
    }
}