package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.dto.FoodDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SaveNewFood implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> errors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.FOOD_FORM_VALIDATION_DATA.getFormFieldsValidationData());

        if (errors.isEmpty()) {
            saveFoodAndUpdateCache(request);
            return "ok";
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                return "json:" + ContextHolder.objectMapper().writeValueAsString(errors);
            } catch (JsonProcessingException e) {
                ContextHolder.logger().error("SaveNewFood execute: objectMapper().writeValueAsString exception: " + e);
                return "json:";
            }
        }
    }

    private void saveFoodAndUpdateCache(HttpServletRequest request) {
        FoodDTO foodDTO = FoodDTO.builder().name(request.getParameter("newFoodName"))
                .calories(Integer.parseInt(request.getParameter("newFoodCalories")))
                .carbohydrates(Integer.parseInt(request.getParameter("newFoodCarbohydrates")))
                .fats(Integer.parseInt(request.getParameter("newFoodFats")))
                .proteins(Integer.parseInt(request.getParameter("newFoodProt")))
                .build();

        foodDTO.setFoodId(ContextHolder.foodService().saveForProfile(foodDTO,
                Long.parseLong(request.getParameter("profileId"))));

        List<FoodDTO> updatedFoodCache = (List<FoodDTO>) request.getSession().getAttribute("user.userFood");
        updatedFoodCache.add(foodDTO);
        request.getSession().setAttribute("user.userFood", updatedFoodCache);
    }
}
