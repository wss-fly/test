package com.feitui.admin.mapper;

import com.feitui.admin.entity.Contact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminContactMapper {
    List<Contact> page(@Param("keyword") String keyword, @Param("status") Integer status);
    Contact findById(@Param("id") Long id);
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    int updateRemark(@Param("id") Long id, @Param("remark") String remark);
    int deleteById(@Param("id") Long id);
    Long totalCount();
    Long todayCount(@Param("today") String today);
    List<Contact> recentList(@Param("limit") int limit);
    List<java.util.Map<String, Object>> countByStatus();
}