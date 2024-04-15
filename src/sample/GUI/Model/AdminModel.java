package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Admin;
import sample.BLL.AdminManager;

public class AdminModel {
    private AdminManager adminManager;
    private ObservableList<Admin> observableAdmins;
    private Admin admin = LoggedInModel.getInstance().getAdmin();

    public AdminModel() throws Exception {
        adminManager = new AdminManager();
        observableAdmins = FXCollections.observableArrayList();
        observableAdmins.addAll(adminManager.getAllAdmins());
    }

    public ObservableList<Admin> getObservableEventCoordinators() {
        return observableAdmins;
    }

}
