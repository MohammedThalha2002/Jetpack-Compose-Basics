package com.example.jetpackcomposebasics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme
import androidx.navigation.NavHostController


@Composable
fun BasicsScreen(navController: NavHostController){
    Column {
        CombinationROWandCOLUMN()
        ListFun()
        Spacer(modifier = Modifier.height(20.dp))
    }
}

// BASIC PREVIEW
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeBasicsTheme {
        Surface {
            val mod = Modifier.background(color = Color.Red)
            Column{
                Greeting("Android", mod)
                ColumnFun()
                RowFun()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

// LAYOUTS

// 1) COLUMN
@Composable
fun ColumnFun(){
    Column {
        Text(text = "Hello guys")
        Text(text = "Nice to meet you")
    }
}

// 2) ROW

@Composable
fun RowFun(){
    Row {
        Text(text = "Hello guys || ")
        Text(text = "Nice to meet you")
    }
}

// Combination with image
@Composable
fun CombinationROWandCOLUMN(){
    Row(
        modifier = Modifier.padding(all = 10.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.profile), contentDescription = "Profile",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        //
        //  space
        Spacer(modifier = Modifier.width(10.dp))
        //
        Column {
            Text(text = "Thalha")
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Project Trainee at ZOHO")
        }
    }
}

// 3) Creating a List

class Training(_day : String, _task : String){
    val day : String = _day
    val task : String = _task
}

@Composable
fun ListFun(){
    val trainingList : List<Training> = listOf<Training>(
        Training("DAY 1", "Learn Kotlin"),
        Training("DAY 2", "Learn Coroutines"),
        Training("DAY 3", "Learn Delegate Functions")
    )

    // 1 way
    LazyColumn{
        items(trainingList) { training ->
            TaskCard(training = training)
        }
    }


}

// 4) Alignment - top, center, bottom,.. (totally 9)
// & Arrangement - SpaceEvenly, SpaceBetween, SpaceAround
@Composable
fun TaskCard(training : Training){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement  =  Arrangement.SpaceBetween
    ) {
        Text(text = training.day, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = training.task)
    }
}

