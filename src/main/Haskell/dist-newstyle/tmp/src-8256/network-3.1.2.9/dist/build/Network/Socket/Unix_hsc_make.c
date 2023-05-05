#include "C:\ghcup\ghc\9.2.5\lib\template-hsc.h"
#line 3 "Unix.hsc"
#include "HsNet.h"
#line 20 "Unix.hsc"
#if !defined(mingw32_HOST_OS)
#line 22 "Unix.hsc"
#endif 
#line 25 "Unix.hsc"
#if defined(HAVE_GETPEEREID)
#line 27 "Unix.hsc"
#endif 
#line 28 "Unix.hsc"
#ifdef HAVE_GETPEEREID
#line 30 "Unix.hsc"
#endif 
#line 31 "Unix.hsc"
#ifdef DOMAIN_SOCKET_SUPPORT
#line 37 "Unix.hsc"
#endif 
#line 38 "Unix.hsc"
#ifdef HAVE_STRUCT_UCRED_SO_PEERCRED
#line 40 "Unix.hsc"
#endif 
#line 55 "Unix.hsc"
#ifdef HAVE_STRUCT_UCRED_SO_PEERCRED
#line 62 "Unix.hsc"
#elif defined(HAVE_GETPEEREID)
#line 69 "Unix.hsc"
#else 
#line 71 "Unix.hsc"
#endif 
#line 78 "Unix.hsc"
#ifdef HAVE_STRUCT_UCRED_SO_PEERCRED
#line 94 "Unix.hsc"
#else 
#line 96 "Unix.hsc"
#endif 
#line 104 "Unix.hsc"
#ifdef HAVE_GETPEEREID
#line 117 "Unix.hsc"
#else 
#line 119 "Unix.hsc"
#endif 
#line 127 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
#line 129 "Unix.hsc"
#else 
#line 131 "Unix.hsc"
#endif 
#line 144 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
#line 150 "Unix.hsc"
#else 
#line 152 "Unix.hsc"
#endif 
#line 160 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
#line 168 "Unix.hsc"
#else 
#line 170 "Unix.hsc"
#endif 
#line 180 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
#line 195 "Unix.hsc"
#else 
#line 197 "Unix.hsc"
#endif 

int main (void)
{
#line 20 "Unix.hsc"
#if !defined(mingw32_HOST_OS)
#line 22 "Unix.hsc"
#endif 
#line 25 "Unix.hsc"
#if defined(HAVE_GETPEEREID)
#line 27 "Unix.hsc"
#endif 
#line 28 "Unix.hsc"
#ifdef HAVE_GETPEEREID
#line 30 "Unix.hsc"
#endif 
#line 31 "Unix.hsc"
#ifdef DOMAIN_SOCKET_SUPPORT
#line 37 "Unix.hsc"
#endif 
#line 38 "Unix.hsc"
#ifdef HAVE_STRUCT_UCRED_SO_PEERCRED
#line 40 "Unix.hsc"
#endif 
#line 55 "Unix.hsc"
#ifdef HAVE_STRUCT_UCRED_SO_PEERCRED
#line 62 "Unix.hsc"
#elif defined(HAVE_GETPEEREID)
#line 69 "Unix.hsc"
#else 
#line 71 "Unix.hsc"
#endif 
#line 78 "Unix.hsc"
#ifdef HAVE_STRUCT_UCRED_SO_PEERCRED
#line 94 "Unix.hsc"
#else 
#line 96 "Unix.hsc"
#endif 
#line 104 "Unix.hsc"
#ifdef HAVE_GETPEEREID
#line 117 "Unix.hsc"
#else 
#line 119 "Unix.hsc"
#endif 
#line 127 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
#line 129 "Unix.hsc"
#else 
#line 131 "Unix.hsc"
#endif 
#line 144 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
#line 150 "Unix.hsc"
#else 
#line 152 "Unix.hsc"
#endif 
#line 160 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
#line 168 "Unix.hsc"
#else 
#line 170 "Unix.hsc"
#endif 
#line 180 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
#line 195 "Unix.hsc"
#else 
#line 197 "Unix.hsc"
#endif 
    hsc_line (1, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("", hsc_stdout());
    hsc_fputs ("{-# LANGUAGE CPP #-}\n"
           "", hsc_stdout());
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_fputs ("#include \"HsNetDef.h\"\n"
           "\n"
           "module Network.Socket.Unix (\n"
           "    isUnixDomainSocketAvailable\n"
           "  , socketPair\n"
           "  , sendFd\n"
           "  , recvFd\n"
           "  , getPeerCredential\n"
           "  , getPeerCred\n"
           "  , getPeerEid\n"
           "  ) where\n"
           "\n"
           "import System.Posix.Types (Fd(..))\n"
           "\n"
           "import Network.Socket.Buffer\n"
           "import Network.Socket.Imports\n"
           "", hsc_stdout());
#line 20 "Unix.hsc"
#if !defined(mingw32_HOST_OS)
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (21, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("import Network.Socket.Posix.Cmsg\n"
           "", hsc_stdout());
#line 22 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (23, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("import Network.Socket.Types\n"
           "\n"
           "", hsc_stdout());
#line 25 "Unix.hsc"
#if defined(HAVE_GETPEEREID)
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (26, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("import System.IO.Error (catchIOError)\n"
           "", hsc_stdout());
#line 27 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (28, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("", hsc_stdout());
#line 28 "Unix.hsc"
#ifdef HAVE_GETPEEREID
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (29, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("import Foreign.Marshal.Alloc (alloca)\n"
           "", hsc_stdout());
#line 30 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (31, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("", hsc_stdout());
#line 31 "Unix.hsc"
#ifdef DOMAIN_SOCKET_SUPPORT
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (32, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("import Foreign.Marshal.Alloc (allocaBytes)\n"
           "import Foreign.Marshal.Array (peekArray)\n"
           "\n"
           "import Network.Socket.Fcntl\n"
           "import Network.Socket.Internal\n"
           "", hsc_stdout());
#line 37 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (38, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("", hsc_stdout());
#line 38 "Unix.hsc"
#ifdef HAVE_STRUCT_UCRED_SO_PEERCRED
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (39, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("import Network.Socket.Options\n"
           "", hsc_stdout());
#line 40 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (41, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("\n"
           "-- | Getting process ID, user ID and group ID for UNIX-domain sockets.\n"
           "--\n"
           "--   This is implemented with SO_PEERCRED on Linux and getpeereid()\n"
           "--   on BSD variants. Unfortunately, on some BSD variants\n"
           "--   getpeereid() returns unexpected results, rather than an error,\n"
           "--   for AF_INET sockets. It is the user\'s responsibility to make sure\n"
           "--   that the socket is a UNIX-domain socket.\n"
           "--   Also, on some BSD variants, getpeereid() does not return credentials\n"
           "--   for sockets created via \'socketPair\', only separately created and then\n"
           "--   explicitly connected UNIX-domain sockets work on such systems.\n"
           "--\n"
           "--   Since 2.7.0.0.\n"
           "getPeerCredential :: Socket -> IO (Maybe CUInt, Maybe CUInt, Maybe CUInt)\n"
           "", hsc_stdout());
#line 55 "Unix.hsc"
#ifdef HAVE_STRUCT_UCRED_SO_PEERCRED
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (56, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("getPeerCredential sock = do\n"
           "    (pid, uid, gid) <- getPeerCred sock\n"
           "    if uid == maxBound then\n"
           "        return (Nothing, Nothing, Nothing)\n"
           "      else\n"
           "        return (Just pid, Just uid, Just gid)\n"
           "", hsc_stdout());
#line 62 "Unix.hsc"
#elif defined(HAVE_GETPEEREID)
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (63, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("getPeerCredential sock =\n"
           "    go `catchIOError` \\_ -> return (Nothing,Nothing,Nothing)\n"
           "  where\n"
           "    go = do\n"
           "        (uid, gid) <- getPeerEid sock\n"
           "        return (Nothing, Just uid, Just gid)\n"
           "", hsc_stdout());
#line 69 "Unix.hsc"
#else 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (70, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("getPeerCredential _ = return (Nothing, Nothing, Nothing)\n"
           "", hsc_stdout());
#line 71 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (72, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("\n"
           "-- | Returns the processID, userID and groupID of the peer of\n"
           "--   a UNIX-domain socket.\n"
           "--\n"
           "-- Only available on platforms that support SO_PEERCRED.\n"
           "getPeerCred :: Socket -> IO (CUInt, CUInt, CUInt)\n"
           "", hsc_stdout());
#line 78 "Unix.hsc"
#ifdef HAVE_STRUCT_UCRED_SO_PEERCRED
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (79, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("getPeerCred s = do\n"
           "    let opt = SockOpt (", hsc_stdout());
#line 80 "Unix.hsc"
    hsc_const (SOL_SOCKET);
    hsc_fputs ("", hsc_stdout());
    hsc_fputs (") (", hsc_stdout());
#line 80 "Unix.hsc"
    hsc_const (SO_PEERCRED);
    hsc_fputs ("", hsc_stdout());
    hsc_fputs (")\n"
           "", hsc_stdout());
    hsc_line (81, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("    PeerCred cred <- getSockOpt s opt\n"
           "    return cred\n"
           "\n"
           "newtype PeerCred = PeerCred (CUInt, CUInt, CUInt)\n"
           "instance Storable PeerCred where\n"
           "    sizeOf    _ = (", hsc_stdout());
#line 86 "Unix.hsc"
    hsc_const (sizeof(struct ucred));
    hsc_fputs ("", hsc_stdout());
    hsc_fputs (")\n"
           "", hsc_stdout());
    hsc_line (87, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("    alignment _ = alignment (0 :: CInt)\n"
           "    poke _ _ = return ()\n"
           "    peek p = do\n"
           "        pid <- (", hsc_stdout());
#line 90 "Unix.hsc"
    hsc_peek (struct ucred, pid);
    hsc_fputs ("", hsc_stdout());
    hsc_fputs (") p\n"
           "", hsc_stdout());
    hsc_line (91, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("        uid <- (", hsc_stdout());
#line 91 "Unix.hsc"
    hsc_peek (struct ucred, uid);
    hsc_fputs ("", hsc_stdout());
    hsc_fputs (") p\n"
           "", hsc_stdout());
    hsc_line (92, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("        gid <- (", hsc_stdout());
#line 92 "Unix.hsc"
    hsc_peek (struct ucred, gid);
    hsc_fputs ("", hsc_stdout());
    hsc_fputs (") p\n"
           "", hsc_stdout());
    hsc_line (93, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("        return $ PeerCred (pid, uid, gid)\n"
           "", hsc_stdout());
#line 94 "Unix.hsc"
#else 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (95, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("getPeerCred _ = return (0, 0, 0)\n"
           "", hsc_stdout());
#line 96 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (97, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("{-# Deprecated getPeerCred \"Use getPeerCredential instead\" #-}\n"
           "\n"
           "-- | Returns the userID and groupID of the peer of\n"
           "--   a UNIX-domain socket.\n"
           "--\n"
           "--  Only available on platforms that support getpeereid().\n"
           "getPeerEid :: Socket -> IO (CUInt, CUInt)\n"
           "", hsc_stdout());
#line 104 "Unix.hsc"
#ifdef HAVE_GETPEEREID
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (105, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("getPeerEid s = do\n"
           "  alloca $ \\ ptr_uid ->\n"
           "    alloca $ \\ ptr_gid -> do\n"
           "      withFdSocket s $ \\fd ->\n"
           "        throwSocketErrorIfMinus1Retry_ \"Network.Socket.getPeerEid\" $\n"
           "          c_getpeereid fd ptr_uid ptr_gid\n"
           "      uid <- peek ptr_uid\n"
           "      gid <- peek ptr_gid\n"
           "      return (uid, gid)\n"
           "\n"
           "foreign import CALLCONV unsafe \"getpeereid\"\n"
           "  c_getpeereid :: CInt -> Ptr CUInt -> Ptr CUInt -> IO CInt\n"
           "", hsc_stdout());
#line 117 "Unix.hsc"
#else 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (118, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("getPeerEid _ = return (0, 0)\n"
           "", hsc_stdout());
#line 119 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (120, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("\n"
           "{-# Deprecated getPeerEid \"Use getPeerCredential instead\" #-}\n"
           "\n"
           "-- | Whether or not UNIX-domain sockets are available.\n"
           "--\n"
           "--   Since 2.7.0.0.\n"
           "isUnixDomainSocketAvailable :: Bool\n"
           "", hsc_stdout());
#line 127 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (128, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("isUnixDomainSocketAvailable = True\n"
           "", hsc_stdout());
#line 129 "Unix.hsc"
#else 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (130, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("isUnixDomainSocketAvailable = False\n"
           "", hsc_stdout());
#line 131 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (132, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("\n"
           "data NullSockAddr = NullSockAddr\n"
           "\n"
           "instance SocketAddress NullSockAddr where\n"
           "    sizeOfSocketAddress _ = 0\n"
           "    peekSocketAddress _   = return NullSockAddr\n"
           "    pokeSocketAddress _ _ = return ()\n"
           "\n"
           "-- | Send a file descriptor over a UNIX-domain socket.\n"
           "--   Use this function in the case where \'isUnixDomainSocketAvailable\' is\n"
           "--  \'True\'.\n"
           "sendFd :: Socket -> CInt -> IO ()\n"
           "", hsc_stdout());
#line 144 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (145, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("sendFd s outfd = void $ allocaBytes dummyBufSize $ \\buf -> do\n"
           "    let cmsg = encodeCmsg $ Fd outfd\n"
           "    sendBufMsg s NullSockAddr [(buf,dummyBufSize)] [cmsg] mempty\n"
           "  where\n"
           "    dummyBufSize = 1\n"
           "", hsc_stdout());
#line 150 "Unix.hsc"
#else 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (151, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("sendFd _ _ = error \"Network.Socket.sendFd\"\n"
           "", hsc_stdout());
#line 152 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (153, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("\n"
           "-- | Receive a file descriptor over a UNIX-domain socket. Note that the resulting\n"
           "--   file descriptor may have to be put into non-blocking mode in order to be\n"
           "--   used safely. See \'setNonBlockIfNeeded\'.\n"
           "--   Use this function in the case where \'isUnixDomainSocketAvailable\' is\n"
           "--  \'True\'.\n"
           "recvFd :: Socket -> IO CInt\n"
           "", hsc_stdout());
#line 160 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (161, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("recvFd s = allocaBytes dummyBufSize $ \\buf -> do\n"
           "    (NullSockAddr, _, cmsgs, _) <- recvBufMsg s [(buf,dummyBufSize)] 32 mempty\n"
           "    case (lookupCmsg CmsgIdFd cmsgs >>= decodeCmsg) :: Maybe Fd of\n"
           "      Nothing      -> return (-1)\n"
           "      Just (Fd fd) -> return fd\n"
           "  where\n"
           "    dummyBufSize = 16\n"
           "", hsc_stdout());
#line 168 "Unix.hsc"
#else 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (169, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("recvFd _ = error \"Network.Socket.recvFd\"\n"
           "", hsc_stdout());
#line 170 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (171, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("\n"
           "-- | Build a pair of connected socket objects.\n"
           "--   For portability, use this function in the case\n"
           "--   where \'isUnixDomainSocketAvailable\' is \'True\'\n"
           "--   and specify \'AF_UNIX\' to the first argument.\n"
           "socketPair :: Family              -- Family Name (usually AF_UNIX)\n"
           "           -> SocketType          -- Socket Type (usually Stream)\n"
           "           -> ProtocolNumber      -- Protocol Number\n"
           "           -> IO (Socket, Socket) -- unnamed and connected.\n"
           "", hsc_stdout());
#line 180 "Unix.hsc"
#if defined(DOMAIN_SOCKET_SUPPORT)
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (181, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("socketPair family stype protocol =\n"
           "    allocaBytes (2 * sizeOf (1 :: CInt)) $ \\ fdArr -> do\n"
           "      let c_stype = packSocketType stype\n"
           "      _rc <- throwSocketErrorIfMinus1Retry \"Network.Socket.socketpair\" $\n"
           "                  c_socketpair (packFamily family) c_stype protocol fdArr\n"
           "      [fd1,fd2] <- peekArray 2 fdArr\n"
           "      setNonBlockIfNeeded fd1\n"
           "      setNonBlockIfNeeded fd2\n"
           "      s1 <- mkSocket fd1\n"
           "      s2 <- mkSocket fd2\n"
           "      return (s1, s2)\n"
           "\n"
           "foreign import ccall unsafe \"socketpair\"\n"
           "  c_socketpair :: CInt -> CInt -> CInt -> Ptr CInt -> IO CInt\n"
           "", hsc_stdout());
#line 195 "Unix.hsc"
#else 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (196, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("socketPair _ _ _ = error \"Network.Socket.socketPair\"\n"
           "", hsc_stdout());
#line 197 "Unix.hsc"
#endif 
    hsc_fputs ("\n"
           "", hsc_stdout());
    hsc_line (198, "Network\\\\Socket\\\\Unix.hsc");
    hsc_fputs ("", hsc_stdout());
    return 0;
}
