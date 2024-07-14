@file:Suppress("DEPRECATION")

package blue.pink.wishlistapp

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import blue.pink.wishlistapp.data.Wish

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
){
    val context = LocalContext.current
    Scaffold (

        topBar = {AppBarView(title = "Wishlist"
        ) {
            Toast.makeText(
                context, "Button Clicked", Toast.LENGTH_LONG
            ).show()

        }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.Cyan,
                onClick = {
                          Toast.makeText(context,"Add Button Clicked", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AddScreen.route+"/0L")
                },
                containerColor = Color.Black
                ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add your wishlist!")

            }
        }
    ){
        val wishlist = viewModel.getAllWish.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            items(wishlist.value,   key={wish-> wish.id }){
                wish ->

                val dismissState = androidx.compose.material.rememberDismissState(
                    confirmStateChange = {
                        if (it == androidx.compose.material.DismissValue.DismissedToEnd || it == androidx.compose.material.DismissValue.DismissedToStart){
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )
                androidx.compose.material.SwipeToDismiss(
                    state = dismissState,
                    background = {
                                 val color by animateColorAsState(
                                    if(dismissState.dismissDirection == DismissDirection.StartToEnd) Color.Red else Color.Unspecified,
                                     label = ""
                                 )
                        val alignment = Alignment.CenterEnd
                        Box(
                            Modifier.fillMaxSize().background(color).padding(horizontal = 20.dp, vertical = 2.dp),
                            contentAlignment = alignment
                        ){
                            Icon(Icons.Default.Delete,contentDescription = "Delete Icon", tint = Color.White)
                        }

                    },
                    directions = setOf(androidx.compose.material.DismissDirection.StartToEnd),
                    dismissThresholds = {
                         FractionalThreshold(0.25f)
                    },
                    dismissContent = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route+"/$id")
                        }
                    }

                    )

            }
        }
    }
}
@Composable
fun WishItem(wish: Wish, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = colorResource(id = R.color.app_bar_color)
        )

    ){
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }
    }
}