package com.vkochenkov.wordlegame.repository

interface LengthRepository {

    fun getLength(): Int

    fun setLength(len: Int)
}