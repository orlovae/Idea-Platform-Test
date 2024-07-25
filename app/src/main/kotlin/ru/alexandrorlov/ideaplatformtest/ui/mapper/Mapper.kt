package ru.alexandrorlov.ideaplatformtest.ui.mapper

import ru.alexandrorlov.ideaplatformtest.database.models.ItemDBO
import ru.alexandrorlov.ideaplatformtest.ui.models.ItemUI
import ru.alexandrorlov.ideaplatformtest.utils.convertDateToLong
import ru.alexandrorlov.ideaplatformtest.utils.convertDateToString

fun ItemDBO.toUI(): ItemUI =
    ItemUI(
        id = this.id,
        name = this.name,
        time = this.timestamp.convertDateToString(),
        tags = this.tags,
        amount = this.amount,
    )

fun ItemUI.toDBO(): ItemDBO =
    ItemDBO(
        id = this.id,
        name = this.name,
        timestamp = this.time.convertDateToLong(),
        tags = this.tags,
        amount = this.amount,
    )
