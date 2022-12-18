import math
import sys
from fractions import Fraction
from gamma_functions import *

def count_ones_zeroes(bits):
    ones = 0
    zeroes = 0
    for bit in bits:
        if (bit == 1):
            ones += 1
        else:
            zeroes += 1
    return (zeroes,ones)

def frequency_within_block_test(bits):

    n = len(bits)
    M = 20
    N = int(math.floor(n/M))
    if N > 99:
        N=99
        M = int(math.floor(n/N))
    
    if len(bits) < 100:
        print ("数据长度过短，至少需要100bits")
    
    print ("  n = %d" % len(bits))
    print ("  N = %d" % N)
    print ("  M = %d" % M)
    
    num_of_blocks = N
    block_size = M 
    
    proportions = list()
    for i in range(num_of_blocks):
        block = bits[i*(block_size):((i+1)*(block_size))]
        zeroes,ones= count_ones_zeroes(block)
        proportions.append(Fraction(ones,block_size))

    chisq = 0.0
    for prop in proportions:  
        chisq += 4.0*block_size*((prop - Fraction(1,2))**2)
    
    print('  chisquare=',chisq)
    p = gammaincc((num_of_blocks/2.0),float(chisq)/2.0)
    success = (p >= 0.01)
    return success,p,None
    

if __name__ == "__main__":
#bit2='0111011010111101111101010000110100011000111011000101010111110001010100010011110110100111010000101010111000101'
    bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
        0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
        0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
        0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
        0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 
    bits = list(map(int, sys.argv[1]))
    s1,s2,s3= frequency_within_block_test(bits)
#    print(s1)
    print("接受水平p为：%s" %s2)
