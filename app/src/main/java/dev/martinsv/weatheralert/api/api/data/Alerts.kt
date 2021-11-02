package dev.martinsv.weatheralert.api.api.data

import com.google.gson.annotations.SerializedName

data class Alerts (

	@SerializedName("regions") val regions : List<String>,
	@SerializedName("ends_utc") val ends_utc : String,
	@SerializedName("effective_local") val effective_local : String,
	@SerializedName("onset_utc") val onset_utc : String,
	@SerializedName("expires_local") val expires_local : String,
	@SerializedName("expires_utc") val expires_utc : String,
	@SerializedName("ends_local") val ends_local : String,
	@SerializedName("uri") val uri : String,
	@SerializedName("onset_local") val onset_local : String,
	@SerializedName("effective_utc") val effective_utc : String,
	@SerializedName("severity") val severity : String,
	@SerializedName("title") val title : String,
	@SerializedName("description") val description : String
)