package net.shvdy.food_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

import net.shvdy.food_tracker.controller.command.ActionCommand;

public class FoodDiaryCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return "food-diary";
    }
}