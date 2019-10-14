while True:
    x = int(input())
    
    if x == 0:
        break
        
    print(int((x%17) == 0))