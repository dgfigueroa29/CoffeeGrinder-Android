package com.interview.coffeegrinder.ui.main

import androidx.lifecycle.*
import com.interview.coffeegrinder.data.Repository
import com.interview.coffeegrinder.model.Coffee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(), LifecycleObserver {
    private val _coffees = MutableLiveData<List<Coffee>>(listOf())
    var coffees: LiveData<List<Coffee>> = _coffees
    private val _coffeeDetail = MutableLiveData<Coffee?>(null)
    var coffeeDetail: LiveData<Coffee?> = _coffeeDetail

    fun fetchCoffees() = viewModelScope.launch {
        _coffees.postValue(repository.getCoffees())
    }

    fun fetchCoffee(id: String) = viewModelScope.launch {
        _coffeeDetail.postValue(repository.getCoffee(id))
    }
}