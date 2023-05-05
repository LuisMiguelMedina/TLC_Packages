module Main where

import System.Directory (getCurrentDirectory)
import System.FilePath ((</>))
import System.IO (IOMode(ReadMode), hClose, hGetContents, openFile, Handle)
import Security (decrypt)

main :: IO ()
main = do
  -- Get the path to the current directory
  currentDir <- getCurrentDirectory
  
  -- Construct the path to the token file relative to the project directory
  let inputFilePath = currentDir </> "app" </> "datos" </> "token.txt"

  -- Open the input file for reading
  handleIn <- openFile inputFilePath ReadMode

  -- Read the contents of the input file
  contents <- hGetContents handleIn

  -- Read the user's key
  key <- getLine

  -- Decrypt the data using the decrypt function
  let decrypted = decrypt contents key

  -- Send the response string through standard output
  putStrLn decrypted

  -- Close the input file
  hClose handleIn