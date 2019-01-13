package guru.springframework.spring5webapp.bootsrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author a1 = new Author("Eric", "Evans");
        Publisher p1 = new Publisher("Apress", "London");
        Book b1 = new Book("DDD", "1234", p1);
        a1.getBooks().add(b1);
        b1.getAuthors().add(a1);
        b1.getPublisher().setName(p1.getName());
        b1.getPublisher().setAddress(p1.getAddress());
        authorRepository.save(a1);
        publisherRepository.save(p1);
        bookRepository.save(b1);


        Author a2 = new Author("Rod", "Johnoson");
        Publisher p2 = new Publisher("Helion", "Warsaw");
        Book b2 = new Book("J2EE", "4567", p2);
        a2.getBooks().add(b2);
        b2.getAuthors().add(a2);
        b2.getPublisher().setName(p2.getName());
        b2.getPublisher().setAddress(p2.getAddress());
        authorRepository.save(a2);
        publisherRepository.save(p2);
        bookRepository.save(b2);

    }
}
