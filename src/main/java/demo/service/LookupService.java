package demo.service;

import demo.model.User;

import java.util.concurrent.CompletableFuture;

public interface LookupService {

    CompletableFuture<User> findUser(String user) throws InterruptedException;
}
