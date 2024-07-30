package org.example.presentation.service

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.Base64
import javax.imageio.ImageIO

@Service
class KeypadService {

    fun getEncodedImage(): String {
        val imagePaths = (0..9).map { "static/images/_$it.png" }
        val combinedImage = combineImages(imagePaths)
        val imageBytes = bufferedImageToByteArray(combinedImage)
        return Base64.getEncoder().encodeToString(imageBytes)
    }

    private fun combineImages(imagePaths: List<String>): BufferedImage {
        val images = imagePaths.map { ClassPathResource(it).inputStream.use { stream -> ImageIO.read(stream) } }
        val width = images[0].width * 3
        val height = images[0].height * 4
        val combinedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val g = combinedImage.graphics

        for (i in images.indices) {
            val x = (i % 3) * images[0].width
            val y = (i / 3) * images[0].height
            g.drawImage(images[i], x, y, null)
        }
        g.dispose()
        return combinedImage
    }

    private fun bufferedImageToByteArray(image: BufferedImage): ByteArray {
        val outputStream = ByteArrayOutputStream()
        ImageIO.write(image, "png", outputStream)
        return outputStream.toByteArray()
    }
}