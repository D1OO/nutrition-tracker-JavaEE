package net.shvdy.nutrition_tracker.model.dao;

import java.sql.SQLException;
import java.util.Optional;

import net.shvdy.nutrition_tracker.model.entity.User;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByUsername(String username) throws SQLException;
}