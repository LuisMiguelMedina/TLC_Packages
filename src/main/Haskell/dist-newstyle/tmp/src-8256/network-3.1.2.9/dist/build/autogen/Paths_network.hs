{-# LANGUAGE CPP #-}
{-# LANGUAGE NoRebindableSyntax #-}
{-# OPTIONS_GHC -fno-warn-missing-import-lists #-}
{-# OPTIONS_GHC -w #-}
module Paths_network (
    version,
    getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where


import qualified Control.Exception as Exception
import qualified Data.List as List
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude


#if defined(VERSION_base)

#if MIN_VERSION_base(4,0,0)
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#else
catchIO :: IO a -> (Exception.Exception -> IO a) -> IO a
#endif

#else
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#endif
catchIO = Exception.catch

version :: Version
version = Version [3,1,2,9] []

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir `joinFileName` name)

getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath



bindir, libdir, dynlibdir, datadir, libexecdir, sysconfdir :: FilePath
bindir     = "C:\\cabal\\store\\ghc-9.2.5\\network-3.1.2.9-70bb9a4424d3661ff77c4c04080ec87c8d7ac4cc\\bin"
libdir     = "C:\\cabal\\store\\ghc-9.2.5\\network-3.1.2.9-70bb9a4424d3661ff77c4c04080ec87c8d7ac4cc\\lib"
dynlibdir  = "C:\\cabal\\store\\ghc-9.2.5\\network-3.1.2.9-70bb9a4424d3661ff77c4c04080ec87c8d7ac4cc\\lib"
datadir    = "C:\\cabal\\store\\ghc-9.2.5\\network-3.1.2.9-70bb9a4424d3661ff77c4c04080ec87c8d7ac4cc\\share"
libexecdir = "C:\\cabal\\store\\ghc-9.2.5\\network-3.1.2.9-70bb9a4424d3661ff77c4c04080ec87c8d7ac4cc\\libexec"
sysconfdir = "C:\\cabal\\store\\ghc-9.2.5\\network-3.1.2.9-70bb9a4424d3661ff77c4c04080ec87c8d7ac4cc\\etc"

getBinDir     = catchIO (getEnv "network_bindir")     (\_ -> return bindir)
getLibDir     = catchIO (getEnv "network_libdir")     (\_ -> return libdir)
getDynLibDir  = catchIO (getEnv "network_dynlibdir")  (\_ -> return dynlibdir)
getDataDir    = catchIO (getEnv "network_datadir")    (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "network_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "network_sysconfdir") (\_ -> return sysconfdir)




joinFileName :: String -> String -> FilePath
joinFileName ""  fname = fname
joinFileName "." fname = fname
joinFileName dir ""    = dir
joinFileName dir fname
  | isPathSeparator (List.last dir) = dir ++ fname
  | otherwise                       = dir ++ pathSeparator : fname

pathSeparator :: Char
pathSeparator = '\\'

isPathSeparator :: Char -> Bool
isPathSeparator c = c == '/' || c == '\\'
