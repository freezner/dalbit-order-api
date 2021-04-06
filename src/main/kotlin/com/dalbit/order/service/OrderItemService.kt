package com.dalbit.order.service

import com.dalbit.order.domain.ItemInfo
import com.dalbit.order.domain.OrderItem
import com.dalbit.order.domain.OrderItemGroup
import com.dalbit.order.repository.OrderItemListRepository
import com.dalbit.order.repository.ScodeRepository
import com.dalbit.order.repository.entity.OrderItemListEntity
import mu.KLogging
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderItemService(
    val orderItemListRepository: OrderItemListRepository,
    val scodeRepository: ScodeRepository
) {
    companion object: KLogging()

    // 메뉴 목록 조회
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
                            itemId = it.seq,
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

    // 메뉴 상세 정보
    fun getOrderItemDetail(itemId: Long) = orderItemListRepository.findById(itemId)

    // 메뉴 추가
    fun setOrderItem(itemInfo: ItemInfo): OrderItemListEntity {
        var orderItemEntity = OrderItemListEntity()

        orderItemEntity = orderItemListRepository.save(itemInfoEntityConverter(orderItemEntity, itemInfo))
        orderItemListRepository.flush()

        return orderItemEntity
    }

    // 메뉴 수정
    fun modifyOrderItem(itemId: Long, itemInfo: ItemInfo): OrderItemListEntity {
        var orderItemEntity = orderItemListRepository.findBySeq(itemId)
            ?: throw Exception("수정할 대상을 찾을 수 없습니다.")

        orderItemEntity = orderItemListRepository.save(itemInfoEntityConverter(orderItemEntity, itemInfo))

        return orderItemEntity
    }

    // 메뉴 삭제
    fun deleteOrderItem(itemId: Long): OrderItemListEntity {
        val orderItemEntity = orderItemListRepository.findBySeq(itemId)
            ?: throw Exception("수정할 대상을 찾을 수 없습니다.")

        orderItemListRepository.save(orderItemEntity.apply {
            this.deleteDate = LocalDateTime.now()
        })

        return orderItemEntity
    }

    // itemInfo DTO를 Entity로 변환
    private fun itemInfoEntityConverter(entity: OrderItemListEntity, itemInfo: ItemInfo): OrderItemListEntity {
        return entity.apply {
            this.cateCode = itemInfo.cateCode
            this.itemName = itemInfo.itemName
            this.itemDescription = itemInfo.itemDescription.toString()
            this.itemLabelCode = itemInfo.itemLabelCode.toString()
            this.itemTypeCode = itemInfo.itemTypeCode.toString()
            this.itemDisplayYn = itemInfo.itemDisplayYn
            this.itemPrice = itemInfo.itemPrice
            this.itemCount = itemInfo.itemCount
        }
    }
}