package com.hp.accounts.Services;

import com.hp.accounts.Constants.AccountsConstant;
import com.hp.accounts.DTO.CustomerDto;
import com.hp.accounts.Exception.CustomerAlerdyExsistException;
import com.hp.accounts.Mapper.CustomerMapper;
import com.hp.accounts.models.Accounts;
import com.hp.accounts.models.Customer;
import com.hp.accounts.repository.AccountsRepository;
import com.hp.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public  class AccountServiceImpl implements IAccountService{

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer= customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlerdyExsistException("customer already exist"+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("ANONYMOUS");
        Customer savedCustomer=customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000L + new Random().nextInt(90000000);
        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountsConstant.SAVINGS);
        accounts.setBranchAddress(AccountsConstant.ADDRESS);
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setCreatedBy("ANONYMOUS");


        return accountsRepository.save(accounts);
    }
}
