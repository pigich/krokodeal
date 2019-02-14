package com.krokodeal.controllers;

import com.krokodeal.MainApp;
import com.krokodeal.pojos.EmailData;
import com.krokodeal.util.EmailUtil;
import com.krokodeal.util.ScheduleUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;

import static com.krokodeal.pojos.EmailData.*;
import static com.krokodeal.pojos.ParserFields.*;
import static com.krokodeal.pojos.ScheduleFields.*;
import static com.krokodeal.util.ScheduleUtil.setCommandToAddSchtasks;

public class MainViewController implements ScheduleUtil {

    @FXML
    private ComboBox<String> setTimeFX;
    @FXML
    private Label addScheduleLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Button addScheduleBtn;
    @FXML
    private CheckBox WED;
    @FXML
    private CheckBox SAT;
    @FXML
    private CheckBox FRI;
    @FXML
    private CheckBox TUE;
    @FXML
    private CheckBox SUN;
    @FXML
    private CheckBox THU;
    @FXML
    private CheckBox MON;
    @FXML
    private CheckBox daylyFX;
    @FXML
    private CheckBox setSchedule;
    @FXML
    private TextField search_textFX;
    @FXML
    private TextField auc_numFX;
    @FXML
    private TextField okrbFX;
    @FXML
    private TextField company_titleFX;
    @FXML
    private DatePicker from_dateFX;
    @FXML
    private DatePicker created_toFX;
    @FXML
    private DatePicker request_end_fromFX;
    @FXML
    private DatePicker request_end_toFX;
    @FXML
    private ComboBox<Enum> periodFX;
    @FXML
    private TextField subjectFX;
    private MainApp mainApp;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        subjectFX.textProperty().addListener((observable, oldValue, newValue) ->
                subjectFXMethod(oldValue, newValue));

        search_textFX.textProperty().addListener((observable, oldValue, newValue) ->
                search_textFXMethod(oldValue, newValue));
        auc_numFX.textProperty().addListener((observable, oldValue, newValue) ->
                auc_numFXMethod(oldValue, newValue));
        okrbFX.textProperty().addListener((observable, oldValue, newValue) ->
                okrbFXMethod(oldValue, newValue));
        company_titleFX.textProperty().addListener((observable, oldValue, newValue) ->
                company_titleFXMethod(oldValue, newValue));

        from_dateFX.setOnAction(event -> setFrom_date(from_dateFX.getValue()));
        created_toFX.setOnAction(event -> setCreated_to(created_toFX.getValue()));
        request_end_fromFX.setOnAction(event -> setRequest_end_from(request_end_fromFX.getValue()));
        request_end_toFX.setOnAction(event -> setRequest_end_to(request_end_toFX.getValue()));

        periodFX.setItems(FXCollections.observableArrayList(PeriodEnum.values()));
        periodFX.setVisibleRowCount(6);
        periodFX.getSelectionModel().selectFirst();
        periodFX.setOnAction(event -> setPeriod(periodFX.getValue().toString()));

        periodFX.getSelectionModel()
                .selectedIndexProperty()
                .addListener((options, oldValue, newValue) -> changePeriodValue((int) newValue));

        MON.disableProperty().setValue(true);
        TUE.disableProperty().setValue(true);
        WED.disableProperty().setValue(true);
        THU.disableProperty().setValue(true);
        FRI.disableProperty().setValue(true);
        SAT.disableProperty().setValue(true);
        SUN.disableProperty().setValue(true);
        daylyFX.disableProperty().setValue(true);
        addScheduleBtn.disableProperty().setValue(true);
        setTimeFX.disableProperty().setValue(true);
        addScheduleLabel.disableProperty().setValue(true);
        timeLabel.disableProperty().setValue(true);

        setTimeFX.setItems(FXCollections.observableArrayList(timeList));
        SingleSelectionModel<String> ssm = setTimeFX.getSelectionModel();
        setTimeFX.setSelectionModel(ssm);
        setTimeFX.getSelectionModel().selectFirst();
        setTimeFX.getSelectionModel()
                .selectedItemProperty()
                .addListener((options, oldValue, newValue) -> changeTimeValue(newValue));
        MON.selectedProperty()
                .addListener((options, oldValue, newValue) -> changeDaysMap(MON.getId(), newValue));
        TUE.selectedProperty()
                .addListener((options, oldValue, newValue) -> changeDaysMap(TUE.getId(), newValue));
        WED.selectedProperty()
                .addListener((options, oldValue, newValue) -> changeDaysMap(WED.getId(), newValue));
        THU.selectedProperty()
                .addListener((options, oldValue, newValue) -> changeDaysMap(THU.getId(), newValue));
        FRI.selectedProperty()
                .addListener((options, oldValue, newValue) -> changeDaysMap(FRI.getId(), newValue));
        SAT.selectedProperty()
                .addListener((options, oldValue, newValue) -> changeDaysMap(SAT.getId(), newValue));
        SUN.selectedProperty()
                .addListener((options, oldValue, newValue) -> changeDaysMap(SUN.getId(), newValue));

        daylyFX.selectedProperty()
                .addListener((options, oldValue, newValue) -> changeDaysMap(daylyFX.getId(), newValue));
        setSchedule.selectedProperty().addListener((options, oldValue, newValue) -> setScheduleMethod(oldValue));
    }

    private void changeTimeValue(String newValue) {
        setSelectedTime(newValue);
    }

    private void changeDaysMap(String string, Boolean newValue) {
        if (string.equals(daylyFX.getId())) {
            if (newValue.equals(false)) {

            } else {
                chosenDaysMap.put("MON", newValue);
                chosenDaysMap.put("TUE", newValue);
                chosenDaysMap.put("WED", newValue);
                chosenDaysMap.put("THU", newValue);
                chosenDaysMap.put("FRI", newValue);
                chosenDaysMap.put("SAT", newValue);
                chosenDaysMap.put("SUN", newValue);
                MON.setSelected(true);
                TUE.setSelected(true);
                WED.setSelected(true);
                THU.setSelected(true);
                FRI.setSelected(true);
                SAT.setSelected(true);
                SUN.setSelected(true);
            }
        } else {
            if (newValue.equals(false)) {
                chosenDaysMap.remove(string);
                daylyFX.setSelected(false);

            } else {
                chosenDaysMap.put(string, newValue);
            }
        }
    }

    private void setScheduleMethod(Boolean newValue) {
        MON.disableProperty().setValue(newValue);
        TUE.disableProperty().setValue(newValue);
        WED.disableProperty().setValue(newValue);
        THU.disableProperty().setValue(newValue);
        FRI.disableProperty().setValue(newValue);
        SAT.disableProperty().setValue(newValue);
        SUN.disableProperty().setValue(newValue);
        daylyFX.disableProperty().setValue(newValue);
        addScheduleBtn.disableProperty().setValue(newValue);
        setTimeFX.disableProperty().setValue(newValue);
        addScheduleLabel.disableProperty().setValue(newValue);
        timeLabel.disableProperty().setValue(newValue);
    }

    /**
     * При выборе периода в ComboBox "Выберите период" , мы присваем переменной period значение, также переменные
     * from_dateFX и created_toFX делаем пустыми и неактивными
     **/
    private void changePeriodValue(int newValue) {
        if (newValue > 0) {
            from_dateFX.disableProperty().setValue(true);
            created_toFX.disableProperty().setValue(true);
            setFrom_date("");
            setCreated_to("");
        } else {
            from_dateFX.disableProperty().setValue(false);
            created_toFX.disableProperty().setValue(false);
        }
        switch (newValue) {
            case 0:
                setPeriod("");
                break;
            case 1:
                setPeriod("1+day");
                break;
            case 2:
                setPeriod("3+days");
                break;
            case 3:
                setPeriod("1+week");
                break;
            case 4:
                setPeriod("2+weeks");
                break;
            case 5:
                setPeriod("1+month");
                break;
        }
    }

    @FXML
    private void createSchedule() {
        if (daylyFX.isSelected()) {
            System.out.println(setCommandToAddSchtasks(getDaily(), getSelectedTime()));
        } else if (MON.isSelected()
                || TUE.isSelected()
                || WED.isSelected()
                || THU.isSelected()
                || FRI.isSelected()
                || SUN.isSelected()
                || SAT.isSelected()) {
            System.out.println(setCommandToAddSchtasks(getSelectedTime(), getChosenDays()));
        }
    }

    /**
     * todo: must be the way to refactor all bellow methods to one
     */
    @FXML
    private void subjectFXMethod(String oldValue, String newValue) {
        if (subjectFX == null || (newValue.length() < oldValue.length())) {
            setSubject("");
        } else {
            setSubject(subjectFX.getText());
        }
    }

    @FXML
    private void okrbFXMethod(String oldValue, String newValue) {
        if (okrbFX == null || (newValue.length() < oldValue.length())) {
            setOkrb("");
        } else {
            setOkrb(okrbFX.getText());
        }
    }

    @FXML
    private void search_textFXMethod(String oldValue, String newValue) {
        if (search_textFX == null || (newValue.length() < oldValue.length())) {
            setSearch_text("");
        } else {
            setSearch_text(search_textFX.getText());
        }
    }

    @FXML
    private void auc_numFXMethod(String oldValue, String newValue) {
        if (auc_numFX == null || (newValue.length() < oldValue.length())) {
            setAuc_num("");
        } else {
            setAuc_num(auc_numFX.getText());
        }
    }

    @FXML
    private void company_titleFXMethod(String oldValue, String newValue) {
        if (company_titleFX == null || (newValue.length() < oldValue.length())) {
            setCompany_title("");
        } else {
            setCompany_title(company_titleFX.getText());
        }
    }

    @FXML
    public void sendEmail() {
        try {
            setParsedHtmlString();
            System.out.println(getParsedHtmlString());
        } catch (IOException e) {
            System.out.println(" Can not parse IceTrade");
        }
        setFinalHtmlString();
        System.out.println(getFinalHtmlString());
        listOfUEmails.add("mybigoblako@gmail.com");
        if (listOfUEmails.size() > 0) {
            for (String toEmail : listOfUEmails
                    ) {
                System.out.println("SSLEmail Start");
                EmailData emailData = new EmailData(getLogin(), getPassword());
                Authenticator auth = new Authenticator() {
                    //need override the getPasswordAuthentication method
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(getLogin(), getPassword());
                    }
                };
                Session session = Session.getDefaultInstance(emailData.getProps(), auth);
                System.out.println("Session created");
                EmailUtil.sendEmail(session, toEmail, subjectFX.getText(), "html");
            }
            listOfUEmails.clear();
        }
    }

    public void preView() {
        System.out.println("Preview");
    }
}
