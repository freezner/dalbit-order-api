package com.dalbit.order.repository

import com.dalbit.order.repository.entity.OrderItemListEntity
import com.dalbit.order.repository.entity.QOrderItemListEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

interface OrderItemListRepositoryCustom {
    fun findBySeq(seq: Long): OrderItemListEntity?
    fun findAllByItemDisplayYn(displayYn: String): List<OrderItemListEntity>
}

@Repository
interface OrderItemListRepository:
    JpaRepository<OrderItemListEntity, Long>,
    OrderItemListRepositoryCustom

class OrderItemListRepositoryImpl:
    QuerydslRepositorySupport(OrderItemListEntity::class.java),
    OrderItemListRepositoryCustom
{
    val table: QOrderItemListEntity = QOrderItemListEntity.orderItemListEntity

    override fun findBySeq(seq: Long): OrderItemListEntity? {
        return from(table)
            .where(
                table.seq.eq(seq)
                    .and(table.deleteDate.isNull)
            )
            .fetchFirst()
    }

    override fun findAllByItemDisplayYn(displayYn: String): List<OrderItemListEntity> {
        return from(table)
            .where(
                table.itemDisplayYn.eq(displayYn)
                    .and(table.deleteDate.isNull)
            )
            .fetch()
    }
}