package ru.alexandrorlov.ideaplatformtest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.alexandrorlov.ideaplatformtest.domain.repository.MainRepository
import ru.alexandrorlov.ideaplatformtest.ui.mapper.toDBO
import ru.alexandrorlov.ideaplatformtest.ui.models.ItemUI
import ru.alexandrorlov.ideaplatformtest.ui.models.ManipulateEvent
import javax.inject.Inject

class ManipulateItemViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    val observeManipulateEvent: MutableSharedFlow<ManipulateEvent> = MutableSharedFlow(extraBufferCapacity = 1)

    init {
        listenManipulateEvent()
    }

    private fun listenManipulateEvent() {
        observeManipulateEvent
            .onEach {
                when(it) {
                    ManipulateEvent.Init -> { }

                    is ManipulateEvent.ChangeAmountItem -> {
                        val oldItemUI: ItemUI = it.item
                        val changeItemUI: ItemUI = oldItemUI.copy(
                            amount = it.changeAmount
                        )
                        repository.insertItem(
                            changeItemUI.toDBO()
                        )
                    }

                    is ManipulateEvent.DeleteItem -> {
                        repository.removeItem(it.id)
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}