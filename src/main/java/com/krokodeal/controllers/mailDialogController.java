package com.krokodeal.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.krokodeal.pojos.EmailData.*;


public class mailDialogController {

    @FXML
    private TextField emailLogin;
    @FXML
    private TextField personalId;
    @FXML
    private TextField emailPassword;
    private boolean okClicked = false;
    private Stage dialogStage;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage stage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return Returns true, если пользователь кликнул OK, в другом случае false.
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            setLogin(emailLogin.getText());
            setPassword(emailPassword.getText());
            setPersonalId(personalId.getText());
//            setSmtpMailProvider();
//            System.out.println(getSmtpMailProvider());
            okClicked = true;
            dialogStage.close();
        }
    }


    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (emailLogin.getText() == null || emailLogin.getText().length() == 0) {
            errorMessage += "No valid Login!\n";
        }
        if (emailPassword.getText() == null || emailPassword.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
