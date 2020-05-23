package net.shvdy.nutrition_tracker.controller.command.user.add_entries_window;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class AddEntriesModalWindow implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String recordId = request.getParameter("recordId");

		request.getSession().getServletContext().setAttribute("newEntriesDTO",
				NewEntriesDTO.builder()
						.profileId(Long.valueOf(request.getParameter("profileId")))
						.recordId(recordId.isEmpty() ? null : Long.parseLong(recordId))
						.recordDate(request.getParameter("recordDate"))
						.entries(new ArrayList<>()).build());

		return "/view/user/add-new-entries-window/window.jsp";
	}
}