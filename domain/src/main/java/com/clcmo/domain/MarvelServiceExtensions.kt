package com.clcmo.domain

import java.security.NoSuchAlgorithmException

fun String.toMD5(): String {
    try {
        // Create MD5 Hash
        val digest = java.security.MessageDigest.getInstance("MD5")
        digest.update(this.toByteArray())
        return digest.digest().toHex()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}

fun ByteArray.toHex() : String {
    val stringBuilder = StringBuilder()
    for (byte in this) {
        stringBuilder.append(String.format("%02x", byte))
    }
    return stringBuilder.toString()
}