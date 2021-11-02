package dev.martinsv.weatheralert.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "alerts",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = arrayOf("city_id"),
            childColumns = arrayOf("city_id"),
            onDelete = ForeignKey.NO_ACTION
        )
    ),)
data class AlertEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "city_id")
    val cityId: Int,

    @ColumnInfo(name = "regions")
    //todo change later was List<String>
    val regions: String,

    @ColumnInfo(name = "ends_utc")
    val endsUtc: String,

    @ColumnInfo(name = "effective_local")
    val effectiveLocal: String,

    @ColumnInfo(name = "onset_utc")
    val onsetUtc: String,

    @ColumnInfo(name = "expires_local")
    val expiresLocal: String,

    @ColumnInfo(name = "expires_utc")
    val expiresUtc: String,

    @ColumnInfo(name = "ends_local")
    val endsLocal: String,

    @ColumnInfo(name = "uri")
    val uri: String,

    @ColumnInfo(name = "onset_local")
    val onsetLocal: String,

    @ColumnInfo(name = "effective_utc")
    val effectiveUtc: String,

    @ColumnInfo(name = "severity")
    val severity: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String
)