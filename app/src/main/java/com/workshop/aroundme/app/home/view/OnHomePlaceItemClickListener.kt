package com.workshop.aroundme.app.home.view

import com.workshop.aroundme.data.model.PlaceEntity

interface OnHomePlaceItemClickListener {

    fun onPlaceItemClicked(placeEntity: PlaceEntity)

    fun onItemStarred(placeEntity: PlaceEntity)
}
