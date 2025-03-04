package com.bridgelabz.SpringAddressBook.service;





import com.bridgelabz.SpringAddressBook.model.AddressBook;
import com.bridgelabz.SpringAddressBook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public AddressBook saveEntry(AddressBook addressBook) {
        return repository.save(addressBook);
    }

    public List<AddressBook> getAllEntries() {
        return repository.findAll();
    }

    public Optional<AddressBook> getEntryById(Long id) {
        return repository.findById(id);
    }

    public AddressBook updateEntry(Long id, AddressBook newEntry) {
        return repository.findById(id)
                .map(entry -> {
                    entry.setName(newEntry.getName());
                    entry.setPhoneNumber(newEntry.getPhoneNumber());
                    entry.setEmail(newEntry.getEmail());
                    entry.setAddress(newEntry.getAddress());
                    return repository.save(entry);
                })
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    public void deleteEntry(Long id) {
        repository.deleteById(id);
    }
}
