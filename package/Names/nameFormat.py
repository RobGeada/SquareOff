import os,sys

cwd = os.getcwd()
f = open(cwd+"/RandName.txt")
names = f.read().split("\n")


f2 = open(cwd+"/randNamesFormatted.txt","w")
for line in names:
	firstChar = line[0:1]
	print firstChar.upper(),line[1:]
	newName = firstChar.upper()+line[1:]
	f2.write(newName)
	f2.write("\n")
