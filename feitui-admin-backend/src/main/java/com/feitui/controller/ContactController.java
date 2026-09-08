package com.feitui.controller;

import com.feitui.common.R;
import com.feitui.entity.Contact;
import com.feitui.service.ContactService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/submit")
    public R<Contact> submitContact(@RequestBody Contact contact) {
        Contact saved = contactService.saveContact(contact);
        return R.ok("提交成功，我们会尽快联系您", saved);
    }

    @GetMapping("/list")
    public R<List<Contact>> listContacts(@RequestParam(required = false) Integer status) {
        List<Contact> list = contactService.listAll(status);
        return R.ok(list);
    }

    @GetMapping("/{id}")
    public R<Contact> getById(@PathVariable Long id) {
        Contact contact = contactService.getById(id);
        if (contact == null) {
            return R.fail("记录不存在");
        }
        return R.ok(contact);
    }

    @PutMapping("/{id}/status")
    public R<Void> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        boolean success = contactService.updateStatus(id, status);
        return success ? R.ok() : R.fail("更新失败");
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        boolean success = contactService.deleteById(id);
        return success ? R.ok() : R.fail("删除失败");
    }
}
