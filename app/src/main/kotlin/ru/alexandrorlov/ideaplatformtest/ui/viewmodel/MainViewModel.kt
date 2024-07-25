package ru.alexandrorlov.ideaplatformtest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alexandrorlov.ideaplatformtest.common.models.ScreenState
import ru.alexandrorlov.ideaplatformtest.domain.repository.MainRepository
import ru.alexandrorlov.ideaplatformtest.ui.mapper.toUI
import ru.alexandrorlov.ideaplatformtest.ui.models.ItemUI
import ru.alexandrorlov.ideaplatformtest.ui.models.SearchEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<ScreenState<List<ItemUI>>> = MutableStateFlow(ScreenState.Loading)
    val state: StateFlow<ScreenState<List<ItemUI>>> = _state.asStateFlow()

    private val _querySearch: MutableStateFlow<String> =
        MutableStateFlow((""))
    val querySearch: StateFlow<String> = _querySearch.asStateFlow()

    val querySearchMainScreen: MutableSharedFlow<SearchEvent> = MutableSharedFlow(extraBufferCapacity = 1)

    init {
        loadAllItem()
        listenQuerySearch()
    }

    private fun loadAllItem() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getAll()
                    .map {list ->
                        list.map { it.toUI() }
                    }.collect {
                        _state.emit(ScreenState.Content(it))
                    }
            }.getOrElse {
                _state.emit(ScreenState.Error(it.message ?: "ERROR"))
            }
        }
    }

    private fun loadSearching(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getSearching(query)
                    .map {list ->
                        list.map { it.toUI() }
                    }.collect {
                        _state.emit(ScreenState.Content(it))
                    }
            }.getOrElse {
                _state.emit(ScreenState.Error(it.message ?: "ERROR"))
            }
        }
    }

    private fun listenQuerySearch() {
        querySearchMainScreen
            .onEach {
                when(it) {
                    SearchEvent.EraseSearchQuery -> {
                        _querySearch.update { "" }
                        loadAllItem()
                    }
                    is SearchEvent.ContentSearchQuery -> {
                        if (it.searchQuery.isNotBlank()) {
                            _querySearch.emit(it.searchQuery)

                            loadSearching(it.searchQuery)
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}