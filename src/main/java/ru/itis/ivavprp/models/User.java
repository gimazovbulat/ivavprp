package ru.itis.ivavprp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.ivavprp.dto.UserDto;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(schema = "ivavprp", name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private Boolean isActive;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), schema = "ivavprp")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
    @Transient
    private Token currentToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive();
    }

    private boolean isActive() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive();
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public static User fromUserDto(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .isActive(userDto.getIsActive()).roles(userDto.getRoles())
                .build();
    }

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }

    public Token getCurrentToken() {
        return currentToken;
    }
}
