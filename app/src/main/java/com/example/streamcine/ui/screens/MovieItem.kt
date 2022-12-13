package com.example.streamcine.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.streamcine.data.models.MovieModel
import androidx.compose.ui.Modifier
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation


@Composable
fun MovieItem(movie: MovieModel) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface() {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = "",

                        builder = {
                            scale(Scale.FILL)
                            placeholder(coil.compose.base.R.drawable.notification_action_background)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = movie.overview,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie.original_title,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Popularity : " + movie.popularity,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = "Note : " + movie.vote_average + "/10",
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Vote : " + movie.vote_count,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }

}