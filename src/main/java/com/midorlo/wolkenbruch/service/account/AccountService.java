package com.midorlo.wolkenbruch.service.account;

import com.midorlo.wolkenbruch.configuration.ApplicationProperties;
import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.domain.security.Privilege;
import com.midorlo.wolkenbruch.domain.security.Role;
import com.midorlo.wolkenbruch.model.mapper.AccountMapper;
import com.midorlo.wolkenbruch.model.security.AccountLoginDto;
import com.midorlo.wolkenbruch.repository.security.AccountRepository;
import com.midorlo.wolkenbruch.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Collection of administrative actions on {@link Account} records.
 */
@SuppressWarnings("unused")
@Service
@Slf4j
public class AccountService extends ApplicationService<Account, AccountRepository> {

    private final ApplicationProperties applicationProperties;
    private final PasswordEncoder       passwordEncoder;
    private final PrivilegeService      privilegeService;

    public AccountService(
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder,
            ApplicationProperties applicationProperties,
            PrivilegeService privilegeService
    ) {
        super(accountRepository);
        this.passwordEncoder       = passwordEncoder;
        this.applicationProperties = applicationProperties;
        this.privilegeService      = privilegeService;
    }

    public Account create(@NonNull AccountLoginDto dto) {
        return create(new AccountMapper(passwordEncoder, applicationProperties).toAccount(dto));
    }

    public Account createIfNotFound(@NonNull AccountLoginDto dto) {
        return repository.findByName(dto.getAccountname())
                         .orElseGet(() -> create(dto));
    }

    @Transactional
    public void delete(Account account) {
        log.info("Deleting account {}", account);
        repository.delete(account);
    }

    public void delete(String login) {
        Optional<Account> accountQuery = repository.findOneByLogin(login);
        if (accountQuery.isEmpty()) {
            throw new EntityNotFoundException("No account found for email " + login);
        } else {
            delete(accountQuery.get());
        }
    }

    @Transactional
    public Account addRoles(Account account, List<Role> roles) {
        roles.forEach(account::addRole);
        return repository.save(account);
    }

    @Transactional
    public Account removeAllRoles(Account account) {
        log.info("Removing all roles from account {}", account);
        account.getRoles().clear();
        return repository.save(account);
    }

    @Transactional
    public Account addRole(Account account, Role role) {
        log.info("Adding {} to {}", role, account);
        return repository.save(account.addRole(role));
    }

    public Account addPrivilege(Long idAccount, Long idPrivilege) {
        Account accountById = repository.findById(idAccount).orElseThrow(EntityNotFoundException::new);
        return addPrivilege(accountById, privilegeService.findById(idPrivilege));
    }

    @Transactional
    public Account addPrivilege(Account account, Privilege privilege) {
        log.info("Adding privilege {} to account {}", privilege, account);
        return repository.save(account.addDirectPrivilege(privilege));
    }

    @Transactional
    public Account addPrivileges(Account account, List<Privilege> privilegeList) {
        privilegeList
                .stream()
                .filter(p -> !account.hasPrivilege(p))
                .forEach(account::addDirectPrivilege);
        return repository.save(account);
    }

    public Optional<Account> findByName(String name) {
        return repository.findByName(name);
    }
}
