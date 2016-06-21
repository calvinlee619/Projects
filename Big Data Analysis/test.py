#!/usr/bin/python

import sys
import re

current_title = None
current_date = None
current_total_count = 0
current_date_count = 0
title = None
date_views = []

# counting variables of checkpoint
output_lines = 0                # count output lines
most_popular_article = ""       # most popular article in July 2014
most_popular_views = 0          # most popular article views 
celebrities = []                # rank celebrities
p = re.compile(r'\AAriana_Grande$|\AScarlett_Johansson$|\
	           |\ADwayne_Johnson$|\AIggy_Azalea$|\AKurt_Russell$')
# film single-day maximum wikipedia page views
The_Fault_in_Our_Stars = 0  
Guardians_of_the_Galaxy = 0
Maleficent = 0
Gravity = 0
Her = 0

Google_date_views = []         # "Google" date views
Amazon_date_views = []         # "Amazon.com" date views

Dawn_of_the_Planet_of_the_Apes = 0
Dawn_of_the_Planet_of_the_Apes_date = ""


for line in sys.stdin:

	line = line.strip()
	# extract element from line
	title, date, count = line.split('\t')
	try:
		count = int(count)
	except ValueError:
		continue

	if current_title == title:
		if current_date == date:
			current_date_count += count

		# For the word, we have a new date.
		# Save the previous date and update info.
		else:
			if current_date:
				# save it 
				date_views.append([current_date,current_date_count])

				# counting film single-day maximum wikipedia page views
				if current_title == "The_Fault_in_Our_Stars_(film)":
					if The_Fault_in_Our_Stars < current_date_count:
						The_Fault_in_Our_Stars = current_date_count
				if current_title == "Guardians_of_the_Galaxy_(film)":
					if Guardians_of_the_Galaxy < current_date_count:
						Guardians_of_the_Galaxy = current_date_count
				if current_title == "Maleficent_(film)":
					if Maleficent < current_date_count:
						Maleficent = current_date_count
				if current_title == "Gravity_(film)":
					if Gravity < current_date_count:
						Gravity = current_date_count
				if current_title == "Her_(film)":
					if Her < current_date_count:
						Her = current_date_count
				
				# counting "Google" & "Amazon.com" date views
				if current_title == "Google":
					Google_date_views.append([current_date,current_date_count])
				if current_title == "Amazon.com":
					Amazon_date_views.append([current_date,current_date_count])

				# counting single-day maximum views of
				# Dawn_of_the_Planet_of_the_Apes
				if current_title == "Dawn_of_the_Planet_of_the_Apes":
					if Dawn_of_the_Planet_of_the_Apes < current_date_count:
						Dawn_of_the_Planet_of_the_Apes = current_date_count
						Dawn_of_the_Planet_of_the_Apes_date = current_date

			# update date & date count info
			current_date = date
			current_date_count = count

		# sum up total count of a word
		current_total_count += count
	
	# We have a new word, if previous word's page views over
	# than 100,000, then output it with following format:
	# <total month views>\t<article name>\t
    # <date1:page views for date1>\t <date2:page views for date2> ...
	else:
		if current_title and current_total_count > 100000:
			result = str(current_total_count) + '\t' + current_title
			if len(date_views) == 0:
				result += '\t' + current_date + ':' + str(current_date_count)
			else:
				for element in date_views:
					result += '\t' +  element[0] + ':' + str(element[1])
				# print last date
				result += '\t' + current_date + ':' + str(current_date_count)
			print result
			# counting checkpoint information
			output_lines += 1
			if most_popular_views < current_total_count:
				most_popular_views = current_total_count
				most_popular_article = current_title

		# ranking celebrities		
		if current_title:
			if p.match(current_title):
				celebrities.append([current_title, current_total_count])


		# update word
		current_total_count = count
		current_date_count = count
		current_title = title
		current_date = date
		del date_views[:] # empty the list for new word

# NOT forget the last article
if current_title == title:
	if current_total_count > 100000:
		result = str(current_total_count) + '\t' + current_title
		if len(date_views) == 0:
			result += '\t' + current_date + ':' + str(current_date_count)
		else:
			for element in date_views:
				result += '\t' +  element[0] + ':' + str(element[1])
			# print last date
			result += '\t' + current_date + ':' + str(current_date_count)
		print result
		# counting checkpoint information
		output_lines += 1
		if most_popular_views < current_total_count:
			most_popular_views = current_total_count
			most_popular_article = current_title
	
	# ranking celebrities		
	if p.match(current_title):
		celebrities.append([current_title, current_total_count])


# print checkpoint information
print "Output lines : " + str(output_lines)
print "Most popular article : " + most_popular_article
print "Most popular article views : " + str(most_popular_views)
print "Celebrities : "
print celebrities
print "Movie : "
print "The_Fault_in_Our_Stars : " + str(The_Fault_in_Our_Stars)
print "Guardians_of_the_Galaxy : " + str(Guardians_of_the_Galaxy)
print "Maleficent : " + str(Maleficent)
print "Gravity : " + str(Gravity)
print "Her : " + str(Her)
print "Google : "
for line in Google_date_views:
	print line[0] + str(line[1])
print "Amazon.com : "
for line in Amazon_date_views:
	print line[0] + str(line[1])
print "Most number of page views date Dawn_of_the_Planet_of_the_Apes : "
print Dawn_of_the_Planet_of_the_Apes_date