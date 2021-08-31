package com.midorlo.wolkenbruch.rest.admin;

import com.midorlo.wolkenbruch.domain.security.Account;
import com.midorlo.wolkenbruch.service.account.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.midorlo.wolkenbruch.model.mapper.RestMapper.generatePaginationHttpHeaders;

@RestController
@RequestMapping("/api/v1/admin/accounts")
public class AccountAdministrationResource {

   private final AccountService service;

   public AccountAdministrationResource(AccountService service) {
      this.service = service;
   }

   /**
    * Default implementation for findAll.
    * NOT enabled by default. To enable it, do override the method and set any Mapping Annotation.
    *
    * @param pageable pageable scope.
    * @return Response Entity.
    */
   @GetMapping("")
   public ResponseEntity<List<Account>> findAll(Pageable pageable) {
      Page<Account> page    = service.findAll(pageable);
      HttpHeaders   headers = generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
      return ResponseEntity.ok().headers(headers).body(page.getContent());
   }

   @PostMapping("/{idAccount}/authorities/add")
   public ResponseEntity<Account> addDirectPrivilege(
      @PathVariable("idAccount") Long idAccount,
      @RequestBody Long id
   ) {
      return ResponseEntity.ok(service.addPrivilege(idAccount, id));
   }
}
