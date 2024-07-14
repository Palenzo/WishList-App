package blue.pink.wishlistapp.data

import java.util.concurrent.Flow

class WishRepository (private val wishDao: WishDao){
    suspend fun addWish(wish: Wish){
        wishDao.addWish(wish)
    }
    fun getAllWishes(): kotlinx.coroutines.flow.Flow<List<Wish>> = wishDao.getAllWishes()
    fun getWishes(id: Long): kotlinx.coroutines.flow.Flow<Wish>{
        return wishDao.getWish(id)
    }
    suspend fun updateAWish(wish: Wish){
        wishDao.updateWish(wish)
    }
    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }

}