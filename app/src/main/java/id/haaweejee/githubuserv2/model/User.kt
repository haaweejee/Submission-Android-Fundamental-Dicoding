package id.haaweejee.githubuserv2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    //TODO: If You want add some attribute or data just add the variabel name
    val username: String,
    val name: String,
    val avatar : Int,
): Parcelable
