package id.haaweejee.githubuserv2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class User(
    val id: Int,
    val login: String,
    val avatar_url : String
)
