package org.example;

import java.util.Objects;

public class Kisiler {
    private int id;
    private String name;
    private String surname;
    private String city;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kisiler kisiler = (Kisiler) o;
        return id == kisiler.id &&
                Objects.equals(name, kisiler.name) &&
                Objects.equals(surname, kisiler.surname) &&
                Objects.equals(city, kisiler.city) &&
                Objects.equals(phone, kisiler.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, city, phone);
    }
}
