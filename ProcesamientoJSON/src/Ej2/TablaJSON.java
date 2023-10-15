package Ej3;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

public class TablaJSON extends JFrame {
    private JTable jTable;

    public TablaJSON() {
        //definir nombre y dimensiones del swing table
        setTitle("Contenido car_sales.json");
        setSize(550, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // crear tabla y sus columnsa
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("First name");
        model.addColumn("Last name");
        model.addColumn("Car Brand");
        model.addColumn("Price");
        model.addColumn("State");

        // extrar los datos y meterlos a la tabla
        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = (JsonArray)parser.parse(new FileReader("src/car_sales.json"));

            for (Object obj : jsonArray) {
                JsonObject jsonObject = (JsonObject) obj;
                model.addRow(new Object[]{
                        jsonObject.get("id"),
                        jsonObject.get("first_name"),
                        jsonObject.get("last_name"),
                        jsonObject.get("car"),
                        jsonObject.get("price"),
                        jsonObject.get("state")
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //instanciar objeto jTable y agregarle color de fondo
        jTable = new JTable(model);
        jTable.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(jTable);
        getContentPane().add(scrollPane);
        setLocationRelativeTo(null);
    }

    //poner la tabla visible
    public static void main(String[] args) {
        TablaJSON appSwing = new TablaJSON();
        appSwing.setVisible(true);
        appSwing.setResizable(false);
    }
}
