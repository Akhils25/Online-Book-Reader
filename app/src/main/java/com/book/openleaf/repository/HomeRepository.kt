package com.book.openleaf.repository

import com.book.openleaf.network.BookApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: BookApiService) {

    suspend fun getBooks(subject: String) = apiService.getSubjectBooks(subject)

    suspend fun getChapters(author: String, title: String): List<String> {
        val a = author.lowercase().replace(" ", "-").replace(Regex("[^a-z-]"), "")
        val t = title.lowercase().replace(" ", "-").replace(Regex("[^a-z-]"), "")

        val url = "https://standardebooks.org/ebooks/$a/$t/text/single-page"
        val html = apiService.getRawHtml(url)

        // Split by section tags to create "Pages"
        return html.split("<section")
            .filter { it.contains("epub:type=\"chapter\"") }
            .map { "<section $it" }
    }
}