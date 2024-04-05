package com.example.iviku

import java.io.File
import java.io.IOException

class FileManager {

    fun createFolder(folderName: String): Boolean {
        val folder = File(folderName)

        return try {
            folder.mkdirs()
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun createFile(fileName: String, content: List<String>): Boolean {
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