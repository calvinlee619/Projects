#!/usr/bin/python

import re
import sys
import os

# filter function
def Filter(line):
	name, title, accesses, data = line.split()

	# step1 : line should start with 'en' & without any suffix attached
	p1 = re.compile(r'^en$')
	
	# step2 : filter special pages
	p2 = re.compile(r'^Media:|^Special:|^Talk:|^User:|^User_talk:|\
	|^Project:|^Project_talk:|^File:|^File_talk:|^MediaWiki:|\
	|^MediaWiki_talk:|^Template:|^Template_talk:|^Help:|^Help_talk:|\
	|^Category:|^Category_talk:|^Portal:|^Wikipedia:|^Wikipedia_talk:')
	
	# step3 : filter articles start with lowercase
	p3 = re.compile(r'^[a-z]')

	# step4 : filter image files
	p4 = re.compile(r".*\.jpg$|.*\.gif$|.*\.png$|.*\.JPG$|.*\.GIF$|\
		|.*\.PNG$|.*\.txt$|.*\.ico$")

	# step5 : filter boilerplate articles
	p5 = re.compile(r'^404_error/$|^Main_Page$|\
		|^Hypertext_Transfer_Protocol$|^Favicon.ico$|^Search$')

	flag = not p1.match(title) and p2.match(title) and p3.match(title) and p4.match(title) and p5.match(title)
	if not flag:
		return None
	
	# finally return valid line in format [title name, page views]
	return [title,accesses]
	


# read file 
filename = os.environ["mapreduce_map_input_file"]
flag = ""
date = filename.split('-')

# do filter
for line in sys.stdin:
	flag = Filter(line)
	if not flag:
		print flag[0] + '-' + date[2] + '\t' + flag[1] 