package sheridan.bajajku.assignment2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [LocalProduct::class], version = 2, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun itemDao(): ProductDao
}
