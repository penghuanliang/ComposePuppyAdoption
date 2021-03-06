package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DemoDataProvider
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.vm.NavViewModel

@Composable
fun HomeScreen(vm: NavViewModel) = VerticalListView(vm)

@Composable
private fun VerticalListView(vm: NavViewModel) {
    val bannerList = remember { DemoDataProvider.banner }
    val puppyList = remember { DemoDataProvider.puppyList }
    val bannerListState = rememberLazyListState()
    val onClickItem: (Puppy) -> Unit = { vm.navigateToDetail(it) }

    LazyColumn {
        item {
            BannerView(listState = bannerListState, list = bannerList, onClickItem)
        }
        items(puppyList.size) { index: Int ->
            val puppy = puppyList[index]
            VerticalListItem(puppy, onClick = onClickItem)
            ListItemDivider()
        }
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )

}
