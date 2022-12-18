import math
import copy
import gf2matrix
import sys

def binary_matrix_rank_test(bits,M=32,Q=32):
    n = len(bits)
    N = int(math.floor(n/(M*Q))) 
    print ("  分块数量为： %d" % N)
    print ("  使用数据的比特数： %d" % (N*M*Q))
    print ("  未使用数据的比特数： %d" % (n-(N*M*Q)) )
    
    if N < 38:
        print (" 分块数必须大于37")
        p = 0.0
        return False,p,None
        
    r = M
    product = 1.0
    for i in range(r):
        upper1 = (1.0 - (2.0**(i-Q)))
        upper2 = (1.0 - (2.0**(i-M)))
        lower = 1-(2.0**(i-r))
        product = product * ((upper1*upper2)/lower)
    FR_prob = product * (2.0**((r*(Q+M-r)) - (M*Q)))
    
    r = M-1
    product = 1.0
    for i in range(r):
        upper1 = (1.0 - (2.0**(i-Q)))
        upper2 = (1.0 - (2.0**(i-M)))
        lower = 1-(2.0**(i-r))
        product = product * ((upper1*upper2)/lower)
    FRM1_prob = product * (2.0**((r*(Q+M-r)) - (M*Q)))
    
    LR_prob = 1.0 - (FR_prob + FRM1_prob)
    
    FM = 0    
    FMM = 0   
    remainder = 0
    for blknum in range(N):
        block = bits[blknum*(M*Q):(blknum+1)*(M*Q)]

        matrix = gf2matrix.matrix_from_bits(M,Q,block,blknum) 

        rank = gf2matrix.rank(M,Q,matrix,blknum)

        if rank == M: 
            FM += 1
        elif rank == M-1:
            FMM += 1  
        else:
            remainder += 1

    chisq =  (((FM-(FR_prob*N))**2)/(FR_prob*N))
    chisq += (((FMM-(FRM1_prob*N))**2)/(FRM1_prob*N))
    chisq += (((remainder-(LR_prob*N))**2)/(LR_prob*N))
    p = math.e **(-chisq/2.0)
    success = (p >= 0.01)
    
    print ("  秩为M的矩阵个数  = ",FM)
    print ("  秩为M-1的矩阵个数  = ",FMM)
    print ("  秩小于M-1的矩阵个数  = ",remainder )
    print ("  chiquare = ",chisq)

    return (success, p, None)

bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
          0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
          0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
          0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
          0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 


if __name__ == "__main__":
    bits = list(map(int, sys.argv[1]))
    s1,s2,s3= binary_matrix_rank_test(bits)
 #   print(s1)
    print("接受水平p为： %s" %s2)
