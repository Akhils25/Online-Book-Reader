package com.book.openleaf.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material3.Text
import coil.compose.AsyncImage
import com.book.openleaf.R
import com.book.openleaf.StylishHeader
import com.book.openleaf.models.SubjectResponse
import com.book.openleaf.viewModels.HomeViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val loading by viewModel.isLoading.observeAsState(initial = false)
    val bookList by viewModel.bookList.observeAsState(initial = emptyList())
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
                item {
                    Spacer(
                        Modifier
                            .height(4.dp)
                            .fillMaxWidth()
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .height(150.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_banner),
                            contentDescription = "Promotional Banner",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Must Read",
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Text(
                                text = "See what’s popular right now",
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }
                        Text(
                            text = "",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color.Red
                        )
                    }
                    Spacer(
                        Modifier
                            .height(4.dp)
                            .fillMaxWidth()
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 0.dp, top = 0.dp, bottom = 0.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(bookList) { book ->
                            ListingCard(navHostController, book)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListingCard(navHostController: NavHostController, book: SubjectResponse.Work) {
    val author = book.authors.firstOrNull()?.name ?: "Unknown"
    val encodedTitle = URLEncoder.encode(book.title, StandardCharsets.UTF_8.toString())
    val encodedAuthor = URLEncoder.encode(author, StandardCharsets.UTF_8.toString())
    Card(
        onClick = {
            navHostController.navigate("DetailScreen/$encodedAuthor/$encodedTitle") {
                popUpTo("HomeScreen") { inclusive = false }
            }
        },
        Modifier
            .width(148.dp)
            .height(217.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column {
            AsyncImage(
                model = "https://covers.openlibrary.org/b/id/${book.cover_id}-M.jpg",
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Black
                )
                Text(
                    text = book.authors.firstOrNull()?.name ?: "Unknown Author",
                    color = Color.Gray,
                    fontSize = 10.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun ShowHomePreview() {
    HomeScreen(rememberNavController())
}