package com.kucw.security.dao;

import com.kucw.security.model.Member;
import com.kucw.security.model.Role;

import java.util.List;

public interface MemberDao {

    // 基本 Member 操作
    Member getMemberByEmail(String email);

    Integer createMember(Member member);

    List<Role> getRolesByMemberId(Integer memberId);

    void addRoleForMemberId(Integer memberId, Role normalRole);

    void removeRoleFromMemberId(Integer memberId, Role role);
}
