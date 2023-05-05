{-# LINE 1 "Network\\Socket\\Options.hsc" #-}
{-# LANGUAGE CPP #-}
{-# LANGUAGE PatternSynonyms #-}
{-# LANGUAGE ScopedTypeVariables #-}
{-# LANGUAGE RecordWildCards #-}
{-# LANGUAGE ViewPatterns #-}


#include "HsNetDef.h"

module Network.Socket.Options (
    SocketOption(SockOpt
                ,UnsupportedSocketOption
                ,AcceptConn,Debug,ReuseAddr,SoDomain,Type,SoProtocol,SoError
                ,DontRoute,Broadcast,SendBuffer,RecvBuffer,KeepAlive,OOBInline
                ,TimeToLive,MaxSegment,NoDelay,Cork,Linger,ReusePort
                ,RecvLowWater,SendLowWater,RecvTimeOut,SendTimeOut
                ,UseLoopBack,UserTimeout,IPv6Only
                ,RecvIPv4TTL,RecvIPv4TOS,RecvIPv4PktInfo
                ,RecvIPv6HopLimit,RecvIPv6TClass,RecvIPv6PktInfo
                ,CustomSockOpt)
  , isSupportedSocketOption
  , whenSupported
  , getSocketType
  , getSocketOption
  , setSocketOption
  , getSockOpt
  , setSockOpt
  , StructLinger (..)
  ) where

import qualified Text.Read as P

import Foreign.Marshal.Alloc (alloca)
import Foreign.Marshal.Utils (with)

import Network.Socket.Imports
import Network.Socket.Internal
import Network.Socket.Types
import Network.Socket.ReadShow

-----------------------------------------------------------------------------
-- Socket Properties

-- | Socket options for use with 'setSocketOption' and 'getSocketOption'.
--
-- The existence of a constructor does not imply that the relevant option
-- is supported on your system: see 'isSupportedSocketOption'
data SocketOption = SockOpt

{-# LINE 50 "Network\\Socket\\Options.hsc" #-}
    !CInt -- ^ Option Level
    !CInt -- ^ Option Name

{-# LINE 56 "Network\\Socket\\Options.hsc" #-}
  deriving (Eq)

-- | Does the 'SocketOption' exist on this system?
isSupportedSocketOption :: SocketOption -> Bool
isSupportedSocketOption opt = opt /= SockOpt (-1) (-1)

-- | Get the 'SocketType' of an active socket.
--
--   Since: 3.0.1.0
getSocketType :: Socket -> IO SocketType
getSocketType s = unpackSocketType <$> getSockOpt s Type

pattern UnsupportedSocketOption :: SocketOption
pattern UnsupportedSocketOption = SockOpt (-1) (-1)


{-# LINE 72 "Network\\Socket\\Options.hsc" #-}
-- | SO_ACCEPTCONN, read-only
pattern AcceptConn :: SocketOption

{-# LINE 75 "Network\\Socket\\Options.hsc" #-}
pattern AcceptConn     = SockOpt (65535) (2)
{-# LINE 76 "Network\\Socket\\Options.hsc" #-}

{-# LINE 79 "Network\\Socket\\Options.hsc" #-}
-- | SO_DEBUG
pattern Debug :: SocketOption

{-# LINE 82 "Network\\Socket\\Options.hsc" #-}
pattern Debug          = SockOpt (65535) (1)
{-# LINE 83 "Network\\Socket\\Options.hsc" #-}

{-# LINE 86 "Network\\Socket\\Options.hsc" #-}
-- | SO_REUSEADDR
pattern ReuseAddr :: SocketOption

{-# LINE 89 "Network\\Socket\\Options.hsc" #-}
pattern ReuseAddr      = SockOpt (65535) (4)
{-# LINE 90 "Network\\Socket\\Options.hsc" #-}

{-# LINE 93 "Network\\Socket\\Options.hsc" #-}

-- | SO_DOMAIN, read-only
pattern SoDomain :: SocketOption

{-# LINE 99 "Network\\Socket\\Options.hsc" #-}
pattern SoDomain       = SockOpt (-1) (-1)

{-# LINE 101 "Network\\Socket\\Options.hsc" #-}

-- | SO_TYPE, read-only
pattern Type :: SocketOption

{-# LINE 105 "Network\\Socket\\Options.hsc" #-}
pattern Type           = SockOpt (65535) (4104)
{-# LINE 106 "Network\\Socket\\Options.hsc" #-}

{-# LINE 109 "Network\\Socket\\Options.hsc" #-}

-- | SO_PROTOCOL, read-only
pattern SoProtocol :: SocketOption

{-# LINE 115 "Network\\Socket\\Options.hsc" #-}
pattern SoProtocol     = SockOpt (-1) (-1)

{-# LINE 117 "Network\\Socket\\Options.hsc" #-}

-- | SO_ERROR
pattern SoError :: SocketOption

{-# LINE 121 "Network\\Socket\\Options.hsc" #-}
pattern SoError        = SockOpt (65535) (4103)
{-# LINE 122 "Network\\Socket\\Options.hsc" #-}

{-# LINE 125 "Network\\Socket\\Options.hsc" #-}
-- | SO_DONTROUTE
pattern DontRoute :: SocketOption

{-# LINE 128 "Network\\Socket\\Options.hsc" #-}
pattern DontRoute      = SockOpt (65535) (16)
{-# LINE 129 "Network\\Socket\\Options.hsc" #-}

{-# LINE 132 "Network\\Socket\\Options.hsc" #-}
-- | SO_BROADCAST
pattern Broadcast :: SocketOption

{-# LINE 135 "Network\\Socket\\Options.hsc" #-}
pattern Broadcast      = SockOpt (65535) (32)
{-# LINE 136 "Network\\Socket\\Options.hsc" #-}

{-# LINE 139 "Network\\Socket\\Options.hsc" #-}
-- | SO_SNDBUF
pattern SendBuffer :: SocketOption

{-# LINE 142 "Network\\Socket\\Options.hsc" #-}
pattern SendBuffer     = SockOpt (65535) (4097)
{-# LINE 143 "Network\\Socket\\Options.hsc" #-}

{-# LINE 146 "Network\\Socket\\Options.hsc" #-}
-- | SO_RCVBUF
pattern RecvBuffer :: SocketOption

{-# LINE 149 "Network\\Socket\\Options.hsc" #-}
pattern RecvBuffer     = SockOpt (65535) (4098)
{-# LINE 150 "Network\\Socket\\Options.hsc" #-}

{-# LINE 153 "Network\\Socket\\Options.hsc" #-}
-- | SO_KEEPALIVE
pattern KeepAlive :: SocketOption

{-# LINE 156 "Network\\Socket\\Options.hsc" #-}
pattern KeepAlive      = SockOpt (65535) (8)
{-# LINE 157 "Network\\Socket\\Options.hsc" #-}

{-# LINE 160 "Network\\Socket\\Options.hsc" #-}
-- | SO_OOBINLINE
pattern OOBInline :: SocketOption

{-# LINE 163 "Network\\Socket\\Options.hsc" #-}
pattern OOBInline      = SockOpt (65535) (256)
{-# LINE 164 "Network\\Socket\\Options.hsc" #-}

{-# LINE 167 "Network\\Socket\\Options.hsc" #-}
-- | SO_LINGER: timeout in seconds, 0 means disabling/disabled.
pattern Linger :: SocketOption

{-# LINE 170 "Network\\Socket\\Options.hsc" #-}
pattern Linger         = SockOpt (65535) (128)
{-# LINE 171 "Network\\Socket\\Options.hsc" #-}

{-# LINE 174 "Network\\Socket\\Options.hsc" #-}
-- | SO_REUSEPORT
pattern ReusePort :: SocketOption

{-# LINE 179 "Network\\Socket\\Options.hsc" #-}
pattern ReusePort      = SockOpt (-1) (-1)

{-# LINE 181 "Network\\Socket\\Options.hsc" #-}
-- | SO_RCVLOWAT
pattern RecvLowWater :: SocketOption

{-# LINE 184 "Network\\Socket\\Options.hsc" #-}
pattern RecvLowWater   = SockOpt (65535) (4100)
{-# LINE 185 "Network\\Socket\\Options.hsc" #-}

{-# LINE 188 "Network\\Socket\\Options.hsc" #-}
-- | SO_SNDLOWAT
pattern SendLowWater :: SocketOption

{-# LINE 191 "Network\\Socket\\Options.hsc" #-}
pattern SendLowWater   = SockOpt (65535) (4099)
{-# LINE 192 "Network\\Socket\\Options.hsc" #-}

{-# LINE 195 "Network\\Socket\\Options.hsc" #-}
-- | SO_RCVTIMEO: this does not work at this moment.
pattern RecvTimeOut :: SocketOption

{-# LINE 198 "Network\\Socket\\Options.hsc" #-}
pattern RecvTimeOut    = SockOpt (65535) (4102)
{-# LINE 199 "Network\\Socket\\Options.hsc" #-}

{-# LINE 202 "Network\\Socket\\Options.hsc" #-}
-- | SO_SNDTIMEO: this does not work at this moment.
pattern SendTimeOut :: SocketOption

{-# LINE 205 "Network\\Socket\\Options.hsc" #-}
pattern SendTimeOut    = SockOpt (65535) (4101)
{-# LINE 206 "Network\\Socket\\Options.hsc" #-}

{-# LINE 209 "Network\\Socket\\Options.hsc" #-}
-- | SO_USELOOPBACK
pattern UseLoopBack :: SocketOption

{-# LINE 212 "Network\\Socket\\Options.hsc" #-}
pattern UseLoopBack    = SockOpt (65535) (64)
{-# LINE 213 "Network\\Socket\\Options.hsc" #-}

{-# LINE 216 "Network\\Socket\\Options.hsc" #-}

{-# LINE 217 "Network\\Socket\\Options.hsc" #-}


{-# LINE 219 "Network\\Socket\\Options.hsc" #-}
-- | TCP_MAXSEG
pattern MaxSegment :: SocketOption

{-# LINE 224 "Network\\Socket\\Options.hsc" #-}
pattern MaxSegment     = SockOpt (-1) (-1)

{-# LINE 226 "Network\\Socket\\Options.hsc" #-}
-- | TCP_NODELAY
pattern NoDelay :: SocketOption

{-# LINE 229 "Network\\Socket\\Options.hsc" #-}
pattern NoDelay        = SockOpt (6) (1)
{-# LINE 230 "Network\\Socket\\Options.hsc" #-}

{-# LINE 233 "Network\\Socket\\Options.hsc" #-}
-- | TCP_USER_TIMEOUT
pattern UserTimeout :: SocketOption

{-# LINE 238 "Network\\Socket\\Options.hsc" #-}
pattern UserTimeout    = SockOpt (-1) (-1)

{-# LINE 240 "Network\\Socket\\Options.hsc" #-}
-- | TCP_CORK
pattern Cork :: SocketOption

{-# LINE 245 "Network\\Socket\\Options.hsc" #-}
pattern Cork           = SockOpt (-1) (-1)

{-# LINE 247 "Network\\Socket\\Options.hsc" #-}

{-# LINE 248 "Network\\Socket\\Options.hsc" #-}


{-# LINE 250 "Network\\Socket\\Options.hsc" #-}
-- | IP_TTL
pattern TimeToLive :: SocketOption

{-# LINE 253 "Network\\Socket\\Options.hsc" #-}
pattern TimeToLive     = SockOpt (0) (4)
{-# LINE 254 "Network\\Socket\\Options.hsc" #-}

{-# LINE 257 "Network\\Socket\\Options.hsc" #-}
-- | Receiving IPv4 TTL.
pattern RecvIPv4TTL :: SocketOption

{-# LINE 260 "Network\\Socket\\Options.hsc" #-}
pattern RecvIPv4TTL    = SockOpt (0) (21)
{-# LINE 261 "Network\\Socket\\Options.hsc" #-}

{-# LINE 264 "Network\\Socket\\Options.hsc" #-}
-- | Receiving IPv4 TOS.
pattern RecvIPv4TOS :: SocketOption

{-# LINE 267 "Network\\Socket\\Options.hsc" #-}
pattern RecvIPv4TOS    = SockOpt (0) (40)
{-# LINE 268 "Network\\Socket\\Options.hsc" #-}

{-# LINE 271 "Network\\Socket\\Options.hsc" #-}
-- | Receiving IP_PKTINFO (struct in_pktinfo).
pattern RecvIPv4PktInfo :: SocketOption

{-# LINE 276 "Network\\Socket\\Options.hsc" #-}
pattern RecvIPv4PktInfo  = SockOpt (0) (19)
{-# LINE 277 "Network\\Socket\\Options.hsc" #-}

{-# LINE 280 "Network\\Socket\\Options.hsc" #-}

{-# LINE 281 "Network\\Socket\\Options.hsc" #-}


{-# LINE 283 "Network\\Socket\\Options.hsc" #-}
-- | IPV6_V6ONLY: don't use this on OpenBSD.
pattern IPv6Only :: SocketOption

{-# LINE 286 "Network\\Socket\\Options.hsc" #-}
pattern IPv6Only       = SockOpt (41) (27)
{-# LINE 287 "Network\\Socket\\Options.hsc" #-}

{-# LINE 290 "Network\\Socket\\Options.hsc" #-}
-- | Receiving IPv6 hop limit.
pattern RecvIPv6HopLimit :: SocketOption

{-# LINE 295 "Network\\Socket\\Options.hsc" #-}
pattern RecvIPv6HopLimit = SockOpt (-1) (-1)

{-# LINE 297 "Network\\Socket\\Options.hsc" #-}
-- | Receiving IPv6 traffic class.
pattern RecvIPv6TClass :: SocketOption

{-# LINE 300 "Network\\Socket\\Options.hsc" #-}
pattern RecvIPv6TClass  = SockOpt (41) (40)
{-# LINE 301 "Network\\Socket\\Options.hsc" #-}

{-# LINE 304 "Network\\Socket\\Options.hsc" #-}
-- | Receiving IPV6_PKTINFO (struct in6_pktinfo).
pattern RecvIPv6PktInfo :: SocketOption

{-# LINE 309 "Network\\Socket\\Options.hsc" #-}
pattern RecvIPv6PktInfo = SockOpt (41) (19)
{-# LINE 310 "Network\\Socket\\Options.hsc" #-}

{-# LINE 313 "Network\\Socket\\Options.hsc" #-}

{-# LINE 314 "Network\\Socket\\Options.hsc" #-}

pattern CustomSockOpt :: (CInt, CInt) -> SocketOption
pattern CustomSockOpt xy <- ((\(SockOpt x y) -> (x, y)) -> xy)
  where
    CustomSockOpt (x, y) = SockOpt x y


{-# LINE 321 "Network\\Socket\\Options.hsc" #-}
{-# COMPLETE CustomSockOpt #-}

{-# LINE 323 "Network\\Socket\\Options.hsc" #-}

{-# LINE 324 "Network\\Socket\\Options.hsc" #-}
-- | Low level 'SO_LINBER' option value, which can be used with 'setSockOpt'.
--
data StructLinger = StructLinger {
    -- | Set the linger option on.
    sl_onoff  :: CInt,

    -- | Linger timeout.
    sl_linger :: CInt
  }
  deriving (Eq, Ord, Show)

instance Storable StructLinger where
    sizeOf    _ = (4)
{-# LINE 337 "Network\\Socket\\Options.hsc" #-}
    alignment _ = alignment (0 :: CInt)

    peek p = do
        onoff  <- ((\hsc_ptr -> peekByteOff hsc_ptr 0)) p
{-# LINE 341 "Network\\Socket\\Options.hsc" #-}
        linger <- ((\hsc_ptr -> peekByteOff hsc_ptr 2)) p
{-# LINE 342 "Network\\Socket\\Options.hsc" #-}
        return $ StructLinger onoff linger

    poke p (StructLinger onoff linger) = do
        ((\hsc_ptr -> pokeByteOff hsc_ptr 0))  p onoff
{-# LINE 346 "Network\\Socket\\Options.hsc" #-}
        ((\hsc_ptr -> pokeByteOff hsc_ptr 2)) p linger
{-# LINE 347 "Network\\Socket\\Options.hsc" #-}

{-# LINE 348 "Network\\Socket\\Options.hsc" #-}

-- | Execute the given action only when the specified socket option is
--  supported. Any return value is ignored.
whenSupported :: SocketOption -> IO a -> IO ()
whenSupported s action
  | isSupportedSocketOption s = action >> return ()
  | otherwise                 = return ()

-- | Set a socket option that expects an 'Int' value.
setSocketOption :: Socket
                -> SocketOption -- Option Name
                -> Int          -- Option Value
                -> IO ()

{-# LINE 362 "Network\\Socket\\Options.hsc" #-}
setSocketOption s so@Linger v = do
    let arg = if v == 0 then StructLinger 0 0 else StructLinger 1 (fromIntegral v)
    setSockOpt s so arg

{-# LINE 366 "Network\\Socket\\Options.hsc" #-}
setSocketOption s sa v = setSockOpt s sa (fromIntegral v :: CInt)

-- | Set a socket option.
setSockOpt :: Storable a
           => Socket
           -> SocketOption
           -> a
           -> IO ()
setSockOpt s (SockOpt level opt) v = do
    with v $ \ptr -> void $ do
        let sz = fromIntegral $ sizeOf v
        withFdSocket s $ \fd ->
          throwSocketErrorIfMinus1_ "Network.Socket.setSockOpt" $
          c_setsockopt fd level opt ptr sz

-- | Get a socket option that gives an 'Int' value.
getSocketOption :: Socket
                -> SocketOption  -- Option Name
                -> IO Int        -- Option Value

{-# LINE 386 "Network\\Socket\\Options.hsc" #-}
getSocketOption s so@Linger = do
    StructLinger onoff linger <- getSockOpt s so
    return $ fromIntegral $ if onoff == 0 then 0 else linger

{-# LINE 390 "Network\\Socket\\Options.hsc" #-}
getSocketOption s so = do
    n :: CInt <- getSockOpt s so
    return $ fromIntegral n

-- | Get a socket option.
getSockOpt :: forall a . Storable a
           => Socket
           -> SocketOption -- Option Name
           -> IO a         -- Option Value
getSockOpt s (SockOpt level opt) = do
    alloca $ \ptr -> do
        let sz = fromIntegral $ sizeOf (undefined :: a)
        withFdSocket s $ \fd -> with sz $ \ptr_sz -> do
            throwSocketErrorIfMinus1Retry_ "Network.Socket.getSockOpt" $
                c_getsockopt fd level opt ptr ptr_sz
        peek ptr


socketOptionBijection :: Bijection SocketOption String
socketOptionBijection =
    [ (UnsupportedSocketOption, "UnsupportedSocketOption")
    , (Debug, "Debug")
    , (ReuseAddr, "ReuseAddr")
    , (SoDomain, "SoDomain")
    , (Type, "Type")
    , (SoProtocol, "SoProtocol")
    , (SoError, "SoError")
    , (DontRoute, "DontRoute")
    , (Broadcast, "Broadcast")
    , (SendBuffer, "SendBuffer")
    , (RecvBuffer, "RecvBuffer")
    , (KeepAlive, "KeepAlive")
    , (OOBInline, "OOBInline")
    , (Linger, "Linger")
    , (ReusePort, "ReusePort")
    , (RecvLowWater, "RecvLowWater")
    , (SendLowWater, "SendLowWater")
    , (RecvTimeOut, "RecvTimeOut")
    , (SendTimeOut, "SendTimeOut")
    , (UseLoopBack, "UseLoopBack")
    , (MaxSegment, "MaxSegment")
    , (NoDelay, "NoDelay")
    , (UserTimeout, "UserTimeout")
    , (Cork, "Cork")
    , (TimeToLive, "TimeToLive")
    , (RecvIPv4TTL, "RecvIPv4TTL")
    , (RecvIPv4TOS, "RecvIPv4TOS")
    , (RecvIPv4PktInfo, "RecvIPv4PktInfo")
    , (IPv6Only, "IPv6Only")
    , (RecvIPv6HopLimit, "RecvIPv6HopLimit")
    , (RecvIPv6TClass, "RecvIPv6TClass")
    , (RecvIPv6PktInfo, "RecvIPv6PktInfo")
    ]

instance Show SocketOption where
    showsPrec = bijectiveShow socketOptionBijection def
      where
        defname = "SockOpt"
        unwrap = \(CustomSockOpt nm) -> nm
        def = defShow defname unwrap showIntInt


instance Read SocketOption where
    readPrec = bijectiveRead socketOptionBijection def
      where
        defname = "SockOpt"
        def = defRead defname CustomSockOpt readIntInt

foreign import CALLCONV unsafe "getsockopt"
  c_getsockopt :: CInt -> CInt -> CInt -> Ptr a -> Ptr CInt -> IO CInt
foreign import CALLCONV unsafe "setsockopt"
  c_setsockopt :: CInt -> CInt -> CInt -> Ptr a -> CInt -> IO CInt
