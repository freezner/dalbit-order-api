package com.dalbit.order.service

import com.dalbit.order.domain.OrderItem
import com.dalbit.order.domain.OrderItemGroup
import com.dalbit.order.repository.OrderItemListRepository
import com.dalbit.order.repository.ScodeRepository
import org.springframework.stereotype.Service

@Service
class OrderItemService(
    val orderItemListRepository: OrderItemListRepository,
    val scodeRepository: ScodeRepository
) {

    fun getOrderItemList(displayYn: String = "Y"): List<OrderItemGroup> {
        val orderItemListEntity = orderItemListRepository.findAllByItemDisplayYn(displayYn)

        val orderCategoryList = orderItemListEntity.groupBy { it.cateCode }.map { it.key to it.value }
        val orderItemList = mutableListOf<OrderItemGroup>()

        orderCategoryList.forEach { pair ->
            // 카테고리 코드로 메뉴 분류 가져오기
            val codeEntity = scodeRepository.findById(pair.first)

            orderItemList.add(
                OrderItemGroup(
                    title = codeEntity.get().codeValue,
                    items = pair.second.map {
                        // 메뉴 속성 코드를 가져온다.
                        val itemProperty = if (it.itemTypeCode != "")
                            scodeRepository.findById(it.itemTypeCode).get().codeValue
                            else ""

                        OrderItem(
                            name = it.itemName,
                            priceMin = 0,
                            price = it.itemPrice,
                            stockCount = it.itemCount,
                            isShow = it.itemDisplayYn,
                            itemProperty = itemProperty
                        )
                    }.toList()
                )
            )
        }

        return orderItemList
    }
}