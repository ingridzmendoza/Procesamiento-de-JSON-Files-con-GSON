package Ej1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcesadorJSON {

    //metodo para calcular promedio
    public static double promedioPrecios(List<Double> precios) {
        double suma = 0;
        for (double valor : precios) {
            suma += valor;
        }
        return (suma / precios.size());
    }

    public static void main(String[] args) {
        try {

            //ubicacion del archivo en source code
            String jsonFilePath = "src/car_sales.json";
            FileReader reader = new FileReader(jsonFilePath);

            //usar gson para hacer el parse
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = jsonParser.parse(reader).getAsJsonArray();

            // hashmap para almacenar los precios por marca
            Map<String, List<Double>> preciosPorMarca = new HashMap<>();

            // extraer precios y almacenarlos en el hashmap
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String marca = jsonObject.get("car").getAsString();

                //quitarle el $ y la , para poder pasar los valores numericos a double
                String precioString = jsonObject.get("price").getAsString().replaceAll("[$,]", "");
                double precio = Double.parseDouble(precioString);

                // comprobar que no se repitan las marcas
                if (preciosPorMarca.containsKey(marca)) {
                    preciosPorMarca.get(marca).add(precio);
                } else {
                    List<Double> precios = new ArrayList<>();
                    precios.add(precio);
                    preciosPorMarca.put(marca, precios);
                }
            }

            //imprimir el reporte
            System.out.printf("Marca: \t\t    | \tPromedio de Precios:\n\n");

            // Calcular y mostrar el promedio por marca de carro
            for (Map.Entry<String, List<Double>> entry : preciosPorMarca.entrySet()) {
                String marca = entry.getKey();
                List<Double> precios = entry.getValue();
                double promedio = promedioPrecios(precios);
                System.out.printf("%-15s | \t\t $%.2f%n", marca, promedio);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

