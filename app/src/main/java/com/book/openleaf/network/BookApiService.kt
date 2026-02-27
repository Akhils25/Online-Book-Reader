package com.book.openleaf.network

import com.book.openleaf.models.SubjectResponse
import retrofit2.http.GET
import retrofit2.http.Header
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
    suspend fun getRawHtml(
        @Url url: String,
        @Header("User-Agent") userAgent: String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
    ): String
}