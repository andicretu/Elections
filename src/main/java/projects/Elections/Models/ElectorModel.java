package projects.Elections.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "electors")
@Inheritance(strategy = InheritanceType.JOINED)
public class ElectorModel {
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
    public ElectorModel() {}
    public ElectorModel(Long id, String email, String password, String name, String description) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
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
    public String getUsername() {
        return email;
    }
}
