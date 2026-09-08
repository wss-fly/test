package com.feitui.admin.mapper;

import com.feitui.admin.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper {
    AdminUser findByUsername(@Param("username") String username);
    void updateLastLogin(@Param("id") Long id);

    List<AdminUser> page(@Param("keyword") String keyword);
    AdminUser findById(@Param("id") Long id);
    int countByUsername(@Param("username") String username);
    List<Integer> selectIds();
    int insert(AdminUser user);
    int updateInfo(@Param("id") Long id, @Param("nickname") String nickname,
                   @Param("role") String role, @Param("status") Integer status);
    int updatePassword(@Param("id") Long id, @Param("password") String password);
    int deleteById(@Param("id") Long id);
    // 删除后紧凑重排：把 id 大于被删 id 的所有行往前挪一位
    int shiftIdDown(@Param("id") Long id);
}