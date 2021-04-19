package com.example.petproject.ui.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.petproject.db.getDatabase
import com.example.petproject.domain.Models
import com.example.petproject.repository.RedditRepository
import kotlinx.coroutines.launch

class OverviewViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RedditRepository(getDatabase(application))

    private val _navigateToSelected = MutableLiveData<Models.Children>()
    val navigateToSelected: LiveData<Models.Children>
        get() = _navigateToSelected

    init {
        viewModelScope.launch {
            repository.refreshChildren()
        }
    }

    val childrenList = repository.childrenList

    fun displayDetail(children: Models.Children){
        _navigateToSelected.value = children
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OverviewViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}