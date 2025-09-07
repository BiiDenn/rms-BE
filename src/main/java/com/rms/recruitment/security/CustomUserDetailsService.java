package com.rms.recruitment.security;

import com.rms.recruitment.enums.PermissionType;
import com.rms.recruitment.enums.RoleType;
import com.rms.recruitment.models.Accounts;
import com.rms.recruitment.models.Roles;
import com.rms.recruitment.repositories.AccountRepository;
import com.rms.recruitment.repositories.RoleRepository;
import com.rms.recruitment.services.RolePermissionService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final RolePermissionService rolePermissionService;

    public CustomUserDetailsService(AccountRepository accountRepository,
            RoleRepository roleRepository,
            RolePermissionService rolePermissionService) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Accounts account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Load user roles
        Optional<Roles> userRole = roleRepository.findByAccountId(account.getAccountId());
        Collection<GrantedAuthority> authorities = getAuthorities(userRole);

        return new User(
                account.getEmail(),
                account.getPassword(),
                account.getStatus() == 1,
                true,
                true,
                true,
                authorities);
    }

    private Collection<GrantedAuthority> getAuthorities(Optional<Roles> userRole) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        if (userRole.isPresent()) {
            Roles role = userRole.get();
            // Add role authority
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleType().name()));

            // Add permission authorities
            Collection<PermissionType> permissions = rolePermissionService.getPermissionsForRole(role.getRoleType());
            for (PermissionType permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission.getCode()));
            }
        } else {
            // If no role assigned, add default USER role
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorities;
    }
}
