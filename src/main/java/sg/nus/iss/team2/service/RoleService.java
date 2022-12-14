package sg.nus.iss.team2.service;

import java.util.List;

import sg.nus.iss.team2.model.Role;

public interface RoleService {
    List<Role> findAllRoles();

    Role findRoleById(Long roleId);

    Role createRole(Role role);

    Role updateRole(Role role);

    void removeRole(Role role);

    List<String> findAllRolesNames();

    List<Role> findRoleByName(String name);

}
