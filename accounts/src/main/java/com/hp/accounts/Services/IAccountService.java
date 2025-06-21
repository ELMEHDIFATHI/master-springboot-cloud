package com.hp.accounts.Services;

import com.hp.accounts.Dto.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean UpdateAccount(CustomerDto customerDto);

    boolean DeleteAccount(String mobileNumber);
}
