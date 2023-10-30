import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.lang.Exception

fun parseWebsite() {
    val url = "https://www.behindthename.com/names/gender/feminine/usage/english/language/english/relationship/core/"

    val fileName = "C:\\Users\\m9132\\IdeaProjects\\parsing\\src\\main\\kotlin\\names.txt"
    val file = File(fileName)
    val namesSet = mutableSetOf<String>()

    for (page in 1..7) {
        val url = url + page
        try {
            val doc: Document = Jsoup.connect(url).get()

            val nameElements = doc.select("div.browsename a.nll")
            for (nameElement in nameElements) {
                val rawName = nameElement.text()
                val cleanedName = rawName.replace(Regex("[0-9]+"), "").trim()
                if (cleanedName !in namesSet) {
                    namesSet.add(cleanedName)
                    println("English, $cleanedName, f")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun main(args: Array<String>) {
    parseWebsite()
}