import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.lang.Exception
//Spain, German, Greek, Italian
//Far East: Japanese, China, Korea
//Central Asia: Kazakhstan, Turkmen, Uzbek, Tajik

fun parseWebsite() {
    val url = "https://www.behindthename.com/names/gender/masculine/usage/nepali/relationship/core/"

    val fileName = "C:\\Users\\m9132\\IdeaProjects\\parsing\\src\\main\\kotlin\\names.txt"
    val file = File(fileName)

    val namesList = mutableListOf<String>()

    file.forEachLine { line ->
        val parts = line.split(", ")
        if (parts.size >= 2) {
            val name = parts[1].trim()
            namesList.add(name)
        }
    }
    for (page in 1..2) {
        val url = url + page
        try {
            val doc: Document = Jsoup.connect(url).get()

            val nameElements = doc.select("div.browsename a.nll")
            for (nameElement in nameElements) {
                val rawName = nameElement.text()
                val cleanedName = rawName.replace(Regex("[0-9]+"), "").trim()
                if (!namesList.contains(cleanedName)) {
                    println("South Asia, $cleanedName, f")
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