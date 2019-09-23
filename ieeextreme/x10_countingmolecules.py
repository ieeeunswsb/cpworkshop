import sys

# comment the first line (read file) and uncomment the 2nd line (read stdin) 
# when submitting your answer
with open('x10_countingmolecules.txt', 'r') as f:
# with sys.stdin as f:
    while True:
        buf = f.readline().strip().split(' ')
        
        if len(buf) == 1: break
    
        c, h, o = int(buf[0]), int(buf[1]), int(buf[2])
        
        water = h/4 + o/2 - c
        co2 = -h/4 + o/2
        glucose = (h/4 - o/2 + c)/6
        
        if water.is_integer() and co2.is_integer() and glucose.is_integer():
            print("{:.0f} {:.0f} {:.0f}".format(water, co2, glucose))
        else:
            print("Error")
#        print("{} {} {}".format(water, co2, glucose))