package com.book.openleaf.repository

import android.util.Log
import com.book.openleaf.network.BookApiService
import javax.inject.Inject

class DetailRepository @Inject constructor(private val apiService: BookApiService) {
    suspend fun getChapters(author: String, title: String): List<String> {
        return try {
            fun String.toSlug() = this.lowercase()
                .replace("'", "")
                .replace(Regex("[^a-z0-9\\s]"), " ")
                .trim()
                .replace(Regex("\\s+"), "-")
            val authorSlug = author.toSlug()
            val titleSlug = title.toSlug()
            val url = "https://standardebooks.org/ebooks/$authorSlug/$titleSlug/text/single-page/"
            val html = apiService.getRawHtml(url)
            val rawSections = html.split("<section")
            var sections = rawSections.filter {
                it.contains("epub:type", ignoreCase = true) ||
                        it.contains("class=\"chapter\"", ignoreCase = true) ||
                        it.contains("id=\"chapter", ignoreCase = true)
            }.map { "<section $it" }
            if (sections.isEmpty()) {
                val mainContent = html.substringAfter("<main", "")
                    .substringBefore("</main>", "")

                if (mainContent.isNotEmpty()) {
                    sections = listOf("<main $mainContent </main>")
                }
            }
            if (sections.isEmpty()) throw Exception("Book content empty after parsing")
            sections

        } catch (e: Exception) {
            listOf("<h2>Format Not Supported</h2><p>The text for this specific edition could not be parsed. Please try another book.</p>")
        }
    }
}