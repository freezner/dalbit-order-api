package com.dalbit.order.repository

import com.dalbit.order.repository.entity.OrderItemListEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemListRepository: JpaRepository<OrderItemListEntity, Long> {
    fun findAllByItemDisplayYn(displayYn: String): List<OrderItemListEntity>
}