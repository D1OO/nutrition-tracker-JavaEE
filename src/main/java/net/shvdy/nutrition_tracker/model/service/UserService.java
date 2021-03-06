package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.dto.UserProfileDTO;
import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.entity.Notification;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.exception.BadCredentialsException;
import net.shvdy.nutrition_tracker.model.service.mapper.UserEntityMapper;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class UserService {

    private UserDAO userDao;
    private UserEntityMapper entityMapper;

    public UserService(UserDAO userDao, UserEntityMapper entityMapper) {
        this.userDao = userDao;
        this.entityMapper = entityMapper;
    }

    public void save(User user) {
        userDao.create(user);
    }

    public void updateProfile(UserProfileDTO userProfileDTO) {
        userDao.updateProfile(entityMapper.userProfileDTOToEntity(userProfileDTO));
    }

    public UserDTO findByUsername(String username) {
        return entityMapper.entityToDTO(userDao.findByUsername(username)
                .orElseThrow(NoSuchElementException::new));
    }

    public UserDTO findByUsernameVerifyPassword(String username, String password)
            throws BadCredentialsException {
        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException(String.format("username '%s' not found", username)));
        if (!BCrypt.checkpw(password, user.getPassword()))
            throw new BadCredentialsException(String.format("wrong password for username: %s", username));
        return entityMapper.entityToDTO(user);
    }

    public List<User> findGroup(String adminUsername) {
        return userDao.findGroup(adminUsername);
    }

    public Set<Notification> findNotifications(UserDTO receiver) {
        return userDao.findNotifications(receiver);
    }

    public void acceptGroupInvitation(Notification invite) {
        userDao.acceptGroupInvitation(invite);
    }

    public void declineGroupInvitation(Notification invite) {
        userDao.declineGroupInvitation(invite);
    }

    public void sendGroupInvitation(Notification invite) {
        userDao.createGroupInvitation(invite);
    }

}
