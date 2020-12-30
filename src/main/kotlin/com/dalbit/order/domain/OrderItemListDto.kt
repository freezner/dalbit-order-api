package com.dalbit.order.domain

import javax.validation.constraints.NotBlank

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

data class ItemInfo(
    @NotBlank
    var cateCode: String,

    @NotBlank
    var itemName: String,

    var itemDescription: String?,

    var itemLabelCode: String?,

    var itemTypeCode: String?,

    @NotBlank
    var itemDisplayYn: String,

    @NotBlank
    var itemPrice: Int,

    @NotBlank
    var itemCount: Int
)