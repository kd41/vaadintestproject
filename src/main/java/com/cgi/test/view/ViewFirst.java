package com.cgi.test.view;

import com.cgi.test.model.User;
import com.cgi.test.service.UserService;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import java.util.List;

public class ViewFirst extends ViewBase {

    private Table table;
    private FieldGroup fieldGroup = new FieldGroup();

    public ViewFirst(UserService userService) {
        super(userService);

        setSpacing(true);
        setMargin(true);

        addComponent(buildTableControls());
        addComponent(buildTable());
        addComponent(buildForm());
        addComponent(buildFormControls());
    }

    @Override
    public String getName() {
        return "First page";
    }

    private Component buildTableControls() {
        HorizontalLayout tableControls = new HorizontalLayout();
        Button add = new Button("Add new user", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                editPerson(getNewUser());
            }
        });
        Button delete = new Button("Delete selected user", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                getUserService().delete((User) table.getValue());
                updateTableData();
            }
        });
        tableControls.addComponent(add);
        tableControls.addComponent(delete);
        return tableControls;
    }

    private Component buildTable() {
        table = new Table(null);
        table.setWidth("500px");
        table.setSelectable(true);
        table.setImmediate(true);
        updateTableData();
        table.addValueChangeListener(new Property.ValueChangeListener() {
            @Override public void valueChange(Property.ValueChangeEvent event) {
                editPerson((User) table.getValue());
            }
        });
        return table;
    }

    private Component buildForm() {
        GridLayout form = new GridLayout(2, 3);

        TextField firstName = new TextField("First name:");
        TextField lastName = new TextField("Last name:");

        fieldGroup.bind(firstName, "firstName");
        fieldGroup.bind(lastName, "lastName");

        form.addComponent(firstName);
        form.addComponent(lastName);
        return form;
    }

    private Component buildFormControls() {
        HorizontalLayout formControls = new HorizontalLayout();
        Button save = new Button("Save", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit();
                    getUserService().save(((BeanItem<User>) fieldGroup.getItemDataSource()).getBean());
                    updateTableData();
                    editPerson(null);
                } catch (FieldGroup.CommitException e) {
                    e.printStackTrace();
                }
            }
        });
        Button delete = new Button("Discard", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                fieldGroup.discard();
            }
        });
        formControls.addComponent(save);
        formControls.addComponent(delete);
        return formControls;
    }

    private void editPerson(User user) {
        if (user == null) {
            user = getNewUser();
        }
        BeanItem<User> item = new BeanItem<User>(user);
        fieldGroup.setItemDataSource(item);
    }

    private void updateTableData() {
        List<User> persons = getUserService().getAll();
        BeanItemContainer<User> container = new BeanItemContainer<User>(User.class, persons);
        table.setContainerDataSource(container);
        table.setVisibleColumns(new String[] { "id", "firstName", "lastName" });
        table.setColumnHeaders( "Id", "First name", "Last name" );
        table.sort(new Object[] { "lastName", "firstName" }, new boolean[] { true, true });
        table.setPageLength(table.getItemIds().size());
    }

    private User getNewUser() {
        User user = new User();
        user.setFirstName("Firstname");
        user.setLastName("Lastname");
        return user;
    }
}
