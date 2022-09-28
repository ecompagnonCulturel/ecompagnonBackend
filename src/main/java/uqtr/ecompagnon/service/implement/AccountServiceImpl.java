package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.Account;
import uqtr.ecompagnon.model.Profile;
import uqtr.ecompagnon.model.Users;
import uqtr.ecompagnon.repository.AccountRepository;
import uqtr.ecompagnon.service.AccountService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public <S extends Account> S save(S s) {

        return accountRepository.save(s);
    }


    public <S extends Account> Iterable<S> saveAll(Iterable<S> iterable) {
        return accountRepository.saveAll(iterable);
    }


    public Optional<Account> findById(Long Accountid) {
        return accountRepository.findById(Accountid);
    }


    public boolean existsById(Long Accountid) {
        return accountRepository.existsById(Accountid);
    }


    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }


    public Iterable<Account> findAllById(Iterable<Long> iterable) {
        return accountRepository.findAllById(iterable);
    }


    public long count() {
        return accountRepository.count();
    }

    public List<Account> getAllAccount() {
        return (List<Account>) accountRepository.findAll();
    }


    public void delAccounttById(Long id) {
        accountRepository.deleteById(id);
    }


    public Iterable<Account> getAccountByUserAndUser(Users user, Profile profile) {
        return accountRepository.findByAccUserAndAccProfile(user, profile);
    }



}
