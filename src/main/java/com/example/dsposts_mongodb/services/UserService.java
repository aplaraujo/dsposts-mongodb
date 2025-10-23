package com.example.dsposts_mongodb.services;

import com.example.dsposts_mongodb.models.dto.UserDTO;
import com.example.dsposts_mongodb.models.entities.User;
import com.example.dsposts_mongodb.repositories.UserRepository;
import com.example.dsposts_mongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO findById(String id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
        return new UserDTO(entity);
    }
}
