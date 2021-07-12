package com.sauravrp.takehome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sauravrp.takehome.db.WorkDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WorkFlowViewModel : ViewModel() {

    sealed class ViewState {
        class Success(val data: List<Work>): ViewState()
    }

    private val mutableViewState = MutableLiveData<ViewState>()

    val viewState: LiveData<ViewState> by lazy {
        mutableViewState
    }

    init {
        viewModelScope.launch {
            WorkDb.workDatbase.workDao().getAllWork().collect {
                mutableViewState.value = ViewState.Success(it)
            }
        }
    }

}

@Entity
data class Work(
    @PrimaryKey val data: String)