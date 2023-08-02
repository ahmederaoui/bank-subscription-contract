package com.adriabt.usersservice.services.impl;

import com.adriabt.usersservice.entities.AppUser;
import com.adriabt.usersservice.exceptions.AppUserNotFound;
import com.adriabt.usersservice.repositories.AppUserRepository;
import com.adriabt.usersservice.services.IAppUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AppUserServiceImpl implements IAppUserService {
    private final AppUserRepository appUserRepository;
    @Override
    public AppUser findUserByEmail(String email) throws AppUserNotFound {
        return appUserRepository.findAppUserByEmail(email).orElseThrow(()->new AppUserNotFound(String.format("The user %s not found",email)));
    }
}
