package com.bridgelabz.SpringAddressBook.service;





import com.bridgelabz.SpringAddressBook.dto.AddressBookDTO;
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

    public AddressBook saveEntry(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook();
        addressBook.setName(addressBookDTO.getName());
        addressBook.setPhoneNumber(addressBookDTO.getPhoneNumber());
        addressBook.setEmail(addressBookDTO.getEmail());
        addressBook.setAddress(addressBookDTO.getAddress());
        return repository.save(addressBook);
    }

    public List<AddressBook> getAllEntries() {
        return repository.findAll();
    }

    public Optional<AddressBook> getEntryById(Long id) {
        return repository.findById(id);
    }


    public AddressBook updateEntry(Long id, AddressBookDTO newEntryDTO) {
        return repository.findById(id)
                .map(entry -> {
                    entry.setName(newEntryDTO.getName());
                    entry.setPhoneNumber(newEntryDTO.getPhoneNumber());
                    entry.setEmail(newEntryDTO.getEmail());
                    entry.setAddress(newEntryDTO.getAddress());
                    return repository.save(entry);
                })
                .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    public void deleteEntry(Long id) {
        repository.deleteById(id);
    }
}
