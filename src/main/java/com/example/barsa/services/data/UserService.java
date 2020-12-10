package com.example.barsa.services.data;

import com.example.barsa.db.entities.User;
import com.example.barsa.db.repositories.UserRepository;
import com.example.barsa.dto.UserDto;
import com.example.barsa.exceptions.DbException;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
       Optional<User> optional = repository.findById(id);

       return optional.orElse(null);
    }

    public User getUserByLogin(String login) {
        List<User> repositoryResult = repository.findByLogin(login);

        if (repositoryResult.size() > 1) {
            LOG.warn("Found more than 1 user by login", new DbException());
        }

        return CollectionUtils.firstElement(repositoryResult);
    }

    public List<User> getUsers(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        return repository.findAll(pageable).getContent();
    }

    public List<User> findUsersByParams(UserDto knownParams) {
        User probe = new User();

        probe.setLogin(knownParams.getLogin());
        probe.setFirstName(knownParams.getFirstName());
        probe.setLastName(knownParams.getLastName());
        probe.setEmail(knownParams.getEmail());

        Example<User> example = Example.of(probe);

        return repository.findAll(example);
    }

    public User createUser(User userEntity) {
        return repository.save(userEntity);
    }

    public User updateUser(User userEntity) {
        return repository.save(userEntity);
    }

    public void deleteUser(User userEntity) {
        repository.delete(userEntity);
    }
}
