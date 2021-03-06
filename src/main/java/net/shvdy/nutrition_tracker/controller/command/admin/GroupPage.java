package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 08.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class GroupPage implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getSession().setAttribute("group", ContextHolder.userService()
                .findGroup((String) request.getSession().getAttribute("user.username")));

        Response.FORWARD.execute().response("/view/fragments/admin/group.jsp", request, response);
    }
}
