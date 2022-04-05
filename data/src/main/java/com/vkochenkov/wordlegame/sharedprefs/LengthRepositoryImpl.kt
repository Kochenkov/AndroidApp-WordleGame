package com.vkochenkov.wordlegame.sharedprefs

import com.vkochenkov.wordlegame.domain.LengthRepository
import com.vkochenkov.wordlegame.domain.model.Language

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