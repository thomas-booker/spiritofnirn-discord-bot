package uk.co.thomasbooker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class TrialMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String role;

    private Long trailId;

    public TrialMember() {
    }

    public TrialMember(Long id, String name, String role, Long trailId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.trailId = trailId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Long getTrailId() {
        return trailId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTrailId(Long trailId) {
        this.trailId = trailId;
    }

    public TrialMember withId(Long id) {
        this.id = id;
        return this;
    }

    public TrialMember withName(String name) {
        this.name = name;
        return this;
    }

    public TrialMember withRole(String role) {
        this.role = role;
        return this;
    }

    public TrialMember withTrailId(Long trailId) {
        this.trailId = trailId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrialMember)) return false;
        TrialMember that = (TrialMember) o;
        return getId().equals(that.getId()) && getName().equals(that.getName()) && getRole().equals(that.getRole()) && getTrailId().equals(that.getTrailId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRole(), getTrailId());
    }

    @Override
    public String toString() {
        return "TrialMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", trailId=" + trailId +
                '}';
    }

}
