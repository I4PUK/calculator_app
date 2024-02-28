package org.example

fun main(){
    val list2 = listOf<String?>(null, "Hello   ", null, null, ",", " World", null, "!")

    print(list2.deleteNullAndParseToString())
}

fun List<String?>.deleteNullAndParseToString() : String {
    var result = ""
    for (element in this) {
        if (!element.isNullOrEmpty()) {
            result += element
        }
    }

    return result
}