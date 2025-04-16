package cvbuilder.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import cvbuilder.View.Observer;

public class CVData {

    // declaring
    private ArrayList<String> userNames;
    private ArrayList<String> userTitles;
    private ArrayList<String> userEmails;
    private ArrayList<String> coreSkills;
    private ArrayList<String> coreProfileStatement;

    private ArrayList<Observer> observers;

    public CVData() {

        // initializing
        this.userNames = new ArrayList<>();
        this.userTitles = new ArrayList<>();
        this.userEmails = new ArrayList<>();
        this.coreSkills = new ArrayList<>();
        this.coreProfileStatement = new ArrayList<>();

        this.loadDataFromCSVFile("data/cv_repo_1.csv");

        this.observers = new ArrayList<>();
    }

    public ArrayList<String> getUserNames() {
        return userNames;
    }

    public ArrayList<String> getUserTitles() {
        return userTitles;
    }

    public ArrayList<String> getUserEmails() {
        return userEmails;
    }

    public ArrayList<String> getCoreSkills() {
        return coreSkills;
    }

    public ArrayList<String> getCoreProfileStatement() {
        return coreProfileStatement;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    // observers are notified when the data changes and the view is updated
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void loadDataFromCSVFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.ready()) {

                // Read a line of CSV
                String cSVline = br.readLine();

                // Split the line by commas
                String[] values = cSVline.split(",");

                switch (values[0].toLowerCase()) {
                    case "user":
                        switch (values[1].toLowerCase()) {
                            case "name":
                                for (int i = 2; i < values.length; i++) {
                                    userNames.add(values[i]);
                                }
                                break;
                            case "title":
                                for (int i = 2; i < values.length; i++) {
                                    userTitles.add(values[i]);
                                }
                                break;
                            case "email":
                                for (int i = 2; i < values.length; i++) {
                                    userEmails.add(values[i]);
                                }
                                break;
                        }
                        break;
                    case "core competencies":
                        switch (values[1].toLowerCase()) {
                            case "skills":
                                for (int i = 2; i < values.length; i++) {

                                    //// For each value separated by comma in the CSV
                                    // Replace "////"" with commas within the value
                                    String formattedSkills = values[i].replace("////", ", ");
                                    coreSkills.add(formattedSkills.trim());
                                }
                                break;
                            case "profile statement":
                                for (int i = 2; i < values.length; i++) {

                                    // For each value separated by comma in the CSV
                                    // Replace //// with periods within the value
                                    String formattedStatement = values[i].replace("////", ". ");
                                    coreProfileStatement.add(formattedStatement.trim());
                                }
                                break;
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Profile statement count: " + coreProfileStatement.size());
        System.out.println("Skills count: " + coreSkills.size());

        System.out.println("User names: " + userNames);
        System.out.println("User titles: " + userTitles);
        System.out.println("User emails: " + userEmails);
        System.out.println("Skills: " + coreSkills);
        System.out.println("Profile statements: " + coreProfileStatement);
    }

}