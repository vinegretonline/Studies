#!/usr/bin/env python3
"""Minimal Minecraft RCON client: rcon.py "cmd1" "cmd2" ... (prints every response)."""
import socket
import struct
import sys

HOST, PORT, PASSWORD = "127.0.0.1", 25575, "smoke"


def main(commands):
    sock = socket.create_connection((HOST, PORT), timeout=15)

    def read_exact(n):
        buf = b""
        while len(buf) < n:
            chunk = sock.recv(n - len(buf))
            if not chunk:
                raise EOFError("RCON connection closed")
            buf += chunk
        return buf

    def request(req_id, req_type, body):
        payload = struct.pack("<ii", req_id, req_type) + body.encode("utf-8") + b"\x00\x00"
        sock.sendall(struct.pack("<i", len(payload)) + payload)
        size = struct.unpack("<i", read_exact(4))[0]
        data = read_exact(size)
        resp_id = struct.unpack("<i", data[:4])[0]
        return resp_id, data[8:-2].decode("utf-8", "replace")

    if request(1, 3, PASSWORD)[0] == -1:
        sys.exit("RCON auth failed")
    for i, cmd in enumerate(commands, start=2):
        print("> " + cmd)
        print(request(i, 2, cmd)[1])
    sock.close()


if __name__ == "__main__":
    main(sys.argv[1:])
