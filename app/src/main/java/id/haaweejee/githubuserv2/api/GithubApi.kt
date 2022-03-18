package id.haaweejee.githubuserv2.api

import id.haaweejee.githubuserv2.model.DetailUsers
import id.haaweejee.githubuserv2.model.User
import id.haaweejee.githubuserv2.model.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query("q")query: String
    ): Call<UsersResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUsers>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowersUser(
        @Path("username")username: String
    ): Call<List<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUser(
        @Path("username")username: String
    ): Call<List<User>>


}