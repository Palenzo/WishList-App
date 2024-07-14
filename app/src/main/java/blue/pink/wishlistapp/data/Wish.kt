package blue.pink.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String ="",
    @ColumnInfo(name = "wish-des")
    val description:String=""
)
object DummyWish{
    val wishList = listOf(
        Wish(
            title = "Job",
            description = "I need it a lot!"
        ),
        Wish(
            title = "Sex",
            description = "I need it a lot!"
        ),
        Wish(
            title = "BlowJob",
            description = "I need it a lot"
        ),
        Wish(
            title = "Ass",
            description = "I need it a lot"
        )
    )

}