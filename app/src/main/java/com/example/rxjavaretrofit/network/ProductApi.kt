package com.example.rxjavaretrofit.network

import com.example.rxjavaretrofit.model.ProductItem
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    fun getProducts(): Observable<List<ProductItem>>
}