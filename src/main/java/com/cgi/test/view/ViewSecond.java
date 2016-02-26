package com.cgi.test.view;

import com.cgi.test.service.UserService;
import com.vaadin.ui.Label;

public class ViewSecond extends ViewBase {

    public ViewSecond(UserService userService) {
        super(userService);
        addComponent(new Label("here comes the second page"));
    }

    @Override
    public String getName() {
        return "Second page";
    }

}
