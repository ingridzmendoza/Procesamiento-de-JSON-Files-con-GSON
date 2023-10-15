package org.example;

public class CreadorJson {
    public final StringBuilder stringParaJson = new StringBuilder(); //StringBuilder crear String para Json
    public boolean primeroElemento = true;
    public boolean enSales = false; //si esta en sales
    public boolean agregarBracket = false; //si se agrego bracket inicial

    public void agregarElemento(String name, String value) { //metodo crear estructura JSON

        if (!primeroElemento) { //agregar , cuando se acabe el contenido de una etiqueta
            stringParaJson.append(",");
        }
        if ("id".equals(name)) { //Agregar { antes de etiqueta ID
            stringParaJson.append("{");
            agregarBracket = true;
        }

        if ("sale".equals(name)) {// Verificamos si se esta en etiqueta de sale
            enSales = true;
            stringParaJson.append("\"").append(name).append("\":");
        } else {
            stringParaJson.append("\"").append(name).append("\":");
        }

        if (value.isEmpty()) {
            stringParaJson.append("null");
        } else if (value.matches("-?\\d+")) {
            stringParaJson.append(value);
        } else {
            stringParaJson.append("\"").append(value).append("\"");
        }

        if (enSales) {// Si estamos dentro de "sale," agregamos '}' al final de la etiqueta sale
            stringParaJson.append("}");
            enSales = false;
        }
        primeroElemento = false;
    }//end metodo agregarElemento

    public String getResultado() { //getResultado de JSON
        return "[" + stringParaJson + "]";
    }

}//end class
