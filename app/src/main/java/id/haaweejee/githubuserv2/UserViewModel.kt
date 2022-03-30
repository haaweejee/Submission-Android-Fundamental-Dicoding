package id.haaweejee.githubuserv2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.haaweejee.githubuserv2.api.RetrofitClient
import id.haaweejee.githubuserv2.model.DetailUsers
import id.haaweejee.githubuserv2.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    companion object{
        const val RESPONSE = "response code"
        const val FAIL = "Failure"
    }

    private val _listUsers = MutableLiveData<List<User>>()
    val listUsers : LiveData<List<User>> = _listUsers

    private val _detailUsers = MutableLiveData<DetailUsers>()
    val detailUsers : LiveData<DetailUsers> = _detailUsers

    private val _followingUsers = MutableLiveData<List<User>>()
    val followingUsers : LiveData<List<User>> = _followingUsers

    private val _followersUser = MutableLiveData<List<User>>()
    val followersUser : LiveData<List<User>> = _followersUser


    fun getSearchUser(query: String){
        viewModelScope.launch(Dispatchers.IO){
            val client = RetrofitClient.apiInstance.getSearchUser(query)
            if (client.isSuccessful){
                _listUsers.postValue(client.body()?.items)
            }
        }

    }

    fun getDetailUser(username: String){
        viewModelScope.launch(Dispatchers.IO){
            val client = RetrofitClient.apiInstance.getDetailUser(username)
            if (client.isSuccessful){
                _detailUsers.postValue(client.body())
            }
        }
    }

    fun getDetailFollowing(username: String) {
        viewModelScope.launch(Dispatchers.IO){
            val client = RetrofitClient.apiInstance.getFollowingUser(username)
            if (client.isSuccessful){
                _followingUsers.postValue(client.body())
            }
        }
    }

    fun getDetailFollowers(username: String){
        viewModelScope.launch(Dispatchers.IO){
            val client = RetrofitClient.apiInstance.getFollowersUser(username)
            if (client.isSuccessful){
                _followersUser.postValue(client.body())
            }
        }
    }

}