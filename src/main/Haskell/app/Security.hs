module Security where

import System.Directory (getCurrentDirectory)
import System.FilePath ((</>))
import Data.Bits (xor)
import Data.Char (chr, ord)
import System.IO (hClose, hPutStrLn, openFile, IOMode(WriteMode), Handle)

main :: IO ()
main = do
  -- Get the path to the current directory
  currentDir <- getCurrentDirectory

  -- Construct the path to the token file relative to the project directory
  let outputFilePath = currentDir </> "src" </> "main" </> "Haskell" </> "app" </> "datos" </> "token.txt"
  putStrLn outputFilePath

  let msg = ""
      key = ""

  let encrypted = encrypt msg key
  handleOut <- openFile outputFilePath WriteMode
  hPutStrLn handleOut encrypted
  hClose handleOut
  putStrLn "Mensaje cifrado guardado en el archivo de salida."
  
-- Función para descifrar un mensaje utilizando una clave
decrypt :: String -> String -> String
decrypt encrypted key = zipWith (\x y -> chr (xor (ord x) (ord y))) encrypted (cycle key)

-- Funcion para encriptar un mensaje
encrypt :: String -> String -> String
encrypt msg key = zipWith (\x y -> chr (xor (ord x) (ord y))) msg (cycle key)