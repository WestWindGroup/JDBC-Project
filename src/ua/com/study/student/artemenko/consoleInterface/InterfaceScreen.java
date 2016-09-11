package ua.com.study.student.artemenko.consoleInterface;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InterfaceScreen {

    private int countItemInList;
    private String headScreen;
    private Map<Integer, String> mapListScreenView = new HashMap<>();
    private Map<Integer, InterfaceScreen> mapListInterfaceScreen = new HashMap<>();
    private InputScreen inputScreen;

    public InterfaceScreen(String path){
        readFromFileList(path);
        inputScreen = new InputScreen();
    }

    private void readFromFileList(String path) {
        File file = new File(path);
        countItemInList = 0;
        try (BufferedReader rd = new BufferedReader(new FileReader(file))) {
            String strHelp;
            while ((strHelp = rd.readLine()) != null) {
                if (countItemInList == 0) {
                    headScreen = strHelp;
                    countItemInList++;
                } else {
                    mapListScreenView.put(countItemInList, strHelp);
                    countItemInList++;
                }
            }
            countItemInList--;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла " + path);
        }
    }

    public void showInterfaceScreen(){
        inputScreen.choiceInputScreen(this.headScreen);
        printLine();
        showString(headScreen);
        printLine();
        showMapList(mapListScreenView);
        printLine();
    }

    public void showMapList(Map<Integer,String> map) {
        Set<Map.Entry<Integer,String>> set = map.entrySet();
        for (Map.Entry<Integer,String> setHelpIter : set) {
            System.out.println(setHelpIter.getKey() + ". " + setHelpIter.getValue());
        }
    }

    private void showString(String headScreen) {
        System.out.println(headScreen);
    }

    private void printLine() {
        System.out.println("--------------------------------------------------------");
    }

    public Map<Integer, InterfaceScreen> getMapListInterfaceScreen() {
        return mapListInterfaceScreen;
    }

    public void setMapListInterfaceScreen(Map<Integer, InterfaceScreen> mapListInterfaceScreen) {
        this.mapListInterfaceScreen = mapListInterfaceScreen;
    }

    public int getCountItemInList() {
        return countItemInList;
    }

    public void setCountItemInList(int countItemInList) {
        this.countItemInList = countItemInList;
    }

    public String getHeadScreen() {
        return headScreen;
    }

    public void setHeadScreen(String headScreen) {
        this.headScreen = headScreen;
    }

    public Map<Integer, String> getMapListScreenView() {
        return mapListScreenView;
    }

    public void setMapListScreenView(Map<Integer, String> mapListScreenView) {
        this.mapListScreenView = mapListScreenView;
    }

    @Override
    public String toString() {
        return "InterfaceScreen{" +
                "headScreen='" + headScreen + '\'' +
                '}';
    }
}
