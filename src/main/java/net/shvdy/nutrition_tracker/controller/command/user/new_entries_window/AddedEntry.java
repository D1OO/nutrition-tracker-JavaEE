package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.dto.DailyRecordEntryDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

public class AddedEntry implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().getServletContext().setAttribute("newEntriesDTO",
                NewEntriesDTOReader.readAddNew(request, DailyRecordEntryDTO.builder()
                        .foodName(request.getParameter("foodName"))
                        .foodDTOJSON(request.getParameter("foodDTOJSON")).build()));

        return "/view/user/add-new-entries-window/new-entries-list.jsp";
    }
}
