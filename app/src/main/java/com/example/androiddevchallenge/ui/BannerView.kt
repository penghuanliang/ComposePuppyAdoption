package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DemoDataProvider
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun BannerView(
    listState: LazyListState,
    list: List<Puppy>,
    onClickItem: ((puppy: Puppy) -> Unit)? = null
) {
    Column {
        Text(
            text = "Featured Puppies",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.subtitle1
        )

        LazyRow(state = listState) {
            items(
                count = list.size,
            ) {
                BannerItem(
                    list[it],
                    Modifier.padding(start = 16.dp, top = 8.dp, bottom = 16.dp)
                ) {
                    if (null != onClickItem) {
                        onClickItem(list[it])
                    }
                }
            }
        }
    }
}


@Composable
fun BannerItem(
    puppy: Puppy,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .size(280.dp, 200.dp)
            .shadow(20.dp)
    ) {
        Box(modifier = Modifier.clickable { onClick() }) {
            val image = ImageBitmap.imageResource(puppy.images.first())
            Image(
                bitmap = image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.9f))
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
            ) {
                Row {
                    Text(
                        text = puppy.name,
                        style = MaterialTheme.typography.h6,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.padding(1.dp))

                    Image(
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.CenterVertically),
                        colorFilter = ColorFilter.tint(puppy.sex.color),
                        imageVector = puppy.sex.label,
                        contentDescription = null
                    )
                }

                Text(
                    text = puppy.breed,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )

                Text(
                    text = puppy.location,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHorizontalListItem() {
    val puppy = DemoDataProvider.Edison
    MyTheme {
        BannerItem(puppy = puppy) {

        }
    }
}

@Preview
@Composable
fun PreviewBannerView() {
    val bannerState = rememberLazyListState()
    val list = remember {
        DemoDataProvider.puppyList
    }
    MyTheme {
        BannerView(listState = bannerState, list = list)
    }
}