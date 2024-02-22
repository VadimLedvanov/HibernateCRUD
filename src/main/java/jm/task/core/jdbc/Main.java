package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("vadim", "asd", (byte) 20);
        userService.saveUser("asdvadim", "asd", (byte) 25);
        userService.removeUserById(1);
        System.out.println(userService.getAllUsers());
    }
}
