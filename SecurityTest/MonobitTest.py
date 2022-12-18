import math
import sys

def count_ones_zeroes(bits):
    ones = 0
    zeroes = 0
    for bit in bits:
        if (bit == 1):
            ones += 1
        else:
            zeroes += 1
    return (zeroes,ones)

def monobit_test(bits):
    n = len(bits)
    
    zeroes,ones = count_ones_zeroes(bits)
    s = abs(ones-zeroes)
    print ("  1的数量为：%d" % ones)
    print ("  0的数量为：%d" % zeroes)
    ss=s/math.sqrt(float(n))
    print('  S = ',ss)
    p = math.erfc(float(s)/(math.sqrt(float(n)) * math.sqrt(2.0)))
    
    success = (p >= 0.1)
    return (success,p,None)


if __name__ == "__main__":
    bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
          0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
          0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
          0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
          0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 
    bits = list(map(int, sys.argv[1]))
    s1,s2,s3= monobit_test(bits)
#   print(s1)
    print("接受水平p为： %s" %s2)
