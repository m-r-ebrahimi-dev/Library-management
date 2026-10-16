package org.example.librarymanagement.user;

import org.example.librarymanagement.common.BusinessException;

public enum Role {
    MEMBER,
    LIBRARIAN,
    ADMIN;

    public static Role fromString(String role) {
        switch (role.toUpperCase()) {
            case "MEMBER":
                return MEMBER;
            case "LIBRARIAN":
                return LIBRARIAN;
            case "ADMIN":
                return ADMIN;
            default:
                throw new BusinessException("Invalid role");
        }
    }
}