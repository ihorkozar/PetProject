package com.example.petproject.ui.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.petproject.domain.Models
import com.example.petproject.repository.RedditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    application: Application,
    private val repository: RedditRepository
) : AndroidViewModel(application) {
    //private val repository = RedditRepository(getDatabase(application))

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

    fun displayDetailComplete(){
        _navigateToSelected.value = null
    }
}