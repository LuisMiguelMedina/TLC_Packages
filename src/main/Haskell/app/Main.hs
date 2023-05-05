module Main where

import System.IO (IOMode(ReadMode), hClose, hGetContents, openFile, Handle)
import Security (decrypt)

main :: IO ()
main = do
  let inputFilePath = "C:/Users/luism/OneDrive/Escritorio/TLC_Bank/src/main/Haskell/app/datos/token.txt"

  -- Abrir el archivo de entrada para leer
  handleIn <- openFile inputFilePath ReadMode

  -- Leer el contenido del archivo
  contents <- hGetContents handleIn

  -- Leer la clave del usuario
  key <- getLine

  -- Descifrar los datos utilizando la función decrypt
  let decrypted = decrypt contents key

  -- Enviar el string de respuesta a través de la salida estándar
  putStrLn decrypted

  -- Cerrar el archivo de entrada
  hClose handleIn