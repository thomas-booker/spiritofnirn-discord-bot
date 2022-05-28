package uk.co.thomasbooker.spritofnirn.trial.model;

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

    private Long trialId;

    public TrialMember() {
    }

    public TrialMember(Long id, String name, String role, Long trialId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.trialId = trialId;
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

    public Long getTrialId() {
        return trialId;
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

    public void setTrialId(Long trialId) {
        this.trialId = trialId;
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
        this.trialId = trailId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrialMember)) return false;
        TrialMember that = (TrialMember) o;
        return getId().equals(that.getId()) && getName().equals(that.getName()) && getRole().equals(that.getRole()) && getTrialId().equals(that.getTrialId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRole(), getTrialId());
    }

    @Override
    public String toString() {
        return "TrialMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", trialId=" + trialId +
                '}';
    }

}
