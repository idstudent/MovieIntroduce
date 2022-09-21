package com.example.movieintroduce.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieintroduce.api.ApiManager
import com.example.movieintroduce.model.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: 1

            val response = ApiManager.getInstance().getNowViewMovies("api_key", "ko", position)

            val body = response.body()?.movieInfoList ?: ArrayList()

            LoadResult.Page(
                data = body,
                prevKey = if(position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }
}