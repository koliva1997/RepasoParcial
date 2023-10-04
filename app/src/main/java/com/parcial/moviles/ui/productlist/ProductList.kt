package com.parcial.moviles.ui.productlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.parcial.moviles.data.local.AppDatabase
import com.parcial.moviles.data.model.Product
import com.parcial.moviles.repository.ProductRepository


@Composable
fun ProductList(
    products: MutableState<List<Product>>,
    selectProduct:(Int)->Unit
) {
    val context = LocalContext.current
    val productDao = AppDatabase.getInstance(context).productDao()
    val productRepository = ProductRepository(productDao)

    LazyColumn{
        items(products.value){product->
            ProductRow(product, selectProduct,
                deleteProduct = {
                    productRepository.delete(product)
                    product.isFavorite = false
                },
                insertProduct = {
                    productRepository.save(product)
                    product.isFavorite = true
                }
            )
        }
    }
}
@Composable
fun ProductRow(
    product: Product,
    selectProduct: (Int) -> Unit,
    deleteProduct: () -> Unit,
    insertProduct:() -> Unit
){
    val isFavorite = remember {
        mutableStateOf(false)
    }
    isFavorite.value = product.isFavorite
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                selectProduct(product.id)
            }
    ){
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProductImage(product.image.url)
            Column(modifier = Modifier.weight(5f)) {
                Text(text = product.name.title, fontWeight = FontWeight.Bold)
            }
            IconButton(modifier = Modifier.weight(1f), onClick = {
                if (isFavorite.value) {
                    deleteProduct()
                } else {
                    insertProduct()
                }
                isFavorite.value = !isFavorite.value
            }) {
                Icon(
                    Icons.Default.Favorite,
                    null,
                    tint = if (isFavorite.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun ProductImage(url: String){
    GlideImage(
        imageModel = {url},
        imageOptions = ImageOptions(contentScale = ContentScale.Fit),
        modifier = Modifier.size(92.dp)
    )
}