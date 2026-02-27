package com.book.openleaf

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.book.openleaf.ui.theme.PurpleLight

@Composable
fun StylishHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                textAlign = TextAlign.Center,
                text = "Home",
                color = PurpleLight,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                textAlign = TextAlign.Center,
                text = "Start Reading with Daily Goals",
                color = Color.Black,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 5.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
            )
        }

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
fun ShowHeaderPreview() {
    StylishHeader()
}