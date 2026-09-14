package com.feitui.admin.service;

import com.feitui.admin.entity.Contact;
import com.feitui.admin.mapper.AdminContactMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContactService {

    private final AdminContactMapper AdminContactMapper;

    public ContactService(AdminContactMapper AdminContactMapper) {
        this.AdminContactMapper = AdminContactMapper;
    }

    public PageInfo<Contact> page(int page, int size, String keyword, Integer status) {
        PageHelper.startPage(page, size);
        List<Contact> list = AdminContactMapper.page(keyword, status);
        return new PageInfo<>(list);
    }

    public Contact getById(Long id) {
        return AdminContactMapper.findById(id);
    }

    public boolean updateStatus(Long id, Integer status) {
        return AdminContactMapper.updateStatus(id, status) > 0;
    }

    public boolean updateRemark(Long id, String remark) {
        return AdminContactMapper.updateRemark(id, remark) > 0;
    }

    public boolean deleteById(Long id) {
        return AdminContactMapper.deleteById(id) > 0;
    }

    public int deleteBatch(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return 0;
        return AdminContactMapper.deleteBatch(ids);
    }

    // 最近N天的咨询量趋势
    public List<Contact> recent(int limit) {
        return AdminContactMapper.recentList(limit);
    }

    public LocalDate today() {
        return LocalDate.now();
    }
}