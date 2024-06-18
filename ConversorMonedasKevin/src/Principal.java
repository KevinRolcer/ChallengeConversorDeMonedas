import javax.swing.*;
import java.io.IOException;

public class Principal {
    private static boolean op=true;
    public static void main(String[] args) {
        Conversor consulta=new Conversor();

        do{
            int n = JOptionPane.showConfirmDialog(
                    null,
                    "¿Desea realizar una conversión?",
                    "Conversor de monedas en java",
                    JOptionPane.YES_NO_OPTION);

            if(n==1){
                op=false;
                System.out.println("Fin del programa");
                break;
            }

            String[] divisas = {"USD Dollar", "EUR Euro", "MXN Peso Mexicano", "JPY Yen", "GBP Libra Esterlina", "Salir"};

            int opcionInicial = JOptionPane.showOptionDialog(null, "Seleccione la moneda que posee",
                    "Conversor de monedas", JOptionPane.DEFAULT_OPTION,
                    0, null, divisas, "Salir");

            int opcionConvertir = JOptionPane.showOptionDialog(null, "Seleccione la divisa a la queb dese",
                    "Conversor de monedas", JOptionPane.DEFAULT_OPTION,
                    0, null, divisas, "Salir");
            try{
                Moneda moneda= consulta.convertirMoneda(divisas[opcionInicial], divisas[opcionConvertir]);
                System.out.println(moneda);

                CrearArchivo generador = new CrearArchivo();
                generador.guardarJson(moneda);

                int cantidad= Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la cantidad de"
                        +divisas[opcionInicial].substring(0,3)+" que desea convertir"));

                System.out.println("Valor actual de 1 "+divisas[opcionInicial] +"a " +
                        divisas[opcionConvertir] +"= "+ moneda.conversion_rate());
                double cantidadFinal= moneda.conversion_rate()*cantidad;

                JOptionPane.showMessageDialog(null,"La cantidad en "+divisas[opcionConvertir].substring(0,3)+
                        " es: " + cantidadFinal);

            } catch (NumberFormatException e){
                System.out.println("Error"+e.getMessage());
            } catch (RuntimeException | IOException e){
                System.out.println(e.getMessage());
                System.out.println("Finalizando la aplicación.");
            }
        }while(op);

        System.out.println("Final del programa");
    }
}
