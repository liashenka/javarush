package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model {

    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        ArrayList<User> loadUsers = new ArrayList<>();
        loadUsers.add(new User("A", 1, 1));
        loadUsers.add(new User("B", 2, 1));
        modelData.setUsers(loadUsers);
    }


    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long id) { throw new UnsupportedOperationException(); }

    @Override
    public void changeUserData(String name, long id, int level) { throw new UnsupportedOperationException(); }
}
