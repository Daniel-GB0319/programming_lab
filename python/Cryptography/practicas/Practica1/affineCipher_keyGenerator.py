import os

# Detects automatically the OS and clears the terminal / command line
def clearScreen():  
	if os.name == "posix":
		os.system ("clear") # Command for Unix-like OS
	elif os.name == "ce" or os.name == "nt" or os.name == "dos":
		os.system ("cls") # Command for Windows OS

# Calculates valid keys for the affine cipher with 
# the Extended Euclidian Algorithm
def extendedEuclides(key, e_result ,n):
    u,v = key[0],key[1] # Values of K(a,b)
    x,y = 0,0 # Stores the inverse multiplicatives
    x1,y1 = 1,0 # 
    x2,y2 = 0,1
    d = 0 # Result of gdc(a,b)
    
    while(u!=0):
        q=v//u
        r=v-(q*u)
        x=x2-(q*x1)
        y=y2-(q*y1)
        v=u
        u=r
        x2=x1
        x1=x
        y2=y1
        y1=y
        d=v
        x=x2
        y=y2

    e_result[0]=d
    e_result[1]=x

    # Validates if the result of gdc(a,b) = 1
    if e_result[0] == 1: 
        # Here the program writes the valid keys in a text file
        file_text.write(f"K({key[0]} , {key[1]}) -- Inverse of a = {e_result[1]}\n")
    
# Main Function
clearScreen()
print("*** Laboratory Session 1 : Extended Euclidean Algorithm ***")
print("-- Gonzalez Barrientos Geovanni Daniel - 3CM11 - Criptography -- \n")

i,j = 0,0 # Auxiliars in the for sentences 
key = [0,0] # Key for the affine cipher
e_result = [0,0] # Result from the Extended Euclidian Algorithm

n = int(input("Enter the size of the alphabet you want to use: "))

# Here we open the text file that stores the valid keys
file_text = open("validKeys.txt","w") 
file_text.write(f"---- Valid keys for alphabet size = {n} ----\n")

# We use the for sentences in order to try with all 
# the possible values of the key
for j in range (n): # All values for b
    for i in range (1, n-1): # All values for a
        key[0]= i
        key[1]= j
        extendedEuclides(key , e_result , n)


# When the program tried with all the possible valid keys,
# closes the text file and stop the execution
file_text.close()
print("Valid keys generated and saved in validKeys.txt")
print("!!! Have a nice day !!!")