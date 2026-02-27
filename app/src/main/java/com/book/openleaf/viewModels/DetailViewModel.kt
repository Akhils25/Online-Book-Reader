package com.book.openleaf.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.book.openleaf.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    private val _chapters = MutableLiveData<List<String>>()
    val chapters: LiveData<List<String>> = _chapters
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadBookText(author: String, title: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getChapters(author, title)
                _chapters.value = response
            } catch (e: Exception) {
                _chapters.value = listOf("Book not found in native text format.")
            }
            _isLoading.value = false
        }
    }
}