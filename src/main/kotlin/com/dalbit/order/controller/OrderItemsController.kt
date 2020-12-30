package com.dalbit.order.controller

import com.dalbit.order.domain.ItemInfo
import com.dalbit.order.service.OrderItemService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/item")
class OrderItemsController(
    val orderItemService: OrderItemService
) {
    @GetMapping("")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun badRequest() {}

    // 메뉴 전체 조회
    @GetMapping("/list")
    fun getOrderItems() = orderItemService.getOrderItemList()

    // 메뉴 상세 조회
    @GetMapping("/detail/{itemId}")
    fun getOrderItemDetail(
        @PathVariable("itemId") itemId: Long
    ) = orderItemService.getOrderItemDetail(itemId)

    // 메뉴 추가
    @PostMapping("/add")
    fun addItem(
        @RequestBody @Valid itemInfo: ItemInfo
    ) = orderItemService.setOrderItem(itemInfo)

    // 메뉴 수정
    @PutMapping("/modify/{itemId}")
    fun modifyItem(
        @PathVariable("itemId") itemId: Long,
        @RequestBody @Valid itemInfo: ItemInfo
    ) = orderItemService.modifyOrderItem(itemId, itemInfo)

    // 메뉴 삭제
    @DeleteMapping("/remove/{itemId}")
    fun deleteItem(
        @PathVariable("itemId") itemId: Long
    ) = orderItemService.deleteOrderItem(itemId)
}