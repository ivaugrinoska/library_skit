package mk.ukim.finki.library_vp.utils;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.enumerations.Role;

public class BaseTestData {

    public static User getUser() {
        return new User("username", "password", "name", "surname", Role.ROLE_USER);
    }

    public static Category getCategory() {
        return new Category("category1");
    }

    public static Book getBook() {
        return new Book("Book1", "Author1", 10, 3.5F, "description", "url", getCategory());
    }
}
