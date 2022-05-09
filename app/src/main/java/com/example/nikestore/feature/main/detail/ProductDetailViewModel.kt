package com.example.nikestore.feature.main.detail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.nikestore.common.EXTRA_KEY_DATA
import com.example.nikestore.common.NikeViewModel
import com.example.nikestore.common.asyncNetworkRequest
import com.example.nikestore.data.repo.CartRepository
import com.example.nikestore.data.repo.CommentRepository
import com.example.nikestore.common.NikeSingleObserver
import com.example.nikestore.data.Comment
import com.example.nikestore.data.Product
import io.reactivex.Completable

class ProductDetailViewModel(
    bundle: Bundle,
    commentRepository: CommentRepository,
    private val cartRepository: CartRepository
) : NikeViewModel() {

    val productLiveData = MutableLiveData<Product>()
    val commentLiveData = MutableLiveData<List<Comment>>()

    init {
        progressBarLiveData.value = true
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
        commentRepository.getAll(productLiveData.value!!.id)
            .asyncNetworkRequest()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : NikeSingleObserver<List<Comment>>(compositeDisposable) {
                override fun onSuccess(t: List<Comment>) {
                    commentLiveData.value = t
                }
            })
    }

    fun onAddToCartBtn(): Completable =
        cartRepository.addToCart(productLiveData.value!!.id).ignoreElement()
}