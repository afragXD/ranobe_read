package com.ranobe_read.database.books

import com.ranobe_read.data.Authors
import com.ranobe_read.data.BookGenres
import com.ranobe_read.data.Books.check
import com.ranobe_read.data.Books.default
import com.ranobe_read.data.Books.nullable
import com.ranobe_read.data.Countries
import com.ranobe_read.data.Genres
import com.ranobe_read.features.books.details.BooksDetailsResponseRemote
import com.ranobe_read.features.books.menu.BooksMenuResponseRemote
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.transactions.transaction

object Books : Table("books") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val enName = varchar("en_name", 100)
    val image = varchar("image", 150)
    val descript = text("descript")
    val rating = decimal("rating", 3, 2)
    val status = varchar("status", 50)
    val chapters = integer("chapters")
    val year = integer("year").check { it greaterEq 0 }
    val author = reference("author_id", Authors)
    val ratingCount = integer("rating_count").default(0)
    val country = reference("country_id", Countries)


    fun selectAllBooks(limit: Int, sortBy: Pair<Column<*>, SortOrder>): List<BooksMenuResponseRemote> {
        return try {
            transaction {
                Books.selectAll()
                    .limit(limit)
                    .orderBy(sortBy.first to sortBy.second)
                    .toList()
                    .map{
                        BooksMenuResponseRemote(
                            id = it[Books.id],
                            name = it[name],
                            descript = it[descript],
                            rating = it[rating].toDouble(),
                            image = it[image],
                            status = it[status]
                        )
                    }
            }
        } catch (e: Exception){
            emptyList()
        }
    }

    fun getBookDetailsById(bookId: Int): BooksDetailsResponseRemote? {
        return transaction {
            val bookRow = (Books innerJoin Authors innerJoin Countries)
                .select { Books.id eq bookId }
                .singleOrNull() ?: return@transaction null

            val genres = (BookGenres innerJoin Genres)
                .slice(Genres.name)
                .select { BookGenres.book eq bookId }
                .map { it[Genres.name] }
                .toSet()

            BooksDetailsResponseRemote(
                id = bookRow[Books.id],
                name = bookRow[Books.name],
                image = bookRow[Books.image],
                descript = bookRow[Books.descript],
                rating = bookRow[Books.rating].toDouble(),
                status = bookRow[Books.status],
                chapters = bookRow[Books.chapters],
                year = bookRow[Books.year],
                authorName = bookRow[Authors.name],
                countryName = bookRow[Countries.name],
                genres = genres,
                ratingCount = bookRow[Books.ratingCount]
            )
        }
    }

}