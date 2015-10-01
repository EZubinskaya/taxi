package com.models;

/**
 * Created by kzub on 9/17/2015.
 */

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity(name = "user")
@AttributeOverrides({
        @AttributeOverride(name="username", column=@Column(name="username")),
        @AttributeOverride(name="password", column=@Column(name="password")),
        @AttributeOverride(name="role", column=@Column(name="role"))
})
public class User extends UserDetail implements Serializable {

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private int count;

    public User() {
    }

    public User(String phone, String name, int count) {
        this.phone = phone;
        this.name = name;
        this.count = count;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (count != user.count) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        return !(name != null ? !name.equals(user.name) : user.name != null);

    }

    @Override
    public int hashCode() {
        int result = phone != null ? phone.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }
}
