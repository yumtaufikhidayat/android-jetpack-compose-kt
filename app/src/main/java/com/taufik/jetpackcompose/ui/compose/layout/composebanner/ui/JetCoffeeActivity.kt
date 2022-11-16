package com.taufik.jetpackcompose.ui.compose.layout.composebanner.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taufik.jetpackcompose.R
import com.taufik.jetpackcompose.ui.compose.layout.composebanner.model.Menu
import com.taufik.jetpackcompose.ui.compose.layout.composebanner.model.dummyBestSellerMenu
import com.taufik.jetpackcompose.ui.compose.layout.composebanner.model.dummyCategory
import com.taufik.jetpackcompose.ui.compose.layout.composebanner.model.dummyMenu
import com.taufik.jetpackcompose.ui.compose.layout.composebanner.ui.components.CategoryItem
import com.taufik.jetpackcompose.ui.compose.layout.composebanner.ui.components.MenuItem
import com.taufik.jetpackcompose.ui.compose.layout.composebanner.ui.components.SearchBar
import com.taufik.jetpackcompose.ui.compose.layout.composebanner.ui.components.SectionText
import com.taufik.jetpackcompose.ui.theme.JetpackComposeTheme

class JetCoffeeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category)
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title }) { menu ->
            MenuItem(menu)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryRowPreview() {
    JetpackComposeTheme {
        CategoryRow()
    }
}

@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Banner()
        SectionText(title = stringResource(R.string.section_category))
        CategoryRow()
        SectionText(title = stringResource(R.string.section_favorite_menu))
        MenuRow(listMenu = dummyMenu)
        SectionText(title = stringResource(R.string.section_best_seller_menu))
        MenuRow(listMenu = dummyBestSellerMenu)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
    JetpackComposeTheme {
        JetCoffeeApp()
    }
}