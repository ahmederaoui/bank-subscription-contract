package com.adriabt.usersservice.services;

import com.adriabt.usersservice.entities.AppUser;
import com.adriabt.usersservice.exceptions.AppUserNotFound;

public interface IAppUserService {
    AppUser findUserByEmail(String email) throws AppUserNotFound;
}
