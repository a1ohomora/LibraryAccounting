package ru.rozvezev.libraryaccounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rozvezev.libraryaccounting.models.Book;
import ru.rozvezev.libraryaccounting.models.Person;


import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByOwner(Person owner);

    List<Book> findByNameStartingWith(String startingWith);

    @Modifying
    @Query("update Book b set b.owner.personId = :personId, b.takenAt = current_date where b.bookId = :bookId")
    void assign(@Param("bookId") int bookId, @Param("personId") int personId);

}
