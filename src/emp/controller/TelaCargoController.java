package emp.controller;

import emp.model.CargoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TelaCargoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Cargo> lvCargos;

    @FXML
    private TextField tfNome;

    @FXML
    private TextArea taDescricao;

    @FXML
    private Button btInserir;

    @FXML
    void altereCargo(MouseEvent event) {

        if(event.getClickCount() == 2){
            Cargo c = lvCargos.getSelectionModel().getSelectedItem();
            tfNome.setText(c.getNome());
            taDescricao.setText(c.getDescricao());
        }
        
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void inserir(ActionEvent event) {

    }

    @FXML
    void salvar(ActionEvent event) {
        
        Cargo c = lvCargos.getSelectionModel().getSelectedItem();
        c.setNome(tfNome.getText());
        c.setDescricao(taDescricao.getText());
        try {
            CargoDAO.update(c);
        } catch (SQLException ex) {
            Logger.getLogger(TelaCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lvCargos.refresh();

    }

    @FXML
    void initialize() {
        
        try {
            ObservableList<Cargo> listaCargos = FXCollections.observableArrayList(CargoDAO.retreaveall());
            lvCargos.setItems(listaCargos);
        } catch (SQLException ex) {
            Logger.getLogger(TelaCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
