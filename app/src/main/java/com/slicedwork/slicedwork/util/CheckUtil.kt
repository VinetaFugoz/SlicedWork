package com.slicedwork.slicedwork.util

import com.slicedwork.slicedwork.enum.IcOccupationAreaEnum

fun getImgOccupationArea(occupationArea: String): Int {
    return when (occupationArea) {
        IcOccupationAreaEnum.PAINTING.toString() -> IcOccupationAreaEnum.PAINTING.icOccupationArea
        IcOccupationAreaEnum.CLEANING.toString() -> IcOccupationAreaEnum.CLEANING.icOccupationArea
        IcOccupationAreaEnum.CONTRUCTION.toString() -> IcOccupationAreaEnum.CONTRUCTION.icOccupationArea
        IcOccupationAreaEnum.ELECTRIC.toString() -> IcOccupationAreaEnum.ELECTRIC.icOccupationArea
        IcOccupationAreaEnum.PLUMBING.toString() -> IcOccupationAreaEnum.PLUMBING.icOccupationArea
        else -> IcOccupationAreaEnum.GARDENING.icOccupationArea
    }
}