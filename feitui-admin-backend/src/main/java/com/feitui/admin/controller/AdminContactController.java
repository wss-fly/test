package com.feitui.admin.controller;

import com.feitui.admin.common.R;
import com.feitui.admin.entity.Contact;
import com.feitui.admin.service.ContactService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/contact")
public class AdminContactController {

    private final ContactService contactService;

    public AdminContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/page")
    public R<PageInfo<Contact>> page(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) String keyword,
                                     @RequestParam(required = false) Integer status) {
        return R.ok(contactService.page(page, Math.min(size, 50), keyword, status));
    }

    @GetMapping("/{id}")
    public R<Contact> get(@PathVariable Long id) {
        Contact c = contactService.getById(id);
        return c == null ? R.fail("记录不存在") : R.ok(c);
    }

    @PutMapping("/{id}/status")
    public R<Void> status(@PathVariable Long id, @RequestParam Integer status) {
        return contactService.updateStatus(id, status) ? R.ok() : R.fail("更新失败");
    }

    @PutMapping("/{id}/remark")
    public R<Void> remark(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return contactService.updateRemark(id, body.get("remark")) ? R.ok() : R.fail("更新失败");
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        return contactService.deleteById(id) ? R.ok() : R.fail("删除失败");
    }

    @PostMapping("/batch")
    public R<Void> deleteBatch(@RequestBody List<Long> ids) {
        contactService.deleteBatch(ids);
        return R.ok();
    }
}