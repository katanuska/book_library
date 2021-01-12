package hr.katarina.library;

import hr.katarina.library.book.Book;
import hr.katarina.library.book.BookRepository;
import hr.katarina.library.book.title.Title;
import hr.katarina.library.book.title.TitleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class LoadDatabase implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        if (titleRepository.count() > 0)
            return;

        Title title = new Title("We Were Liars", "E. Lockhart");
        logger.info("Preloading " + titleRepository.save(title));
        logger.info("Preloading " + bookRepository.save(new Book(title, "PR 8923 W6 L36 1990 c.2")));
        logger.info("Preloading " + bookRepository.save(new Book(title, "PR 8923 W6 L36 1990 c.3")));
    }
}