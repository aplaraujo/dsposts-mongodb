package com.example.dsposts_mongodb.services;

import com.example.dsposts_mongodb.models.dto.PostDTO;
import com.example.dsposts_mongodb.models.dto.UserDTO;
import com.example.dsposts_mongodb.models.entities.Post;
import com.example.dsposts_mongodb.models.entities.User;
import com.example.dsposts_mongodb.repositories.PostRepository;
import com.example.dsposts_mongodb.repositories.UserRepository;
import com.example.dsposts_mongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostDTO findById(String id) {
        Post entity = getEntityById(id);
        return new PostDTO(entity);
    }

    public List<PostDTO> findByTitle(String text) {
        List<Post> list = postRepository.searchTitle(text);
        return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }

    // Método auxiliar para buscar no banco de dados o id
    @Transactional(readOnly = true)
    private Post getEntityById(String id) {
        Optional<Post> obj = postRepository.findById(id);
        Post entity = obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
        return entity;
    }
}
