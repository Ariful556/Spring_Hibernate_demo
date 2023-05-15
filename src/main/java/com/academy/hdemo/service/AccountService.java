package com.academy.hdemo.service;


import com.academy.hdemo.dao.AccountDAO;
import com.academy.hdemo.exception.AccountNotFoundException;
import com.academy.hdemo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    @Qualifier("AccountDAOImp2")
    AccountDAO accountDAO;

    public Account findById(long id) {
        return accountDAO.findByAccountId(id);
    }

    public void saveAccount(Account account) {
        accountDAO.save(account);
    }

    public void update(Account account) {
        accountDAO.update(account);
    }

    public void deleteAccount(Account account) {
        accountDAO.delete(account);

    }

    public List getAllAccounts() {
        return accountDAO.accountList();
    }

    public void transferAccount(Long fromAccountNumber, Long toAccountNumber, Long thisamount) {

        Long senderID = fromAccountNumber;
        Long receiverID = toAccountNumber;
        Long amount = thisamount;

        if (senderID < amount && receiverID <amount) {
            long currentSenderAmount = accountDAO.findByAccountId(senderID).getBalance();
            long newSenderAmount = currentSenderAmount - amount;
            accountDAO.findByAccountId(senderID).setBalance(newSenderAmount);
            accountDAO.update(accountDAO.findByAccountId(senderID));

            long currentReceiverAmount = accountDAO.findByAccountId(receiverID).getBalance();
            long newTakerAmount = currentReceiverAmount + amount;
            accountDAO.findByAccountId(receiverID).setBalance(newTakerAmount);
            accountDAO.update(accountDAO.findByAccountId(receiverID));

        } else {
            throw new AccountNotFoundException("No blance");
        }

    }
}