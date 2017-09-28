package panucci.julian.apiexample

import io.reactivex.Observable

/**
 * Created by dks0398255 on 9/28/17.
 */

class SearchRepository(val apiService: GithubApiService) {

    fun searchUsers(location: String, language: String): io.reactivex.Observable<Result> {
        return apiService.search(query = "location:$location language:$language")
    }

    fun searchUsers(username: String): Observable<Result> {
        return apiService.search(query = username)
    }
}

object SearchRepositoryProvider {

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(GithubApiService.Factory.create())
    }

}