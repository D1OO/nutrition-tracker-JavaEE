package net.shvdy.nutrition_tracker.controller.listener;

import net.shvdy.nutrition_tracker.model.entity.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("user.role", Role.GUEST);
        httpSessionEvent.getSession().setAttribute("lang", "en");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        HashSet<Long> loggedUsers = (HashSet<Long>) servletContext.getAttribute("loggedUsers");
        loggedUsers.remove(httpSessionEvent.getSession().getAttribute("user.userId"));
        servletContext.setAttribute("loggedUsers", loggedUsers);
    }
}
