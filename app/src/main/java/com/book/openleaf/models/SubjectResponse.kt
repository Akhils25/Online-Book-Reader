package com.book.openleaf.models

data class SubjectResponse(
    val works: List<Work>
) {
    data class Work(
        val title: String,
        val authors: List<Author>,
        val cover_id: Int? = null
    ) {
        data class Author(val name: String)
    }
}
