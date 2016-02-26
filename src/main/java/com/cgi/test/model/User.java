package com.cgi.test.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER", schema = "public")
public class User implements Serializable {

    private static final long serialVersionUID = -8873693798537022539L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;
    //private Set<Address> addresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //    public Set<Address> getAddresses() {
    //        return addresses;
    //    }
    //
    //    public void setAddresses(Set<Address> addresses) {
    //        this.addresses = addresses;
    //    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("firstName", firstName)
            .append("lastName", lastName)
            .toString();
    }
}
