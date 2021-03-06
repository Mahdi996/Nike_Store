package com.example.nikestore.data.repo

import com.example.nikestore.data.repo.source.CommentDataSource
import com.example.nikestore.data.Comment
import io.reactivex.Single

class CommentRepositoryImpl(private val commentDataSource: CommentDataSource) : CommentRepository {

    override fun getAll(productId: Int): Single<List<Comment>> = commentDataSource.getAll(productId)

    override fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }
}