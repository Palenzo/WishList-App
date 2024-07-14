package blue.pink.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import blue.pink.wishlistapp.data.Wish
import blue.pink.wishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
): ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDesState by mutableStateOf("")

    fun onWishTitleChange(newString: String){
        wishTitleState = newString
    }
    fun onWishDesChange(newString: String){
        wishDesState = newString
    }

    lateinit var getAllWish: Flow<List<Wish>>
    init {
        viewModelScope.launch {
            getAllWish = wishRepository.getAllWishes()
        }
    }
    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish = wish)
        }
    }
    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateAWish(wish = wish)
        }
    }
    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish=wish)
        }
    }
    fun getAWish(id : Long): Flow<Wish>{
        return wishRepository.getWishes(id)
    }

}