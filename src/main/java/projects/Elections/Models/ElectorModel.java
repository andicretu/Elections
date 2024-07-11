package projects.Elections.Models;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "electors")
@Inheritance(strategy = InheritanceType.JOINED)
public class ElectorModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column (name = "NAME", nullable = false)
    private String name;
    @Column (name = "DESCRIPTION", nullable = false)
    private String description;
    @Column (name = "CANDIDATESTATUS", nullable = true)
    private Boolean candidateStatus;
    public ElectorModel() {}
    public ElectorModel(Long id, String email, String password, String name, String description, Boolean candidateStatus) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
        this.candidateStatus = candidateStatus;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = this.id;}
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getCandidateStatus() {
        return candidateStatus;
    }
    public void setCandidateStatus(Boolean candidateStatus) {
        this.candidateStatus = candidateStatus;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(candidateStatus ? "ROLE_CANDIDATE" : "ROLE_ELECTOR"));
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
