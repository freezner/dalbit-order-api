package com.dalbit.order.service

import com.sun.org.apache.xml.internal.security.utils.Base64
import org.springframework.stereotype.Service
import org.springframework.util.Base64Utils
import java.security.*
import java.util.*
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Service
class CryptoService {
    private val iv: String = "0000000000000001"
    private val keySpec: Key = SecretKeySpec(byteArrayOf(16), "AES")

    fun encrypt(input: String): String {
        val c: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        c.init(Cipher.ENCRYPT_MODE, keySpec, IvParameterSpec(iv.toByteArray()))
        val encrypted = c.doFinal(input.toByteArray())
        val enString = Base64Utils.encode(encrypted)

        return enString.toString()
    }

    fun decrypt(input: String): String {
        val c: Cipher = Cipher.getInstance("AES/CBC/PKC5Padding")
        c.init(Cipher.DECRYPT_MODE, keySpec, IvParameterSpec(iv.toByteArray()))
        val byteStr = Base64Utils.decode(input.toByteArray())

        return c.doFinal(byteStr).toString()
    }
}