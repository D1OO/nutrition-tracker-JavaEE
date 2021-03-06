package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import com.fasterxml.jackson.core.type.TypeReference;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.dto.DailyRecordEntryDTO;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 26.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class NewEntriesDTOReader {

    static NewEntriesDTO read(HttpServletRequest request) throws IOException {
        NewEntriesDTO newEntries = readDTO(request);
        newEntries.setEntries(readList(request));
        return newEntries;
    }

    static boolean validateHasErrors(NewEntriesDTO dto) throws NumberFormatException {
        validateNewEntriesQuantity(dto.getEntries());
        return dto.getEntries().stream().anyMatch(x -> Optional.ofNullable(x.getQuantityError()).isPresent());
    }

    static NewEntriesDTO readAddNew(HttpServletRequest request, DailyRecordEntryDTO newEntry) throws IOException {
        NewEntriesDTO newEntriesDTO = readDTO(request);
        List<DailyRecordEntryDTO> newEntriesList = readList(request);

        newEntriesList.add(newEntry);
        newEntriesDTO.setEntries(newEntriesList);

        return newEntriesDTO;
    }

    private static NewEntriesDTO readDTO(HttpServletRequest request) throws IOException {
        return ContextHolder.objectMapper()
                .readValue(request.getParameter("newEntriesDTOJSON"), NewEntriesDTO.class);
    }

    private static List<DailyRecordEntryDTO> readList(HttpServletRequest request) throws IOException {
        return ContextHolder.objectMapper()
                .readValue(request.getParameter("newEntriesJSON"), new TypeReference<ArrayList<DailyRecordEntryDTO>>() {
                });
    }

    private static void validateNewEntriesQuantity(List<DailyRecordEntryDTO> newEntries) throws NumberFormatException {
        int min = Integer.parseInt(String.valueOf(
                PropertiesContainer.DotProperties.APP_PROPERTIES.getProp().get("min-entry-quantity")));
        int max = Integer.parseInt(String.valueOf(
                PropertiesContainer.DotProperties.APP_PROPERTIES.getProp().get("max-entry-quantity")));
        newEntries.forEach(e -> e.setQuantity(Optional.ofNullable(e.getQuantity()).orElse(0)));
        newEntries.stream()
                .filter(e -> e.getQuantity() < min || e.getQuantity() > max)
                .forEach(e -> e.setQuantityError(String.format("Must be between %dg and %dg", min, max)));
    }
}
