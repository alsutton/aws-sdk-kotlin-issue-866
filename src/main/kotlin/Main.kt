import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.s3.presigners.presign
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.hours

fun main() {
    S3Client {
        region = "eu-west-2"
    }.use {
        val request = GetObjectRequest {
            bucket = "bucket"
            key = "item"
        }

        runBlocking {
            var presignedUrl = request.presign(it.config, 1L.hours).url.toString()
            println("URL : $presignedUrl")
        }
    }
}