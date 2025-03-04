package com.bridgelabz.SpringAddressBook.service;





import com.bridgelabz.SpringAddressBook.dto.AddressBookDTO;
import com.bridgelabz.SpringAddressBook.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AddressBookService {

    private final List<AddressBook> addressBookList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);  // Manual ID generation

    public AddressBook saveEntry(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook();
        addressBook.setId(idCounter.getAndIncrement());  // Assigning unique ID
        addressBook.setName(addressBookDTO.getName());
        addressBook.setPhoneNumber(addressBookDTO.getPhoneNumber());
        addressBook.setEmail(addressBookDTO.getEmail());
        addressBook.setAddress(addressBookDTO.getAddress());
        addressBookList.add(addressBook);
        return addressBook;
    }

    public List<AddressBook> getAllEntries() {
        return addressBookList;
    }

    public Optional<AddressBook> getEntryById(Long id) {
        return addressBookList.stream()
                .filter(entry -> entry.getId().equals(id))
                .findFirst();
    }

    public AddressBook updateEntry(Long id, AddressBookDTO newEntryDTO) {
        Optional<AddressBook> optionalEntry = getEntryById(id);
        if (optionalEntry.isPresent()) {
            AddressBook entry = optionalEntry.get();
            entry.setName(newEntryDTO.getName());
            entry.setPhoneNumber(newEntryDTO.getPhoneNumber());
            entry.setEmail(newEntryDTO.getEmail());
            entry.setAddress(newEntryDTO.getAddress());
            return entry;
        } else {
            throw new RuntimeException("Entry not found");
        }
    }

    public void deleteEntry(Long id) {
        addressBookList.removeIf(entry -> entry.getId().equals(id));
    }
/*
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

 */
}
