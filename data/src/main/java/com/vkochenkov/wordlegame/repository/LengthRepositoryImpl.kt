package com.vkochenkov.wordlegame.repository

import com.vkochenkov.wordlegame.sharedprefs.LengthSharedPrefs

class LengthRepositoryImpl(
    private val sharedPrefs: LengthSharedPrefs
) : LengthRepository {

    override fun getLength(): Int {
        return sharedPrefs.getLength()
    }

    override fun setLength(len: Int) {
        sharedPrefs.setLength(len)
    }
}