package org.example.domain.model.user;

import lombok.Getter;

@Getter
public final class User {
    private final UserId userId;
    private final UserName userName;
    private final Email email;
    private Password password;
    private final BirthDate birthDate;

    public User(UserName userName, Email email, Password password, BirthDate birthDate) {
        if(userName == null){
            throw new IllegalArgumentException("UserName cannot be null");
        }
        if(email == null){
            throw new IllegalArgumentException("Email cannot be null");
        }
        if(password == null){
            throw new IllegalArgumentException("Password cannot be null");
        }
        if(birthDate == null){
            throw new IllegalArgumentException("BirthDate cannot be null");
        }

        userId = UserId.generate();
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public void changePassword(Password newPassword) {
        if (newPassword == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        this.password = newPassword;
    }

}
