package com.holu31.brainwave.render

import java.io.FileNotFoundException
import java.io.InputStream

class Asset constructor(val path: String) {
	
	val stream: InputStream
		get() = javaClass.classLoader.getResourceAsStream(path)
			?: throw FileNotFoundException("failed to load file at path $path")
	
	val bytes: ByteArray
		get() = stream.use { it.readAllBytes() } // use автоматически закрывает поток после выполнения всех операций
	
	val text: String
		get() = bytes.decodeToString()
	
}