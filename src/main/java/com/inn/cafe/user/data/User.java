package com.inn.cafe.user.data;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.inn.cafe.user.data.enums.Role;
import com.inn.cafe.user.data.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_users")
public class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String firstName; 
  private String lastName;

  @Column(
    unique = true,
    updatable = true
  )
  private String email;

  @Column(
    unique = true,
    updatable = true
  )
  private String username;

  @Column(
    unique = true,
    updatable = true
  )
  private String contactNumber;

  @Column(updatable = true)
  private String password;
  
  @Enumerated(EnumType.STRING)
  private Role role;
  
  @Column(updatable = true)
  @Enumerated(EnumType.STRING)
  private Status status;

  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.toString()));
  }

  @Override
  public String getPassword() {
    return password;
  }
  @Override
  public String getUsername() {
    return (username != null) 
            ? username : (email != null) 
            ? email : contactNumber;
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
