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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TelaClienteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Cargo> lvCargos;

    @FXML
    private TextField tfNome;
   
    @FXML
    private TextField tfCPF;

    @FXML
    private Button btInserir;
    
    @FXML
    private Button btExcluir;

    @FXML
    void altereCargo(MouseEvent event) {

        if(event.getClickCount() == 2){
            Cargo c = lvCargos.getSelectionModel().getSelectedItem();
            tfNome.setText(c.getNome());
            taDescricao.setText(c.getDescricao());
            lvCargos.setDisable(true);
            btExcluir.setDisable(false);
        }
        
    }

    @FXML
    void cancelar(ActionEvent event) {
        
        limpaTela();
        btInserir.setDisable(true);

    }

   @FXML
    void excluir(ActionEvent event) {
        
        Alert desejaExcluir = new Alert(Alert.AlertType.CONFIRMATION);
        desejaExcluir.setTitle("Atenção aí ow");
        desejaExcluir.setHeaderText("FICA LIGADO AI MORO?");
        desejaExcluir.setContentText("MAS VOCÊ ESTÁ CERTO DISSO? ~voz do Silvio Santos~");
                
        if(desejaExcluir.showAndWait().get() == ButtonType.OK){
        
            try {   
                Cargo c = lvCargos.getSelectionModel().getSelectedItem();
                CargoDAO.delete(c);
                lvCargos.getItems().remove(c);
                lvCargos.refresh();
                lvCargos.setDisable(false);
                lvCargos.getSelectionModel().clearSelection();
            } catch (SQLException ex) {
                Logger.getLogger(TelaClienteController.class.getName()).log(Level.SEVERE, null, ex);
                Alert erroAlerta = new Alert(Alert.AlertType.ERROR);
                erroAlerta.setHeaderText("Ih rapaz aí deu ruim");
                erroAlerta.setContentText(ex.getMessage());
                erroAlerta.showAndWait();
            }
            
        }
        
    }

    @FXML
    void salvar(ActionEvent event) {
        
        if(tfNome.getText().equals("")){
            
            throw new RuntimeException("Existem campos vazios!");
        }
        
        if(lvCargos.getSelectionModel().isEmpty()){
            
            Cargo c = new Cargo(tfNome.getText(), taDescricao.getText());
            try {
                CargoDAO.create(c);
                lvCargos.getItems().add(c);
            } catch (SQLException ex) {
                Logger.getLogger(TelaClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
        
        Cargo c = lvCargos.getSelectionModel().getSelectedItem();
        c.setNome(tfNome.getText());
        c.setDescricao(taDescricao.getText());
        try {
            CargoDAO.update(c);
        } catch (SQLException ex) {
            Logger.getLogger(TelaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
        
        limpaTela();
        lvCargos.refresh();

    }

    @FXML
    void initialize() {
        
        try {
            ObservableList<Cargo> listaCargos = FXCollections.observableArrayList(CargoDAO.retreaveall());
            lvCargos.setItems(listaCargos);
        } catch (SQLException ex) {
            Logger.getLogger(TelaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void limpaTela(){
        
        tfNome.clear();
        taDescricao.clear();
        lvCargos.getSelectionModel().clearSelection();
        lvCargos.setDisable(false);
        btExcluir.setDisable(true);
       
        
    }
    
    
}
