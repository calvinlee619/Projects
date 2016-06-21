#!/usr/bin/python
import sys

for line in sys.stdin:
	line = line.strip()
	temp = line.split('\t')
	title = temp[1]
	for i in range(2,len(temp)):
		print title + '\t' + temp[i].split(':')[0] + '\t' + temp[i].split(':')[1]