package sample.BLL;

import sample.BE.Admin;
import sample.DAL.AdminDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminManager {
    private final AdminDAO adminDAO;
    private List<Admin> allAdmins = new ArrayList<>();
    public AdminManager() throws IOException {
        adminDAO = new AdminDAO();
    }

    public List<Admin> getAllAdmins() throws Exception {
        allAdmins = adminDAO.getAllAdmins();
        return allAdmins;
    }

    public void updateAdmin(Admin admin) throws Exception {
        adminDAO.updateAdmin(admin);
    }
}
