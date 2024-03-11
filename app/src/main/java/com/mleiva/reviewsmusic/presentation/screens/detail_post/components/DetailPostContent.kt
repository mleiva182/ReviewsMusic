package com.mleiva.reviewsmusic.presentation.screens.detail_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mleiva.reviewsmusic.R
import com.mleiva.reviewsmusic.presentation.screens.detail_post.DetailPostViewModel
import com.mleiva.reviewsmusic.presentation.ui.theme.Brown500
import com.mleiva.reviewsmusic.presentation.ui.theme.ReviewsMusicTheme

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.detail_post.components
 * Creted by: Marcelo Leiva on 11-03-2024 at 10:22
 ***/
@Composable
fun DetailPostContent(paddingValues: PaddingValues, navHostController: NavHostController, viewModel: DetailPostViewModel = hiltViewModel()){

    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        Box() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                model = viewModel.post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    modifier = Modifier.size(35.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 15.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape),
                    model = viewModel.post.user?.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(top = 7.dp, start = 20.dp)
            ) {
                Text(
                    text = viewModel.post.user?.userName ?: "",
                    fontSize = 13.sp
                )
                Text(
                    text = viewModel.post.user?.email ?: "",
                    fontSize = 11.sp
                )
            }
            Text(
                modifier = Modifier.padding(start = 25.dp, bottom = 15.dp),
                text = viewModel.post.name,
                fontSize = 20.sp,
                color = Brown500
            )
            //Spacer(modifier = Modifier.height(15.dp))
            if (!viewModel.post.user?.userName.isNullOrBlank()) {
                Card(
                    modifier = Modifier.padding(start = 13.dp, bottom = 15.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 7.dp, horizontal = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(
                                id = if (viewModel.post.category == "Rock") R.drawable.icon_rock
                                else if (viewModel.post.category == "Metal") R.drawable.icon_metal
                                else if (viewModel.post.category == "Pop") R.drawable.icon_pop
                                else if (viewModel.post.category == "Hip Hop") R.drawable.icon_hip_hop
                                else if (viewModel.post.category == "Urban") R.drawable.icon_urban
                                else if (viewModel.post.category == "Other") R.drawable.icon_other
                                else R.drawable.icon_rock
                            ),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = viewModel.post.category,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                    }
                }
            }else{
                Spacer(modifier = Modifier.height(15.dp))
                Box() {}
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Gray)
                .padding(horizontal = 20.dp, vertical = 10.dp)
            ){
                Divider(
                    modifier = Modifier.padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
                    startIndent = 20.dp,
                    thickness = 2.dp,
                    color = Color.DarkGray
                )
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    text = viewModel.post.description,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                    text = "Loren Ipsum",
                    fontSize = 14.sp
                )
            }

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailPostContentPreview() {
    ReviewsMusicTheme(
        darkTheme = true
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            DetailPostContent(paddingValues = PaddingValues(), rememberNavController())
        }
    }
}