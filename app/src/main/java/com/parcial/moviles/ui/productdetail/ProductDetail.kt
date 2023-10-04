package com.parcial.moviles.ui.productdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.parcial.moviles.data.local.AppDatabase
import com.parcial.moviles.data.model.Product
import com.parcial.moviles.data.model.ProductImage
import com.parcial.moviles.data.model.ProductTitle
import com.parcial.moviles.repository.ProductRepository
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductDetail(id: Int) {
    val context = LocalContext.current
    val heroDao = AppDatabase.getInstance(context).productDao()
    val repository = ProductRepository(heroDao)
    val product = remember{
        mutableStateOf<Product?>(null)
    }
    repository.searchById(id = id){ result ->
        product.value = result.data!!
    }


    if (product.value != null){
        Column{
            Spacer(modifier = Modifier.height(64.dp))
            ProductImage(product.value!!.image)
            Spacer(modifier = Modifier.height(8.dp))
            ProductTitle(product.value!!.name)
        }
    }
}

@Composable
fun ProductImage(url: String){
    GlideImage(
        imageModel = {url},
        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
        modifier = Modifier
            .size(256.dp)
            .clip(shape = RoundedCornerShape(4.dp))
    )
}