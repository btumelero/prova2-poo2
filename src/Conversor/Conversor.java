/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conversor;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author bruno.191196
 */
public class Conversor {
  ArrayList<Map<EnumTemperatura, Double>> registro;
  Map<EnumTemperatura, Double> temperaturas;

  public Conversor() {
    this.registro = new ArrayList<>();
    this.temperaturas = new HashMap<>();
  }
  
  public Map<EnumTemperatura, Double> converter (String temperatura, double valor) {
    if (temperatura.equalsIgnoreCase(EnumTemperatura.CELSIUS.toString())) {
      temperaturas.put(EnumTemperatura.CELSIUS, valor);
      temperaturas.put(EnumTemperatura.FAHRENHEIT, celsiusParaFahrenheit(valor));
      temperaturas.put(EnumTemperatura.KELVIN, celsiusParaKelvin(valor));
    } else {
      if (temperatura.equalsIgnoreCase(EnumTemperatura.FAHRENHEIT.toString())) {
        temperaturas.put(EnumTemperatura.CELSIUS, fahrenheitParaCelsius(valor));
        temperaturas.put(EnumTemperatura.FAHRENHEIT, valor);
        temperaturas.put(EnumTemperatura.KELVIN, fahrenheitParaKelvin(valor));
      } else {
        if (temperatura.equalsIgnoreCase(EnumTemperatura.KELVIN.toString())) {
          temperaturas.put(EnumTemperatura.CELSIUS, kelvinParaCelsius(valor));
          temperaturas.put(EnumTemperatura.FAHRENHEIT, kelvinParaFahrenheit(valor));
          temperaturas.put(EnumTemperatura.KELVIN, valor);
        }
      }
    }
    registro.add(temperaturas);
    return temperaturas;
  }
  
  public double fahrenheitParaCelsius (double valor) {
    return (valor - 32) / 1.8;
  }
  
  public double celsiusParaFahrenheit (double valor) {
    return 1.8 * valor + 32;
  }
  
  public double kelvinParaCelsius (double valor) {
    return valor - 273;
  }
  
  public double celsiusParaKelvin (double valor) {
    return valor + 273;
  }
  
  public double fahrenheitParaKelvin (double valor) {
    return (((valor - 32) / 9) - 273) / 5;
  }
  
  public double kelvinParaFahrenheit (double valor) {
    return (((valor - 273) / 5) - 32) / 9;
  }
  
  public void salvar () {
    registro.forEach((map) -> {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("Conversões.txt"))) {
        for (Entry entry: map.entrySet()) {
          bw.write(entry.getKey().toString() + ": " +entry.getValue());
          bw.newLine();
        }
        bw.newLine();
      }
      catch (FileNotFoundException ex) {
        System.out.println("Conversões.txt não encontrado");
      }
      catch (IOException ex) {
        System.out.println("Não foi possível ler o arquivo Conversões.txt");
      }
    });
  }
}
