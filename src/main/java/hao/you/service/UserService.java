package hao.you.service;

import hao.you.domain.User;

import java.util.List;

public interface UserService {
    public Integer addUser(User user);
    public Integer deleteUserById(Integer id);
    public Integer update(User user);
    public User getUserById(String id);
    public User getUserByName(String name);
    public List<User> getAllUser();
}
