package com.vkochenkov.wordlegame

import org.junit.Test
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import kotlin.collections.ArrayList

class PrepareWordsFile {

    @Test
    fun prepare() {
        val reader = BufferedReader(FileReader(
            "C:\\Users\\User\\Documents\\MyProgrammingProjects\\Android\\AndroidApp-WordleGame\\data\\src\\test\\resources\\eng.txt"
        ))
        val writer = BufferedWriter(FileWriter(
            "C:\\Users\\User\\Documents\\MyProgrammingProjects\\Android\\AndroidApp-WordleGame\\data\\src\\test\\resources\\eng-sorted.txt"
        ))
        var str: String? = reader.readLine()
        val list = ArrayList<String>()


        while (str != null) {
            if (str.length in 4..8) {

                list.add(str)
            }
            str = reader.readLine()
        }
        reader.close()

        list.sort()

        for (s in list) {
            writer.write(s + "\n")
        }

        writer.close()
    }
}