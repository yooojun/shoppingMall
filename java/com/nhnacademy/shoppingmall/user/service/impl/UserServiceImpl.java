package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.service.UserService;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId) {
        //todo#4-1 회원조회
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            return null;
//            throw new UserNotFoundException(userId);
        return user.get();
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        if (userRepository.countByUserId(user.getUserId()) > 0) {
            throw new UserAlreadyExistsException(user.getUserId());
        }
        int result = userRepository.save(user);
        if (result < 1) {
            throw new RuntimeException("save fail");
        }

    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        if (userRepository.countByUserId(user.getUserId()) < 1) {
            throw new UserNotFoundException(user.getUserId());
        }
        int result = userRepository.update(user);
        if (result < 1) {
            throw new RuntimeException("update fail");
        }

    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        if (userRepository.countByUserId(userId) < 1) {
            throw new UserNotFoundException(userId);
        }
        int result = userRepository.deleteByUserId(userId);
        if (result < 1) {
            throw new RuntimeException("delete fail");
        }

    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        Optional<User> userOptional = userRepository.findByUserIdAndUserPassword(userId, userPassword);
        if (userOptional.isEmpty())
            throw new UserNotFoundException(userId);
        int result = userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
        if (result < 1) {
            throw new RuntimeException("doLogin");
        }
        return userOptional.get();
    }

}
