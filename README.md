# Kotlin-Retrofit
Small example project demonstrating how to use Retrofit for Android in Kotlin

### Example of Service being used
```kotlin
val service = GithubApiService.Factory.create()

button.setOnClickListener {
    service.search("julp04")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({ result ->
                Log.d("Result", "There are ${result.items.size} Java developers in Lagos")
            }, {
                error -> error.printStackTrace()
            })
}
```

### Github Service
```kotlin

interface GithubApiService {

    @GET("search/users")
    fun search(@Query("q") query: String,
               @Query("page") page: Int = 1,
               @Query("per_page") perPage: Int = 20): Observable<Result>

    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): GithubApiService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .build()

            return retrofit.create(GithubApiService::class.java);
        }
    }
}
```

### Search Respository Class
Makes the specific calls to the API
```kotlin
class SearchRepository(val apiService: GithubApiService) {

    fun searchUsers(location: String, language: String): io.reactivex.Observable<Result> {
        return apiService.search(query = "location:$location language:$language")
    }

    fun searchUsers(username: String): Observable<Result> {
        return apiService.search(query = username)
    }
}
```

### Search Repository Provider
Singleton class in Kotlin. Initializes the Github Service
```kotlin
object SearchRepositoryProvider {

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(GithubApiService.Factory.create())
    }
}
```
## Data Classes
### User on GitHub
```kotlin

data class User(
        val login: String,
        val id: Long,
        val url: String,
        val html_url: String,
        val followers_url: String,
        val following_url: String,
        val starred_url: String,
        val gists_url: String,
        val type: String,
        val score: Double
)
```
### The Result that comes back from API in JSON
```kotlin
/**
 * These represent the parameters of the json that comes back
 * Ex: {
 *      total_count: 1
 *      incomplete_results: false
 *      items: [
 *      - {
 *              login: "Julp04",
                id: 8071655,
                avatar_url: "https://avatars0.githubusercontent.com/u/8071655?v=4",
                gravatar_id: "",
                url: "https://api.github.com/users/Julp04",
                html_url: "https://github.com/Julp04",
                gists_url: "https://api.github.com/users/Julp04/gists{/gist_id}",
                starred_url: "https://api.github.com/users/Julp04/starred{/owner}{/repo}",
                score: 40.436512
 *         }
 *             ]
 *      }
 *  }
 */
data class Result (val total_count: Int, val incomplete_results: Boolean, val items: List<User>)
```
