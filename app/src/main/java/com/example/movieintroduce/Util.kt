package com.example.movieintroduce

fun getGenre(genreId : Int) : String {
    var genre : String

    when(genreId) {
        12 -> {
            genre = "어드벤쳐"
        }
        14 -> {
            genre = "판타지"
        }
        16 -> {
            genre = "애니"
        }
        18 -> {
            genre = "드라마"
        }
        27 -> {
            genre = "호러"
        }
        28 -> {
            genre = "액션"
        }
        35 -> {
            genre = "코미디"
        }


        80 -> {
            genre ="범죄"
        }
        99 -> {
            genre = "다큐멘터리"
        }
        10751 -> {
            genre = "가족"
        }
        36 -> {
            genre = "역사"
        }
        10402 -> {
            genre = "음악"
        }
        9648 -> {
            genre = "미스테리"
        }
        10749 -> {
            genre = "로맨스"
        }
        878 -> {
            genre = "공상과학"
        }
        10770 -> {
            genre = "tv영화"
        }
        53 -> {
            genre = "스릴러"
        }
        10752 -> {
            genre = "전쟁"
        }
        37 -> {
            genre = "서부극"
        }
        else -> {
            genre = ""
        }
    }
    return genre
}
