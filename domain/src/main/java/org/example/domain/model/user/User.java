package org.example.domain.model.user;

import lombok.Getter;
import org.example.domain.validation.ValidationUtils;

@Getter
public final class User {
    private final UserId userId;
    private UserName userName;
    private Email email;
    private Password password;
    private BirthDate birthDate;

    public User(UserId userId, UserName userName, Email email, Password password, BirthDate birthDate) {
        this.userId = ValidationUtils.requireNonNull(userId, "UserId cannot be null");
        this.userName = ValidationUtils.requireNonNull(userName, "UserName cannot be null");
        this.email = ValidationUtils.requireNonNull(email, "Email cannot be null");
        this.password = ValidationUtils.requireNonNull(password, "Password cannot be null");
        this.birthDate = ValidationUtils.requireNonNull(birthDate, "BirthDate cannot be null");
    }
    public static User create(UserName userName, Email email, Password password, BirthDate birthDate) {
        return new User(UserId.generate(), userName, email, password, birthDate);
    }

    public void changePassword(Password newPassword) {
        this.password = ValidationUtils.requireNonNull(newPassword, "New password cannot be null");
    }
    public void changeUserName(UserName newUserName) {
        this.userName = ValidationUtils.requireNonNull(newUserName, "New user name cannot be null");
    }
    public void changeEmail(Email newEmail) {
        this.email = ValidationUtils.requireNonNull(newEmail, "New email cannot be null");
    }
    public void changeBirthDate(BirthDate newBirthDate) {
        this.birthDate = ValidationUtils.requireNonNull(newBirthDate, "New birth date cannot be null");
    }
}
