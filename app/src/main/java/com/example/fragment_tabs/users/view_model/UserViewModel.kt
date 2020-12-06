package com.example.fragment_tabs.users.view_model

import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragment_tabs.data.ApiServiceFactory
import com.example.fragment_tabs.users.model.User
import kotlinx.coroutines.launch

//LOADING STATUS ENUM
enum class UserApiStatus { LOADING, ERROR, DONE }

class UserViewModel : ViewModel() {
    //stores the status of the response
    private val _status = MutableLiveData<UserApiStatus>()
    val status: LiveData<UserApiStatus>
        get() = _status

    //stores list of USER data
    private val _usersData = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _usersData

    private var _navigateToUserDetail = MutableLiveData<User>()
    val navigateToUserDetail: LiveData<User>
        get() = _navigateToUserDetail

    //For storing copy of original user data
    private val _filteredUsers = MutableLiveData<List<User>>()

    /**
     * Call getUsers() on init so we can display status immediately.
     */
    init {
        getUsers()
    }

    //Storing value for list item click
    fun onUserListItemClicked(user: User){
        _navigateToUserDetail.value = user
    }

    //Clearing value once list item click finished for avoiding duplicates on screen orientation
    fun onUserDetailNavigated(){
        _navigateToUserDetail.value = null
    }

    //Binder for SearchView it will be called when user enter text in search view
    fun getQueryTextListener(): SearchView.OnQueryTextListener? {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                //Filters data based on the entered text
                filterData(query)
                return true
            }
        }
    }

    /*
    Filters data and updates '_users' LiveData object
    Business logic for Search functionality
    */
    private fun filterData(searchText: String) {
        if (searchText.isEmpty()) {
            _usersData.value = _filteredUsers.value
            return
        }
        _usersData.value = _filteredUsers.value?.filter { it.login.contains(searchText) }
    }

    /**
     * Sets the value of the status LiveData to the User API status.
     */
    private fun getUsers() {
        //Coroutine with ViewModelScope
        viewModelScope.launch {
            //Shows loader before loading data from REST API
            _status.value = UserApiStatus.LOADING
            try{
                //On Success REST API call
                _usersData.value = ApiServiceFactory.retrofitService.getUsers()
                _filteredUsers.value = _usersData.value
                _status.value = UserApiStatus.DONE
            }catch (e: Exception){
                //On Failure REST API Call
                _status.value = UserApiStatus.ERROR
                _usersData.value = ArrayList()
            }
        }
    }
}