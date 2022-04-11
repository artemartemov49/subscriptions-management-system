package com.artem.subscriptionsmanagementsystem.service;

import com.artem.subscriptionsmanagementsystem.database.repository.UserRepository;
import com.artem.subscriptionsmanagementsystem.dto.user.UserCreateEditDto;
import com.artem.subscriptionsmanagementsystem.dto.user.UserReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.user.UserCreateEditMapper;
import com.artem.subscriptionsmanagementsystem.mapper.user.UserReadMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
            .map(userReadMapper::map)
            .toList();
    }

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id)
            .map(userReadMapper::map);
    }

    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
            .map(userCreateEditMapper::map)
            .map(userRepository::save)
            .map(userReadMapper::map)
            .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Integer id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
            .map(entity -> userCreateEditMapper.map(userDto, entity))
            .map(userRepository::saveAndFlush)
            .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return userRepository.findById(id)
            .map(entity -> {
                userRepository.delete(entity);
                userRepository.flush();
                return true;
            })
            .orElse(false);
    }
}
