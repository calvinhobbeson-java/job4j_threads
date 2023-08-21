package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        accounts.putIfAbsent(account.id(), account);
        return true;
    }

    public synchronized boolean update(Account account) {
        accounts.replace(account.id(), account);
        return true;
    }

    public synchronized boolean delete(int id) {
        accounts.remove(id);
        return true;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> fromAccount = this.getById(fromId);
        Optional<Account> toAccount = this.getById(toId);
        if (fromAccount.isPresent() && toAccount.isPresent() && fromAccount.get().amount() >= amount) {
            accounts.replace(fromId, new Account(fromId, accounts.get(fromId).amount() - amount));
            accounts.replace(toId, new Account(toId, accounts.get(toId).amount() + amount));
        }
        return true;
    }
}