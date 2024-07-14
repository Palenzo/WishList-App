package blue.pink.wishlistapp

import android.content.Context
import androidx.room.Room
import blue.pink.wishlistapp.data.WishDatabase
import blue.pink.wishlistapp.data.WishRepository

object Graph {
    lateinit var database: WishDatabase


    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,WishDatabase::class.java,"wishlist.db").build()
    }
}