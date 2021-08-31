package com.midorlo.wolkenbruch.components;

import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.domain.security.Privilege;
import com.midorlo.wolkenbruch.domain.security.Role;
import com.midorlo.wolkenbruch.model.security.AccountLoginDto;
import com.midorlo.wolkenbruch.model.security.DefaultPrivileges;
import com.midorlo.wolkenbruch.model.security.DefaultRoles;
import com.midorlo.wolkenbruch.service.account.AccountService;
import com.midorlo.wolkenbruch.service.account.PrivilegeService;
import com.midorlo.wolkenbruch.service.account.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean accountsInstalled = false;

    private final AccountService   accountService;
    private final PrivilegeService privilegeService;
    private final RoleService      roleService;

    public SetupDataLoader(
            PrivilegeService privilegeService,
            AccountService accountService, RoleService roleService
    ) {
        this.accountService   = accountService;
        this.roleService      = roleService;
        this.privilegeService = privilegeService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {

        if (accountsInstalled) {
            return;
        }

        // Create the System Account first because it's the default auditor.

        Account systemAccount = accountService.createIfNotFound(new AccountLoginDto("System", "", "system@localhost",
                true));
        Account adminAccount  = accountService.createIfNotFound(new AccountLoginDto("admin", "admin", "admin" +
                "@localhost", true));
        Account demoAccount   = accountService.createIfNotFound(new AccountLoginDto("Demo", "demo", "demo@localhost",
                true));

        // Create the privileges
        Privilege systemPrivilege =
                privilegeService.createIfNotFound(new Privilege(DefaultPrivileges.System.toString()));
        Privilege adminPrivilege =
                privilegeService.createIfNotFound(new Privilege(DefaultPrivileges.Admin.toString()));
        Privilege accountPrivilege =
                privilegeService.createIfNotFound(new Privilege(DefaultPrivileges.Account.toString()));

        // Create the roles
        Role systemRole  = roleService.createIfNotFound(new Role(DefaultRoles.System.toString()));
        Role adminRole   = roleService.createIfNotFound(new Role(DefaultRoles.Admin.toString()));
        Role accountRole = roleService.createIfNotFound(new Role(DefaultRoles.Account.toString()));

        roleService.addAllPrivileges(systemRole, systemPrivilege, adminPrivilege, accountPrivilege);
        roleService.addAllPrivileges(adminRole, adminPrivilege, accountPrivilege);
        roleService.addAllPrivileges(accountRole, accountPrivilege);

        // Link roles to accounts
        systemAccount = accountService.addRole(accountService.removeAllRoles(systemAccount), systemRole);
        adminAccount  = accountService.addRole(accountService.removeAllRoles(adminAccount), adminRole);
        demoAccount   = accountService.addRole(accountService.removeAllRoles(demoAccount), accountRole);

        log.info("Finished installing system accounts {}", List.of(systemAccount, adminAccount, demoAccount));
        accountsInstalled = true;
    }
}
