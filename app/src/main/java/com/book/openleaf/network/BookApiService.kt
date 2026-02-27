package com.book.openleaf.network

import com.book.openleaf.models.SubjectResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface BookApiService {

    @GET("subjects/{subject}.json")
    suspend fun getSubjectBooks(
        @Path("subject") subject: String,
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): SubjectResponse

    @GET
    suspend fun getRawHtml(@Url url: String): String
}