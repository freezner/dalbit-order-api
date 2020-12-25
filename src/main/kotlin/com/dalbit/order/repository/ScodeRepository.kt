package com.dalbit.order.repository

import com.dalbit.order.repository.entity.ScodeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScodeRepository: JpaRepository<ScodeEntity, String> {

}