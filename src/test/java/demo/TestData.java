package demo;

import demo.model.User;

public class TestData {

    public static User createUser() {
        return new User(
                "The Octocat",
                "https://github.blog"
        );
    }
}
