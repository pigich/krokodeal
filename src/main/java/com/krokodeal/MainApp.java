package com.krokodeal;


import com.krokodeal.controllers.MainViewController;
import com.krokodeal.controllers.mailDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {

    private final static String PROG_VERSION = "_0.1";
    private static Stage primaryStage;
    //    private static String image = src + "/images/logo.png";
//    private static File imageFile = new File(image);
    private BorderPane rootLayout;

    public MainApp() {
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0)
            launch(args);
        else {
            for (String s : args
                    ) {
                System.out.println(s);
            }
            System.exit(0);
        }
    }

    /**
     * Диалог выбора почтового провайдера через кого будет отправляться почта.
     */
    public static boolean showPersonEditDialog() {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/EmailEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit EmailData");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
//            dialogStage.getIcons().add(new Image(imageFile.toURI().toString()));
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Передаём адресата в контроллер.
            mailDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("Krokodeal" + PROG_VERSION);
        MainApp.primaryStage.setMinHeight(750);
        MainApp.primaryStage.setMinWidth(910);
        initRootLayout();
        try {
            showFirstView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализирует корневой макет.
     */
    private void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/krokodeal/fxml/RootLayout.fxml"));
            rootLayout = loader.load();
            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Загружаем главное окно
     */
    private void showFirstView() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/krokodeal/fxml/MainView.fxml"));
            AnchorPane firstView = loader.load();
            rootLayout.setCenter(null);
            rootLayout.setBottom(firstView);
            MainViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }

}
