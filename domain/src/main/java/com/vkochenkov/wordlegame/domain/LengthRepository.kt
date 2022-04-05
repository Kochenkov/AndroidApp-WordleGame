package com.vkochenkov.wordlegame.domain

interface LengthRepository {

    fun getLength(): Int

    fun setLength(len: Int)
}