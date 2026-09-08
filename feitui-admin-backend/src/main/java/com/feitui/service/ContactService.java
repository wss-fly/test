package com.feitui.service;

import com.feitui.entity.Contact;
import java.util.List;

public interface ContactService {
    Contact saveContact(Contact contact);
    Contact getById(Long id);
    List<Contact> listAll(Integer status);
    boolean updateStatus(Long id, Integer status);
    boolean deleteById(Long id);
}
