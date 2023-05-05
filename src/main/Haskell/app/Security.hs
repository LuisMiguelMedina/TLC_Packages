module Security where
  
import Data.Bits (xor)
import Data.Char (chr, ord)
import System.IO (hClose, hPutStrLn, openFile, IOMode(WriteMode), Handle)

main :: IO ()
main = do
  let msg = "CloudSA63d961d9%LuismiFmat!"
      key = "1415926535"
      outputFilePath = "C:/Users/luism/OneDrive/Escritorio/TLC_Bank/src/main/Haskell/app/datos/token.txt"
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