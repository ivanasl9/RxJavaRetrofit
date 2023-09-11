package com.example.rxjavaretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaretrofit.adapter.ProductAdapter
import com.example.rxjavaretrofit.model.ProductItem
import com.example.rxjavaretrofit.network.RetrofitInstance
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        productAdapter = ProductAdapter(this, ArrayList())
        recyclerView = findViewById(R.id.recViewProducts)
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
            adapter = productAdapter
        }
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(getObservable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> getObserver(response as ArrayList<ProductItem>) },
                { t -> onFailure(t) }
            ))

    }

    private fun getObservable(): Observable<List<ProductItem>> {
        return RetrofitInstance.api.getProducts()
    }

    private fun getObserver(productList: ArrayList<ProductItem>) {
        if (productList.size > 0) {
            productAdapter.setDataProducts(productList)
        }
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(applicationContext, "$t", Toast.LENGTH_SHORT).show()
    }
}


