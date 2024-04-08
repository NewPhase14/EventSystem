package sample.BLL;

import sample.BE.Admin;
import sample.BE.EventCoordinator;
import sample.BLL.util.StatisticsSearcher;
import sample.DAL.AdminDAO;
import sample.DAL.EventCoordinatorDAO;

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
}
