package com.dalbit.order.repository.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.Id

@Entity(name = "S_CODE")
@EntityListeners(AuditingEntityListener::class)
class ScodeEntity {

    @Column(name = "MAIN_CODE", length = 2)
    var mainCode: String = ""

    @Column(name = "MID_CODE", length = 2)
    var midCode: String = ""

    @Column(name = "SUB_CODE", length = 2)
    var subCode: String = ""

    @Id
    @Column(name = "FULL_CODE", length = 6)
    var fullCode: String = ""

    @Column(name = "CODE_VALUE")
    var codeValue: String = ""

    @Column(name = "ORDER_NO")
    var orderNo: Int? = 1

    @Column(name = "USE_YN", length = 1)
    var useYn: String = ""

    @Column(name = "CODE_MEMO", columnDefinition = "text")
    var codeMemo: String? = ""

    @CreatedDate
    @Column(name = "CREATE_DATE", nullable = false, updatable = false)
    var createDate: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    var updateDate: LocalDateTime = LocalDateTime.now()
}