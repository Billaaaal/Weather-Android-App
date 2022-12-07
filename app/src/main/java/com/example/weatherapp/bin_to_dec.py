import math
binary = str(input("Entrez votre nombre binaire : "))
binary_list = list(binary)
total = 0
i = 0
for num in reversed(binary):
    if num == "0":
        pass
    elif num == "1":
        total = total + 2**i
    i = i + 1  
print(total)    
    
        
        