import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class ex22_login_register {

public static void main(String[] args){
try (Scanner llegir = new  Scanner(System.in)) {


    System.out.print("Introdueix el teu usuari: ");
    String  usuari = llegir.nextLine();
    
    System.out.print("Introdueix la teva contrasenya (número enter): ");
    int contrIngressada = llegir.nextInt();
    llegir.nextLine(); //Netejar el buffer de l'Scanner.


    String nomFitxer = usuari + ".dat"; //Construím el nom del fitxer binari [nomusuari].dat.
    Path path = Paths.get(nomFitxer); //Métode per comprovar si el fitxer existeix. Com no acepta un String directament, necessita un objecte de tipus Path
                                    
    
   if (Files.exists(path)){ //Comprovar si el fitxer existeix.

    //DataInputStream per lleguir valor integer guardat en binari, del fitxers .dat. 
    try (DataInputStream lector = new DataInputStream(new FileInputStream(nomFitxer))) {

        int contrGuardada = lector.readInt(); //Aquest métode llegueix el número enter guardat en binari.

    //Verificar si coincideix.
    if (contrIngressada == contrGuardada) {
        System.out.println("Login Correcte");
    } else {
        System.out.println("Contrasenya Incorrecte");
    }

    } catch (IOException e) {
        System.out.println("Error a l'hora de processar el fitxer"); //Error que salta si el fixter no existeix o no es troba o a l'hora de lleguir o escriure dades etc.
    }

   //Si el fitxer no existeix demanar registrar-se.
   } else { 
        System.out.println("L'usuari no existeix. Vols registrar-te? (S/N): ");
        String opcio = llegir.nextLine();
    
        //Acepta majúscules i minúscules.
        if (opcio.equals("S") || opcio.equals("s")) {
            
            //DataOutputStream escriu el valor integer en binari.
            try (DataOutputStream escriptor = new DataOutputStream(new FileOutputStream(nomFitxer))) {
                escriptor.writeInt(contrIngressada); 
                System.out.println("Usuari registrat correctament!");

            } catch (IOException e) {
                System.out.println("Error en crear el fitxer");
            }
        }
   }

   llegir.close();
}
}
}