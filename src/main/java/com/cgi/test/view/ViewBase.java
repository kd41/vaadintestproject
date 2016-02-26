package com.cgi.test.view;

import com.cgi.test.service.UserService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

public abstract class ViewBase extends VerticalLayout implements View {

    private UserService userService;

    public abstract String getName();

    public ViewBase(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
