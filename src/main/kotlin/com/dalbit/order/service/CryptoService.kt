package com.dalbit.order.service

import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.stereotype.Service
import java.security.*
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Service
class CryptoService {
    private val iv: String = "sksmsqkrgudwlsdlek".substring(0, 16)
    private val keySpec: Key = SecretKeySpec(iv.toByteArray(), "AES")
    private val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    private val escape: String = "|es|"

    fun encrypt(input: String): String {
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, IvParameterSpec(iv.toByteArray()))

        return Base64
            .encodeBase64String(cipher.doFinal(input.toByteArray()))
            .toString().replace("/", escape)
    }

    fun decrypt(input: String): String {
        cipher.init(Cipher.DECRYPT_MODE, keySpec, IvParameterSpec(iv.toByteArray()))

        return cipher
            .doFinal(Base64.decodeBase64(input.replace(escape, "/")))
            .decodeToString()
    }
}