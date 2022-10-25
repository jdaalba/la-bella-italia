package com.jdaalba.entity;

import java.util.Collection;
import java.util.Set;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Document
public class User implements UserDetails {

  @MongoId
  private ObjectId id;

  private String username;

  private String password;

  private Set<String> userRoles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return userRoles.stream()
        .map(r -> "ROLE_" + r)
        .map(SimpleGrantedAuthority::new)
        .toList();
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
