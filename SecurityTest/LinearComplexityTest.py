import math
import sys
from gamma_functions import *

def berelekamp_massey(bits):
    n = len(bits)
    b = [0 for x in bits]  
    c = [0 for x in bits]
    b[0] = 1
    c[0] = 1
    
    L = 0
    m = -1
    N = 0
    while (N < n):

        d = bits[N]
        for i in range(1,L+1):
            d = d ^ (c[i] & bits[N-i])
        if (d != 0):  
            t = c[:]
            for i in range(0,n-N+m):
                c[N-m+i] = c[N-m+i] ^ b[i] 
            if (L <= (N/2)):
                L = N + 1 - L
                m = N
                b = t 
        N = N +1

    return L , c[0:L]
    
def linear_complexity_test(bits,patternlen=None):
    n = len(bits)
    # Step 1.
    if patternlen != None:
        M = patternlen  
    else: 
        if n < 1000000:
            print (" 至少需要 10^6 bits")
            exit()
        M = 512
    K = 6 
    N = int(math.floor(n/M))
    print ("  M = ", M)
    print ("  N = ", N)
    print ("  K = ", K )   
    
    # Step 2 
    LC = list()
    for i in range(N):
        x = bits[(i*M):((i+1)*M)]
        LC.append(berelekamp_massey(x)[0])
    
    # Step 3 
    a = float(M)/2.0
    b = ((((-1)**(M+1))+9.0))/36.0
    c = ((M/3.0) + (2.0/9.0))/(2**M)
    mu =  a+b-c
    
    T = list()
    for i in range(N):
        x = ((-1.0)**M) * (LC[i] - mu) + (2.0/9.0)
        T.append(x)
        
    # Step 4 
    v = [0,0,0,0,0,0,0]
    for t in T:
        if t <= -2.5:
            v[0] += 1
        elif t <= -1.5:
            v[1] += 1
        elif t <= -0.5:
            v[2] += 1
        elif t <= 0.5:
            v[3] += 1
        elif t <= 1.5:
            v[4] += 1
        elif t <= 2.5:
            v[5] += 1            
        else:
            v[6] += 1

    # Step 5 
    pi = [0.010417,0.03125,0.125,0.5,0.25,0.0625,0.020833]
    chisq = 0.0
    for i in range(K+1):
        chisq += ((v[i] - (N*pi[i]))**2.0)/(N*pi[i])
    print ("  chisquare = ",chisq)
    # Step 6
    P = gammaincc((K/2.0),(chisq/2.0))
    success = (P >= 0.01)
    return (success, P, None)
    
if __name__ == "__main__":
    bits = [1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0]
    L,poly = berelekamp_massey(bits)

    #bits = [1,1,0,1,0,1,1,1,1,0,0,0,1,1,1,0,1,0,1,1,1,1,0,0,
        #    0,1,1,1,0,1,0,1,1,1,1,0,0,0,1,1,1,0,1,0,1,1,1,1,
         #   0,0,0,1,1,1,0,1,0,1,1,1,1,0,0,0,1]
    bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
          0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
          0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
          0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
          0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 
    bits = list(map(int, sys.argv[1]))
    success,p,_ = linear_complexity_test(bits,patternlen=7)
    
    print ("L =",L)
    print ("p = ",p)