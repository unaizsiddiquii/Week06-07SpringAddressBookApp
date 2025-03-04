package com.bridgelabz.SpringAddressBook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
}
