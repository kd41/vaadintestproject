package com.cgi.test.view;

/**
 * Created by alekseim on 19.02.2016.
 */

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class Navigation extends UI {

    @Autowired
    protected ViewFirst viewFirst;

    @Autowired
    protected ViewSecond viewSecond;

    private MenuBar menuBar = new MenuBar();
    private Panel panel = new Panel();
    private final Navigator navigator = new Navigator(this, panel);

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(menuBar);
        layout.addComponent(panel);
        setContent(layout);
        navigator.addView("", viewFirst);
        addVeiw(viewFirst);
        addVeiw(viewSecond);
        layout.addComponent(new Label("Created by Aleksei M."));
    }

    private void addVeiw(final ViewBase view) {
        navigator.addView(view.getName(), view);
        menuBar.addItem(view.getName(), new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                navigator.navigateTo(view.getName());
            }
        });
    }
}
