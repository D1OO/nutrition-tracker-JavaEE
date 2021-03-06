package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.DailyRecordDTO;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;
import net.shvdy.nutrition_tracker.model.dao.DailyRecordDAO;
import net.shvdy.nutrition_tracker.model.service.mapper.DailyRecordEntityMapper;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DailyRecordService {

    private final DailyRecordDAO dailyRecordDAO;
    private final DailyRecordEntityMapper dailyRecordMapper;

    public DailyRecordService(DailyRecordDAO dailyRecordDAO, DailyRecordEntityMapper dailyRecordMapper) {
        this.dailyRecordDAO = dailyRecordDAO;
        this.dailyRecordMapper = dailyRecordMapper;
    }

    public void saveNewEntries(NewEntriesDTO newEntriesDTO) {
        dailyRecordDAO.save(DailyRecordEntityMapper.newEntriesToDailyRecord(newEntriesDTO));
    }

    /**
     * Returns daily records for requested period, starting from and including {@code periodEndDate} to {@code quantity}
     * days before it, with date headers localized according to {@code currentLocale}
     * <br><br>
     * Inserts blank records for days, where records are absent
     *
     * @param profileId     User profile id
     * @param periodEndDate The last day of wanted date period
     * @param quantity      Date period size
     * @param currentLocale Dates locale
     * @return Daily records
     */
    public List<DailyRecordDTO> findPaginated(Long profileId, String periodEndDate, int quantity,
                                              Locale currentLocale) {
        return insertBlankForAbsentDays(profileId, periodEndDate, quantity, currentLocale,
                dailyRecordDAO.findByDatePeriodAndQuantity(profileId,
                        LocalDate.parse(periodEndDate).minusDays(quantity - 1).toString(),
                        periodEndDate).stream()
                        .map(entity -> dailyRecordMapper.recordEntityToDTO(entity, currentLocale))
                        .collect(Collectors.toMap(DailyRecordDTO::getRecordDate, Function.identity())));
    }

    private List<DailyRecordDTO> insertBlankForAbsentDays(Long profileId, String periodEndDate, int size, Locale locale,
                                                          Map<String, DailyRecordDTO> weeklyRecords) {
        IntStream.range(0, size)
                .mapToObj(n -> LocalDate.parse(periodEndDate).minusDays(n).toString())
                .forEach(day -> weeklyRecords.putIfAbsent(day, createBlankRecord(profileId, day, locale)));
        return new ArrayList<>(weeklyRecords.values()).stream()
                .sorted(Comparator.comparing(DailyRecordDTO::getRecordDate).reversed())
                .collect(Collectors.toList());
    }

    private DailyRecordDTO createBlankRecord(Long profileId, String date, Locale locale) {
        return DailyRecordDTO.builder()
                .recordDate(date)
                .userProfileId(profileId)
                .dateHeader(dailyRecordMapper.getShortDateHeader(date, locale))
                .entries(new ArrayList<>())
                .build();
    }

}
