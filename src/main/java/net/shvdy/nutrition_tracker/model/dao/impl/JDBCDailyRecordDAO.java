package net.shvdy.nutrition_tracker.model.dao.impl;

import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.dao.mapper.ResultSetMapper;
import net.shvdy.nutrition_tracker.model.entity.DailyRecord;
import net.shvdy.nutrition_tracker.model.entity.DailyRecordEntry;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class JDBCDailyRecordDAO implements DailyRecordDAO {

	private DataSource dataSource;
	private ResultSetMapper<DailyRecord> resultSetMapper;
	private Properties queries;

	public JDBCDailyRecordDAO(DataSource dataSource, ResultSetMapper<DailyRecord> resultSetMapper, Properties queries) {
		this.dataSource = dataSource;
		this.resultSetMapper = resultSetMapper;
		this.queries = queries;
	}

//	@Override
//	public void create(DailyRecord dailyRecord) throws SQLException {
//		try (Connection connection = dataSource.getConnection();
//			 PreparedStatement insertUserStatement = connection
//					 .prepareStatement(queries.getProperty("userdao.INSERT_DAILY_RECORD_SQL"));
//			 PreparedStatement insertUserProfileStatement = connection
//					 .prepareStatement(queries.getProperty("userdao.INSERT_USER_PROFILE_SQL"))) {
//
////
//			insertUserProfileStatement.executeUpdate();
//
//			connection.commit();
//		}
//	}

	public Optional<DailyRecord> findByRecordDate(String recordDate) throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection
					 .prepareStatement(queries.getProperty("daily_record_dao.SELECT_BY_RECORDDATE_SQL"))) {

			statement.setString(1, recordDate);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(resultSetMapper.map(resultSet));
				}
			}
		}
		return Optional.empty();
	}

	public void save(DailyRecord dailyRecord) throws SQLException {
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement insertDailyRecordStatement = connection
					 .prepareStatement(queries.getProperty("daily_record_dao.INSERT_DAILY_RECORD_SQL"));
			 PreparedStatement insertEntriesStatement = connection
					 .prepareStatement(queries.getProperty("daily_record_dao.INSERT_ENTRIES_SQL"))) {

			connection.setAutoCommit(false);

			insertDailyRecordStatement.setLong(1, dailyRecord.getRecordId());
			insertDailyRecordStatement.setLong(2, dailyRecord.getUserProfileId());
			insertDailyRecordStatement.setString(3, dailyRecord.getRecordDate());
			insertDailyRecordStatement.setInt(4, dailyRecord.getDailyCaloriesNorm());

			for (DailyRecordEntry entry : dailyRecord.getEntries()) {
				insertEntriesStatement.setLong(1, entry.getFood().getFoodId());
				insertEntriesStatement.setLong(2, dailyRecord.getRecordId());
				insertEntriesStatement.setInt(3, entry.getQuantity());
				try {
					insertEntriesStatement.setLong(4, entry.getEntryId());
				} catch (NullPointerException e) {
					insertEntriesStatement.setNull(4, Types.BIGINT);
				}

				insertEntriesStatement.addBatch();
			}

			insertDailyRecordStatement.executeUpdate();
			insertEntriesStatement.executeBatch();

			connection.commit();
		}
	}

	@Override
	public void create(DailyRecord entity) throws SQLException {

	}
}
