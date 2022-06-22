package com.example.movieintroduce.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.movieintroduce.R
import com.example.movieintroduce.viewmodel.MovieDetailViewModelFactory
import com.example.movieintroduce.api.ApiService
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.db.MovieDatabase
import com.example.movieintroduce.repository.MovieDBRepository
import com.example.movieintroduce.viewmodel.MovieDetailViewModel
import com.example.movieintroduce.viewmodel.MovieIntroduceViewModel
import kotlinx.coroutines.flow.Flow


class MovieIntroduceActivity : AppCompatActivity() {
    private lateinit var movieIntroduceViewModel: MovieIntroduceViewModel
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = MovieDatabase.getInstance(application).movieDAO
        val repository = MovieDBRepository(dao)
        val factory = MovieDetailViewModelFactory(repository)

        movieIntroduceViewModel = ViewModelProvider(this).get(MovieIntroduceViewModel::class.java)
        movieDetailViewModel =
            ViewModelProvider(this, factory).get(MovieDetailViewModel::class.java)

        getMovieIntroduce()
    }

    private fun getMovieIntroduce() {
        val movies = movieIntroduceViewModel.getMovieIntroduces()

        setContent {
            Conversation(movies = movies)
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Conversation(movies: Flow<PagingData<Movie>>) {
        val intent = Intent(this@MovieIntroduceActivity, MyLikeActivity::class.java)
        val movieItems: LazyPagingItems<Movie> = movies.collectAsLazyPagingItems()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "현재 상영중인 영화",
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(R.drawable.ic_baseline_favorite_24),
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .weight(0.8f)
                        .clickable {
                            startActivity(intent)
                        }
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                    items(movieItems.itemCount) { index ->
                        movieItems[index]?.let {
                            ShowMovieItem(movie = it)
                        }
                    }
                }
            }

        }

    }

    @Composable
    fun ShowMovieItem(movie: Movie) {
        val intent = Intent(this@MovieIntroduceActivity, MovieDetailActivity::class.java)
        intent.putExtra("item", movie)

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    startActivity(intent)
                }

        ) {
            Box(
                modifier = Modifier
                    .height(260.dp)
            ) {
                Column() {
                    Image(
                        painter = rememberAsyncImagePainter(model = "${ApiService.IMAGE_BASE_URL}${movie.movieMainImg}"),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxHeight(0.8f)
                            .fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 8.dp)
                    ) {
                        Text(
                            text = movie.movieTitle,
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = movie.movieGrade.toString(),
                        )
                    }

                }

            }
        }
    }

}
