package com.dalbit.order.controller

import com.dalbit.order.service.OrderItemService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
class OrderItemsController(
    val orderItemService: OrderItemService
) {
    // 메뉴 전체 조회
    @GetMapping("")
    fun getOrderItems() = orderItemService.getOrderItemList()

    // 메뉴 상세 조회
    @GetMapping("/{itemId}")
    fun getOrderItemDetail(
        @PathVariable("itemId") itemId: String
    ) { }

    @PostMapping("/add")
    fun addItem() { }

    @PutMapping("/{itemId}")
    fun modifyItem(
        @PathVariable("itemId") itemId: String
    ) { }

    @DeleteMapping("/{itemId}")
    fun deleteItem(
        @PathVariable("itemId") itemId: String
    ) { }
}