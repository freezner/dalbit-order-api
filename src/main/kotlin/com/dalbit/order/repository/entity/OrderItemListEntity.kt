package com.dalbit.order.repository.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "ORDER_ITEM_LIST")
@EntityListeners(AuditingEntityListener::class)
class OrderItemListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    var seq: Long = 0L

    @Column(name = "CATE_CODE", length = 6)
    var cateCode: String = ""

    @Column(name = "ITEM_NAME")
    var itemName: String = ""

    @Column(name = "ITEM_DESCRIPTION", columnDefinition = "text")
    var itemDescription: String = ""

    @Column(name = "ITEM_LABEL_CODE", length = 6)
    var itemLabelCode: String = ""

    @Column(name = "ITEM_TYPE_CODE", length = 6)
    var itemTypeCode: String = ""

    @Column(name = "ITEM_DISPLAY_YN", length = 1)
    var itemDisplayYn: String = ""

    @Column(name = "ITEM_PRICE")
    var itemPrice: Int = 0

    @Column(name = "ITEM_COUNT")
    var itemCount: Int = 0

    @CreatedDate
    @Column(name = "CREATE_DATE", nullable = false, updatable = false)
    var createDate: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    var updateDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "DELETE_DATE")
    var deleteDate: LocalDateTime? = null
}