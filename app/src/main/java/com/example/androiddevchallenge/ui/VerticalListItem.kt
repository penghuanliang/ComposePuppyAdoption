package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DemoDataProvider
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.typography


@Composable
fun VerticalListItem(
    puppy: Puppy,
    modifier: Modifier = Modifier,
    onClick: (puppy: Puppy) -> Unit
) {
    val typography = MaterialTheme.typography
    Row(
        modifier = modifier
            .padding(16.dp)
            .clickable { onClick(puppy) }
    ) {
        ItemImage(
            puppy,
            Modifier.padding(end = 18.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(puppy.name, style = typography.subtitle1)
            ItemInfo("${puppy.sex.str}, ${puppy.age}")
            ItemInfo(puppy.breed)
            ItemInfo(puppy.location)
        }
    }

}

@Composable
fun ItemImage(puppy: Puppy, modifier: Modifier) {
    val image = ImageBitmap.imageResource(puppy.images.first())
    Image(
        bitmap = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(120.dp, 80.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
fun ItemInfo(str: String) {
    Text(str, style = typography.body2, maxLines = 1, overflow = TextOverflow.Ellipsis)
}

@Preview
@Composable
fun PreviewVerticalItem() {
    val puppy = DemoDataProvider.Edison
    VerticalListItem(puppy) {

    }
}