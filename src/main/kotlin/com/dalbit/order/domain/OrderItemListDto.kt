package com.dalbit.order.domain

data class OrderItemGroup(
    var title: String,
    var items: List<OrderItem>?
)

data class OrderItem(
    var name: String,
    var priceMin: Int?,
    var price: Int,
    var stockCount: Int,
    var isShow: String,
    var itemProperty: String?
)