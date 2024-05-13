package com.workintech.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Validated
@Entity
@Data
@NoArgsConstructor
@Table(name = "user", schema = "ecommerce")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name must not be null, empty or blank")
    @Size(min = 3,max = 35,message = "Name should be between 3 and 35 characters.")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Email must not be null, empty or blank")
    @Size(min = 10,max = 60,message = "Email should be between 10 and 60 characters.")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Password must not be null, empty or blank")
    @Size(min = 8,max = 100,message = "Password should be between 8 and 100 characters.")
    @Column(name = "password")
    private String password;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Address> addresses;

    public void addAddress(Address address){
        if(addresses == null){
            addresses = new ArrayList<>();
        }
        addresses.add(address);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
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
