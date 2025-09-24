//package com.ecomm.user;
//
//import com.ecomm.entity.Permission;
//import com.ecomm.entity.Role;
//import com.ecomm.repository.PermissionRepository;
//import com.ecomm.repository.RoleRepository;
//import com.ecomm.userservice.UserServiceApplication;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//
//@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//@DataJpaTest
//@ActiveProfiles("test")
//@Import(RBACBootstrap.class) // only bring the seeder into the JPA slice
//class RBACBootstrapTest {
//
//    private final RoleRepository roleRepo;
//    private final PermissionRepository permissionRepo;
//    private final RBACBootstrap bootstrap;
//
//    @MockBean
//    AuthenticationConfiguration authCfg;
//
//    @MockBean
//    AuthenticationManager authenticationManager;
//
//    @BeforeEach
//    void stub() throws Exception {
//        when(authCfg.getAuthenticationManager()).thenReturn(authenticationManager);
//    }
//    RBACBootstrapTest(RoleRepository roleRepo,
//                      PermissionRepository permissionRepo,
//                      RBACBootstrap bootstrap) {
//        this.roleRepo = roleRepo;
//        this.permissionRepo = permissionRepo;
//        this.bootstrap = bootstrap;
//    }
//
//    @Test
//    void seedsRolesAndPermissions_idempotent() {
//        bootstrap.seedRbac(); // 1st run
//
//        Role admin = roleRepo.findByNameWithPermissions("ROLE_ADMIN").orElseThrow();
//        Role user  = roleRepo.findByNameWithPermissions("ROLE_USER").orElseThrow();
//
//        assertThat(names(admin)).containsExactlyInAnyOrder("USER_READ","USER_WRITE","ROLE_READ","ROLE_WRITE");
//        assertThat(names(user)).containsExactlyInAnyOrder("USER_READ");
//
//        bootstrap.seedRbac(); // 2nd run: no duplicates
//
//        admin = roleRepo.findByNameWithPermissions("ROLE_ADMIN").orElseThrow();
//        user  = roleRepo.findByNameWithPermissions("ROLE_USER").orElseThrow();
//
//        assertThat(names(admin)).containsExactlyInAnyOrder("USER_READ","USER_WRITE","ROLE_READ","ROLE_WRITE");
//        assertThat(names(user)).containsExactlyInAnyOrder("USER_READ");
//    }
//
//    private static Set<String> names(Role r) {
//        return r.getPermissions().stream().map(Permission::getName).collect(Collectors.toSet());
//    }
//}
