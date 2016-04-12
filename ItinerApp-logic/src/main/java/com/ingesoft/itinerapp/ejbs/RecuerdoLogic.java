package com.ingesoft.itinerapp.ejbs;


import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RecuerdoLogic implements IBookLogic {

    @Inject private BookPersistence persistence;

    public List<BookDTO> getBooks() {
        return BookConverter.listEntity2DTO(persistence.findAll());
    }

    public BookDTO getBook(Long id) {
        return BookConverter.basicEntity2DTO(persistence.find(id));
    }

    public BookDTO createBook(BookDTO dto) {
        BookEntity entity = BookConverter.basicDTO2Entity(dto);
        persistence.create(entity);
        return BookConverter.basicEntity2DTO(entity);
    }

    public BookDTO updateBook(BookDTO dto) {
        BookEntity entity = persistence.update(BookConverter.basicDTO2Entity(dto));
        return BookConverter.basicEntity2DTO(entity);
    }

    public void deleteBook(Long id) {
        persistence.delete(id);
    }
}
