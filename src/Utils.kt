import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)


fun List<String>.splitOnEmpty(): List<List<Int>>{
    val output = mutableListOf(mutableListOf<Int>())
    this.forEach {
        if(it.toIntOrNull() != null){
            output.last().add(it.toInt())
        } else {
            output.add(mutableListOf())
        }
    }
    return output
}

fun List<T>.splitOnEmptyString(): List<List<T>>{
    val output = mutableListOf(mutableListOf<String>())
    this.forEach {
        if(!it.isEmpty()){
            output.last().add(it.toInt())
        } else {
            output.add(mutableListOf())
        }
    }
    return output
}