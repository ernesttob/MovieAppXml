package com.example.netfilxcloneapp.presentation.screens.person

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.person.FetchPersonActorsMovie
import com.example.netfilxcloneapp.presentation.screens.home.HomeScreenAction
import com.example.netfilxcloneapp.presentation.screens.home.HomeScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonScreenViewModel @Inject constructor(
    private val popularPersonUseCase: FetchPersonActorsMovie
) : ViewModel() {

    private val _uiAction: MutableSharedFlow<PersonAction> = MutableSharedFlow()
    val uiAction: SharedFlow<PersonAction> = _uiAction.asSharedFlow()

    fun onEvent(event: PersonEvent) {
        when (event) {
            is PersonEvent.OnFetchPopularPerson -> fetchPopularPerson()
        }
    }

    private fun fetchPopularPerson() {
        viewModelScope.launch {
            val popularPersonDeferred = async {
                popularPersonUseCase(page = 1)
            }

            val popularPerson = popularPersonDeferred.await()
            if (popularPerson.isSuccess){
                _uiAction.emit(
                    PersonAction.FetchPopularPerson(
                        popularPersons = popularPerson.getOrNull().orEmpty()
                    )
                )
            }

        }
    }

}