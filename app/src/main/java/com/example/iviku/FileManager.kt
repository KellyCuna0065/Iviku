package com.example.iviku

import android.util.Log
import java.io.File
import java.io.IOException

class FileManager {

    lateinit var content: List<String>

    fun createFolder(folderName: String): Boolean {
        val folder = File(folderName)

        return try {
            folder.mkdir()
            Log.i("FILE MANAGER", "Folder created!")
            true
        } catch (e: IOException) {
            e.printStackTrace()
            Log.i("FILE MANAGER", "Folder not created :c")
            false
        }
    }

    fun createFile(fileName: String): Boolean {
        val file = File(fileName)
        return try {
            file.bufferedWriter().use { writer ->
                for (line in content) {
                    writer.write(line)
                    writer.newLine()
                }
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun doesDirectoryExist(directoryPath: String):Boolean {
        val directory = File(directoryPath)
        return directory.exists() && directory.isDirectory
    }

}