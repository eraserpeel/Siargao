

f = open("coords", 'r')
o = open("out.csv", 'w')
count = 1
l = ""
for line in f.readlines():
    if count % 3 == 0:
        l = l + line.strip('\n') 
        o.write(l + "\n")
        l = "" 
    else:
        l = l + line.strip('\n') + ","
    count += 1
