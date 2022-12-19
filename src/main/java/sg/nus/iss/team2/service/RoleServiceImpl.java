package sg.nus.iss.team2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.team2.model.Role;
import sg.nus.iss.team2.repository.RoleRepository;

import javax.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repo;

    @Override
    public List<Role> findAllRoles() {
        // TODO Auto-generated method stub
        return repo.findAll();
    }

    @Override
    public Role findRoleById(Long roleId) {
        // TODO Auto-generated method stub
        return repo.findById(roleId).get();
    }

    @Override
    public Role createRole(Role role) {
        // TODO Auto-generated method stub
        return repo.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        // TODO Auto-generated method stub
        return repo.save(role);
    }

    @Override
    public void removeRole(Role role) {
        repo.delete(role);
    }

    @Override
    public List<String> findAllRolesNames() {
        List<String> names = new ArrayList<>();
        repo.findAll().stream().forEach(x -> names.add(x.getRoleName()));
        return names;
    }

    @Override
    public List<Role> findRoleByName(String name) {
        return repo.findRoleByName(name);
    }

    @Override
    @Transactional
    public Role findRoleByRoleName(String name) {
        return repo.findRoleByRoleName(name);
    }

}
