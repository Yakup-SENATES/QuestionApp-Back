package com.example.fs.services;

import com.example.fs.entities.User;
import com.example.fs.repos.CommentRepository;
import com.example.fs.repos.LikeRepository;
import com.example.fs.repos.PostRepository;
import com.example.fs.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    LikeRepository likeRepository;
    CommentRepository commentRepository;
    PostRepository postRepository;

    public UserService(UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    /*
    * Get All Users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /*
    * Create User
    */
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

      /*
      * Get User by Id
      */
      public User getUserById(Long userId) {
          return  userRepository.findById(userId).orElse(null);
      }


    /*
    * Update User
    */
        public User updateUser(Long userId, User newUser) {
            Optional<User> user =  userRepository.findById(userId);
            if (user.isPresent()){
                User foundUser=user.get();
                foundUser.setUserName(newUser.getUserName());
                foundUser.setPassword(newUser.getPassword());
                userRepository.save(foundUser);
                return foundUser;
            }else return null;
        }

    /*
    * Delete User
    */
        public void deleteUser(Long userId) {
            userRepository.deleteById(userId);
        }

    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }


    public List<Object> getUserActivity(Long userId) {

            List<Long> postIds = postRepository.findTopByUserId(userId);
        System.out.println(postIds+"userId:"+userId);
            if (postIds.isEmpty()) return null;

        System.out.println(commentRepository.findUserCommentsByPostId(postIds));
        return null;
    }
}
