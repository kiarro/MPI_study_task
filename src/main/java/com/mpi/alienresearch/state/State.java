package com.mpi.alienresearch.state;

import com.mpi.alienresearch.model.User;

public class State {
    private static User currentUser = null;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}
