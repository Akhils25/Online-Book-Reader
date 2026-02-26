package com.book.openleaf.Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material3.Text
import coil.compose.AsyncImage
import com.book.openleaf.StylishHeader
import com.book.openleaf.viewModels.HomeViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val loading by viewModel.isLoading.observeAsState(initial = false)
    val bookList by viewModel.bookList.observeAsState(initial = emptyList())
    val chaptersList by viewModel.chapters.observeAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        viewModel.loadSubject("novela_juvenil")
    }
    Scaffold(
        topBar = {
            StylishHeader()
        }
    ) { padding ->
        if (loading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Black)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(color = Color.White)
            ) {
                items(bookList) { book ->
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Row(Modifier.padding(12.dp)) {
                            AsyncImage(
                                model = "https://covers.openlibrary.org/b/id/${book.cover_id}-M.jpg",
                                contentDescription = "",
                                modifier = Modifier
                                    .size(70.dp, 100.dp)
                                    .clip(RoundedCornerShape(4.dp))
                            )
                            Column(Modifier.padding(start = 12.dp)) {
                                Text(
                                    book.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    maxLines = 2,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 10.sp
                                )
                                Text(book.authors.firstOrNull()?.name ?: "", color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
    /*@Composable
    fun ListingScreen(viewModel: BookViewModel, onSelect: (Work) -> Unit) {
        LaunchedEffect(Unit) { viewModel.loadSubject("novela_juvenil") }

        LazyColumn(Modifier.fillMaxSize()) {
            items(viewModel.bookList) { book ->
                Card(
                    Modifier.fillMaxWidth().padding(8.dp).clickable { onSelect(book) },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(Modifier.padding(12.dp)) {
                        AsyncImage(
                            model = "https://covers.openlibrary.org/b/id/${book.cover_id}-M.jpg",
                            contentDescription = null,
                            modifier = Modifier.size(70.dp, 100.dp).clip(RoundedCornerShape(4.dp))
                        )
                        Column(Modifier.padding(start = 12.dp)) {
                            Text(book.title, style = MaterialTheme.typography.titleMedium, maxLines = 2)
                            Text(book.authors.firstOrNull()?.name ?: "", color = Color.Gray)
                        }
                    }
                }
            }
        }
    }*/


    /* @OptIn(ExperimentalFoundationApi::class)
     @Composable
     fun ReadingScreen(work: Work, viewModel: BookViewModel) {
         LaunchedEffect(work) { viewModel.loadBookText(work.authors[0].name, work.title) }

         if (viewModel.loadingByPage) {
             Box(Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }
         } else {
             val pagerState = rememberPagerState(pageCount = { viewModel.chapters.size })

             HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { index ->
                 Column(Modifier.fillMaxSize().padding(20.dp).verticalScroll(rememberScrollState())) {
                     AndroidView(
                         factory = { context ->
                             TextView(context).apply {
                                 textSize = 19f
                                 setLineSpacing(0f, 1.2f)
                                 setTextColor(android.graphics.Color.BLACK)
                             }
                         },
                         update = { it.text = HtmlCompat.fromHtml(viewModel.chapters[index], 0) }
                     )
                 }
             }
         }
     }*/
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun ShowHomePreview() {
    HomeScreen(rememberNavController())
}