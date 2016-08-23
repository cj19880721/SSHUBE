package com.sshube.spring.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_user")
public class User implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String gender;

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid", unique = true)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", length = 16)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", length = 16)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "gender", length = 1)
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User() {
        this("1234546", "123456a", "1", 2l);
    }

    public User(String username, String password, String gender, Long id) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.id = id;
    }
}
