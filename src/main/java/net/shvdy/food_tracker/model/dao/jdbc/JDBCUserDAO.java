package net.shvdy.food_tracker.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Optional;

import javax.sql.DataSource;

import net.shvdy.food_tracker.model.dao.UserDAO;
import net.shvdy.food_tracker.model.dao.mapper.ObjectMapper;
import net.shvdy.food_tracker.model.entity.User;

public class JDBCUserDAO implements UserDAO {

	private static final String INSERT_USER_SQL = "INSERT INTO user"
			+ "(email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, role) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?);";

	private static final String INSERT_USER_PROFILE_SQL = "INSERT INTO user_profile"
			+ "(profile_id, first_name, last_name) "
			+ "VALUES (?, ?, ?);";

	private static final String SELECT_BY_USERNAME_SQL = "SELECT *" +
			" FROM user INNER JOIN user_profile ON user.id = user_profile.profile_id WHERE email = ?;";

	private DataSource dataSource;
	private ObjectMapper<User> objectMapper;

	public JDBCUserDAO(DataSource dataSource, ObjectMapper<User> objectMapper) {
		this.dataSource = dataSource;
		this.objectMapper = objectMapper;
	}

	@Override
	public void create(User user) throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement insertUserStatement = connection.prepareStatement(INSERT_USER_SQL);
			 PreparedStatement insertUserProfileStatement = connection.prepareStatement(INSERT_USER_PROFILE_SQL)) {

			connection.setAutoCommit(false);

			insertUserStatement.setString(1, user.getUsername());
			insertUserStatement.setString(2, user.getPassword());
			insertUserStatement.setBoolean(3, true);
			insertUserStatement.setBoolean(4, true);
			insertUserStatement.setBoolean(5, true);
			insertUserStatement.setBoolean(6, true);
			insertUserStatement.setString(7, user.getRole().name());
			insertUserStatement.executeUpdate();

			insertUserProfileStatement.setLong(1, user.getId());
			insertUserProfileStatement.setString(2, user.getUserProfile().getFirstName());
			insertUserProfileStatement.setString(3, user.getUserProfile().getLastName());
			insertUserProfileStatement.executeUpdate();

			connection.commit();
		}
	}

	@Override
	public Optional<User> findByUsername(String username) throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SELECT_BY_USERNAME_SQL)) {

			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(objectMapper.mapResultSet(resultSet));
				}
			}
		}
		return Optional.empty();
	}
}
