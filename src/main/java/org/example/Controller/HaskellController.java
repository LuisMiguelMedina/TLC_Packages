package org.example.Controller;

import org.example.Utilities.KeyManager;
import java.io.*;
import java.nio.file.*;
import java.lang.System;

public class HaskellController {
    public String conexionPorHaskell() throws IOException {
        ProcessBuilder pb = pathByOS();
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
    public ProcessBuilder pathByOS(){
        //Encuentra el sistema
        String os = System.getProperty("os.name").toLowerCase();

        //Busca el path
        Path currentPath = Paths.get("").toAbsolutePath();
        Path targetPath = Paths.get("src", "main", "Haskell");
        Path combinedPath = currentPath.resolve(targetPath);
        String pathString = combinedPath.toString();

        if (os.contains("win")) {
            String command = "cd " + pathString + " & cabal run";
            return new ProcessBuilder("cmd.exe", "/c", command);
        } else {
            String command = "cd " + pathString + " && cabal run";
            return new ProcessBuilder("/bin/bash", "-c", command);
        }
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