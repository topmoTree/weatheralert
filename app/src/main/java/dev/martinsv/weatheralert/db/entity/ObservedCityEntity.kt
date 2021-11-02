package dev.martinsv.weatheralert.db.entity

import androidx.room.*

@Entity(
    tableName = "observed_city",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = arrayOf("city_id"),
            childColumns = arrayOf("city_id"),
            onDelete = ForeignKey.NO_ACTION
        )
    ),
    indices = [Index(value = ["city_id"], unique = true)]
)
data class ObservedCityEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "city_id")
    val cityId: Int
)