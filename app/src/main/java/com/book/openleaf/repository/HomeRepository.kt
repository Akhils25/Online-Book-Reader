package com.book.openleaf.repository

import com.book.openleaf.network.BookApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: BookApiService) {

    suspend fun getBooks(subject: String) = apiService.getSubjectBooks(subject)
}