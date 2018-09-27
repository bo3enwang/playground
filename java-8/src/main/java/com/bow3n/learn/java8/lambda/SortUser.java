package com.bow3n.learn.java8.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author bowen
 */
public class SortUser {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        Comparator<User> comparator = Comparator.comparing(User::getUserName);
        Collections.sort(users, comparator);
    }
}
