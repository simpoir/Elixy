#!/usr/bin/env python

import os
import sys
import subprocess
import time
import select

m, s  = os.openpty()

p=subprocess.Popen(['sudo', os.environ['LXC_CMD_PREFIX']+'/lxc-console']+sys.argv[1:], stdin=s, stdout=s, stderr=s)
si = sys.stdin.fileno()
so = sys.stdout.fileno()

try:
	while True:
		rd, _, err = select.select([m, si], [], [])
		if m in rd:
			os.write(so, os.read(m, 1024))
		if si in rd:
			os.write(m, os.read(si, 1024))
		if err:
			p.kill()
finally:
	p.kill()

