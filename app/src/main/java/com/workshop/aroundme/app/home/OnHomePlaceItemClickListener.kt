package com.workshop.aroundme.app.home

import com.workshop.aroundme.data.model.PlaceEntity

interface OnHomePlaceItemClickListener {

    fun onPlaceItemCliced(placeEntity: PlaceEntity)

    fun onItemStarred(placeEntity: PlaceEntity)
}
