package com.aplikacja.lotywkosmos.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@ColumnName(name="id", updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "You have to provide first name")
    private String name;

    @NotBlank(message = "You have to provide last name")
    private String lastName;

    private String sex;         //M or F
    private String country;
    private String notes;
    private LocalDate birthDate;

    @OneToMany
    @ElementCollection
    private List<Flight> flights = new ArrayList<>();


    public Tourist(){}

    public Tourist(String name, String lastName, String sex, String country, LocalDate birthDate){
        this.name=name;
        this.lastName = lastName;
        this.sex = sex;
        this.country = country;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        String myStr = "id : " + this.getId() +
                        "\nname: " + this.getName() +
                        "\nlast name: " + this.getLastName() +
                        "\nsex: " + this.getSex() +
                        "\ncountry: " + this.getCountry() +
                        "\nnotes: " + this.getNotes() +
                        "\nbirth date: " + this.getBirthDate();
        return myStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public List<Flight> getFlights(){
        return flights;
    }

    public void addFlight(Flight flight){
        flights.add(flight);
    }

}
