package org.example

import java.util.Date

fun main() {

}

class Library {
    private var _books: Array<Book>? = null

    fun addBook(book: Book){
        if (_books != null) {
            _books!!.plus(book)
        } else {
            _books = arrayOf(book)
        }
    }

    fun deleteBook(book: Book): Array<Book>? {
        val index = _books?.indexOf(book);
        val mutableBooks = _books?.toMutableList()
        mutableBooks?.remove(book)

        return mutableBooks?.toTypedArray()
    }

    fun findBookByIbsn(ibsn: String): Book? {
        val result = _books?.find {
            if (it.isbn != null && it.isbn == ibsn) {
                return it
            } else {
                return null
            }
        }

        return result
    }

    fun findBookByDescription(query: String) : Book? {
        val result = _books?.find {
            if (it.description != null && it.description!!.contains(query)) {
                return it
            } else {
                return null
            }
        }

        return result
    }

    fun findBookByName(name: String) : Book? {
        val result = _books?.find {
            if (it.name != null && it.name == name) {
                return it
            } else {
                return null
            }
        }

        return result
    }
}

open class Book{
    var name: String? = null
    var isbn: String? = null
    var description: String? = null

    constructor(){
        name = null
        isbn = null
        description = null
    }

    constructor(_name: String, _isbn: String, _description: String){
        name = _name
        isbn = _isbn
        description = _description
    }
}

class ArtBook: Book() {
    var genres: ArtBookGenres = ArtBookGenres.NONE
}

enum class ArtBookGenres {
    DETECTIVE, ROMANTICS, ADVENTURES, NONE,
}

class ScienceBook: Book() {
    var openDate: Date? = null
    var scientistName: String = ""
}

class ChildBook: Book() {
    var ageToRead: Int = 0
}