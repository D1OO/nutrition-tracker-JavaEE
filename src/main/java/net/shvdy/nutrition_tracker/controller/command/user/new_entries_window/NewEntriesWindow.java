package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Optional;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class NewEntriesWindow implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        createNewEntriesDTO(request);
        return "/view/user/add-new-entries-window/window.jsp";
    }

    private void createNewEntriesDTO(HttpServletRequest request) {
        request.getSession().getServletContext().setAttribute("newEntriesDTO",
                NewEntriesDTO.builder()
                        .profileId(Long.valueOf(request.getParameter("profileId")))
                        .recordId(Optional.ofNullable(request.getParameter("recordId"))
                                .isPresent() ? Long.parseLong(request.getParameter("recordId")) : null)
                        .recordDate(request.getParameter("recordDate"))
                        .currentDailyCaloriesNorm((Integer) request.getSession().getAttribute("user.dailyCaloriesNorm"))
                        .entries(new ArrayList<>()).build());
    }
}