package uk.co.thomasbooker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Trial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDateTime startTime;

    private String owner;

    public Long getId() {
        return id;
    }

    public Trial() {
    }

    public Trial(Long id, String name, LocalDateTime startTime, String owner) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
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

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
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

    public Trial withStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
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
        return getId().equals(trial.getId()) && getName().equals(trial.getName()) && getStartTime().equals(trial.getStartTime()) && getOwner().equals(trial.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStartTime(), getOwner());
    }

    @Override
    public String toString() {
        return "Trial{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", owner='" + owner + '\'' +
                '}';
    }

}
