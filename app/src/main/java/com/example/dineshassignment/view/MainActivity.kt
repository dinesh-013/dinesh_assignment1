package com.example.dineshassignment.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.dineshassignment.ui.theme.DineshAssignmentTheme
import com.example.dineshassignment.viewmodel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.retrieveData()
        }



        setContent {
            DineshAssignmentTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
                                    Text(text = "Mandates Details", fontWeight = Bold, fontSize = 15.sp)
                                }

                            },
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(Icons.Filled.KeyboardArrowLeft,"back arrow")
                                }
                            },

                        )
                    }, content = {
                        Box(modifier = Modifier
                            .padding(it)
                            .border(1.dp, Color.LightGray)) {
                            Greeting(viewModel)
                        }

                    })

            }
        }
    }
}

@Composable
fun Greeting(viewModel: MainActivityViewModel, modifier: Modifier = Modifier) {
    val myData by viewModel.myData.observeAsState(null)
    Column(modifier
        .fillMaxSize()

    ) {
        Box(
            modifier
                .padding(20.dp)
                .clip(RoundedCornerShape(5.dp))
                .border(2.dp, Color.LightGray)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)



        )
        {
            Column(modifier.padding(10.dp,0.dp,0.dp,0.dp)) {

                Row(modifier = modifier.padding(0.dp,10.dp,0.dp,0.dp)) {

                    val text1 = buildAnnotatedString {
                        append("Valid till - ")
                        withStyle(style = SpanStyle(fontWeight = Bold)) {
                            myData?.let {
                                append(
                                    it.string1
                                )
                            }
                        }
                    }
                    Text(text = text1, fontSize = 12.sp)

                    Spacer(modifier = modifier.width(30.dp))

                    val text2 = buildAnnotatedString {
                        append("Up to amount - ")
                        withStyle(style = SpanStyle(fontWeight = Bold)) {
                            myData?.let {
                                append(
                                    it.string2
                                )
                            }
                        }
                    }

                    Text(text = text2, fontSize = 12.sp, modifier = modifier.padding(0.dp,0.dp,5.dp,0.dp))


                }

                Spacer(modifier = modifier.height(10.dp))

                Divider(color = Color.LightGray, modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp))

                Spacer(modifier = modifier.height(12.dp))

                val text3 = buildAnnotatedString {
                    append("Frequency -")
                    withStyle(style = SpanStyle(fontWeight = Bold)) {
                        myData?.let {
                            append(
                                it.string3
                            )
                        }
                    }
                }

                Text(text = text3, fontSize = 12.sp)

                Spacer(modifier = modifier.height(10.dp))

                Divider(color = Color.LightGray, modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp))

                Spacer(modifier = modifier.height(10.dp))

                Box(modifier = modifier
                    .padding(0.dp,0.dp,10.dp, 15.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.Yellow)){

                    Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "info Button",
                            modifier = modifier
                                .padding(2.dp, 5.dp, 160.dp, 15.dp)
                                .size(15.dp),
                            tint = Color.Black
                        )

                    val text4 = buildAnnotatedString {

                        myData?.let { append(it.string4) }

                        withStyle(style = SpanStyle(fontWeight = Bold)) {
                           append(" UGX 10,000")
                        }

                    }

                    Text( modifier = modifier
                            .padding(20.dp,5.dp, 10.dp,5.dp),
                            text = text4, fontSize = 10.sp)

                }

            }

        }

        Text(text = "AutoPayment Option", fontSize = 15.sp,
            modifier = modifier.padding(20.dp,0.dp, 0.dp,0.dp), fontWeight = Bold
        )

        Spacer(modifier = modifier.height(10.dp))

        Row(modifier = Modifier.padding(20.dp,0.dp,20.dp, 0.dp)) {
            Image(

                painter = rememberAsyncImagePainter("https://apptestsoko.s3.ap-south-1.amazonaws.com/assets/a.png"),
                contentDescription = "img1",
                modifier = modifier
                    .clip(RoundedCornerShape(5.dp))
                    .border(2.dp, Color.Yellow)
                    .background(Color.Green)
                    .padding(10.dp)
                    .wrapContentWidth()
                    .height(80.dp)

            )
            Spacer(modifier = modifier.width(30.dp))
            Image(

                painter = rememberAsyncImagePainter("https://apptestsoko.s3.ap-south-1.amazonaws.com/assets/m.png"),
                contentDescription = "img2",
                modifier = modifier
                    .clip(RoundedCornerShape(5.dp))
                    .border(2.dp, Color.LightGray)
                    .padding(10.dp)
                    .wrapContentWidth()
                    .height(80.dp)
            )
            Spacer(modifier = modifier.width(30.dp))
            Image(

                painter = rememberAsyncImagePainter("https://apptestsoko.s3.ap-south-1.amazonaws.com/assets/fp.png"),
                contentDescription = "img3",
                modifier = modifier
                    .clip(RoundedCornerShape(5.dp))
                    .border(2.dp, Color.LightGray)
                    .padding(10.dp)
                    .wrapContentWidth()
                    .height(80.dp)

            )
        }
        Spacer(modifier = modifier.height(20.dp))

        Text(text = "Pay Using", fontSize = 15.sp,
            modifier = modifier.padding(20.dp,0.dp, 0.dp,0.dp), fontWeight = Bold
        )

        Spacer(modifier = modifier.height(10.dp))

        Row(modifier = modifier
            .padding(20.dp, 10.dp, 20.dp, 0.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(2.dp, Color.Red)
            .fillMaxWidth()
            .wrapContentHeight()




        ){

            myData?.let {
                Text(text = it.string5, fontSize = 12.sp,
                    modifier = Modifier
                        .padding(10.dp, 10.dp,0.dp,10.dp)
                        .wrapContentWidth()
                        .wrapContentHeight()

                )

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp,10.dp,10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {

                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "info Button",
                    tint = Color.Black
                )

            }

        }


        }


}

