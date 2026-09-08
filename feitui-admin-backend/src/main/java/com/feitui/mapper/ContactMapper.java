package com.feitui.mapper;

import com.feitui.entity.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ContactMapper {
    int insert(Contact contact);
    Contact selectById(@Param("id") Long id);
    List<Contact> selectList(@Param("status") Integer status);
    int updateById(Contact contact);
    int deleteById(@Param("id") Long id);
}
