package org.example.Controller;

import org.example.Utilities.KeyManager;
import java.io.*;
public class HaskellController {
    public String conexionPorHaskell() throws IOException {
        // Cambiar el directorio de trabajo y ejecutar cabal run
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "cd C:/Users/luism/OneDrive/Escritorio/TLC_Bank/src/main/Haskell & cabal run");
        pb.redirectErrorStream(true);
        Process p = pb.start();

        // Crear los streams para enviar solicitudes y recibir respuestas
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        PrintWriter writer = new PrintWriter(p.getOutputStream(), true);

        // key
        writer.println(KeyManager.key);

        // Leer el string de respuesta del proceso Haskell
        String invalid = reader.readLine();
        String Tokens = reader.readLine();

        // Cerrar el proceso Haskell
        p.destroy();
        return Tokens;
    }
    public String usuario(String tokens){
    String[] split = tokens.split("%");
        return split[0];
    }
    public String contrasena(String tokens){
        String[] split = tokens.split("%");
        return split[1];
    }
}