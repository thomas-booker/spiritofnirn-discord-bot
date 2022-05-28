package uk.co.thomasbooker.spritofnirn.trial.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Trial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime startDateTime;

    private String owner;

    public Long getId() {
        return id;
    }

    public Trial() {
    }

    public Trial(Long id, String name, LocalDateTime startDateTime, String owner) {
        this.id = id;
        this.name = name;
        this.startDateTime = startDateTime;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Trial withId(Long id) {
        this.id = id;
        return this;
    }

    public Trial withName(String name) {
        this.name = name;
        return this;
    }

    public Trial withStartDateTime(LocalDateTime startTime) {
        this.startDateTime = startTime;
        return this;
    }

    public Trial withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trial)) return false;
        Trial trial = (Trial) o;
        return getId().equals(trial.getId()) && getName().equals(trial.getName()) && getStartDateTime().equals(trial.getStartDateTime()) && getOwner().equals(trial.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStartDateTime(), getOwner());
    }

    @Override
    public String toString() {
        return "Trial{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startDateTime=" + startDateTime +
                ", owner='" + owner + '\'' +
                '}';
    }

}
