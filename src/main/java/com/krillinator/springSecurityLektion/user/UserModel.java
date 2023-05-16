package com.krillinator.springSecurityLektion.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.krillinator.springSecurityLektion.rats.Rat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 50,  message = "Username too short or too long")
    private String username;
    /* en array av råttor? */

    @NotEmpty
    @Size(min = 6, max = 200, message = "Password must be at least 6 characters long")
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<SimpleGrantedAuthority> authorities;  
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rat> rats;


    public UserModel(String username, String password, Set<SimpleGrantedAuthority> authorities , boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.rats = new ArrayList<>();
    }

   

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

     // Metod för att lägga till en råtta
    public void addRat(Rat rat) {
        this.rats.add(rat);
        rat.setOwner(this);
    }


  
}
