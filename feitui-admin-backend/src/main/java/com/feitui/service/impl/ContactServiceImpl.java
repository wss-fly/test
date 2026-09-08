package com.feitui.service.impl;

import com.feitui.entity.Contact;
import com.feitui.mapper.ContactMapper;
import com.feitui.service.ContactService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    @Override
    public Contact saveContact(Contact contact) {
        if (contact.getStatus() == null) {
            contact.setStatus(0);
        }
        contactMapper.insert(contact);
        return contact;
    }

    @Override
    public Contact getById(Long id) {
        return contactMapper.selectById(id);
    }

    @Override
    public List<Contact> listAll(Integer status) {
        return contactMapper.selectList(status);
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setStatus(status);
        return contactMapper.updateById(contact) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return contactMapper.deleteById(id) > 0;
    }
}
